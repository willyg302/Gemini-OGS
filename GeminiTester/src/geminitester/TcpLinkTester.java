package geminitester;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is designed to specifically render data from Gemini OGS.
 * 
 * @author William
 */
public class TcpLinkTester {
    private int state;
    private boolean error;
   
    public static final int PERFORM = 1; 
    public static final int AFTER = 2;
    
    public TcpLinkTester() {
        reset();
        error = false;
    }
    
    public final void reset() {
        state = PERFORM;
        //error = false;
    }
      
    /**
     * Sends an HTTP GET request to a specified URL.
     *
     * @param endpoint - The URL of the server.
     *      (Example: "http://www.yahoo.com/search")
     * @param requestParameters - All the request parameters.
     *      (Example: "param1=val1&param2=val2")
     *      Note: This method will add the question mark (?) to the request - 
     *      DO NOT add it yourself.
     * @return - The response from the end point.
     */
    public static String sendGetRequest(String endpoint, String requestParameters) {
        String result = null;
        if(endpoint.startsWith("http://")) {
            try {
                String urlStr = endpoint;
                if(requestParameters != null && requestParameters.length() > 0)
                    urlStr += "?" + requestParameters;
                URL url = new URL(urlStr);
                URLConnection conn = url.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while((line = rd.readLine()) != null)
                    sb.append(line);
                rd.close();
                result = sb.toString();
            } catch (Exception e) {
                result = "Exception: " + e;
            }
        }
        return result;
    }
    
    /**
     * Reads data from the reader and posts it to a server via POST request.
     * 
     * @param data - The data you want to send.
     * @param endpoint - The server's address.
     * @throws Exception
     */
    public static String postData(String data, String endpoint)  {
        URLConnection conn=null;
        String result = null;
        try {
            URL url = new URL(endpoint);
            conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = rd.readLine()) != null)
                sb.append(line);
            wr.close();
            rd.close();
            result = sb.toString();
        } catch (Exception e) {
            result = "Connection error (is server running at " + endpoint + " ?): " + e;
        }
        return result;
    }
    
    /**
     * Strips all HTML tags and Form identifiers from a POST/GET. Note that
     * this only works for Gemini OGS POST/GET messages.
     * 
     * @param toParse - The string to parse. 
     */   
    public static String parseGet(String toParse) {
        String result = "";
        String[] array = toParse.split("<@>");
        int i;
        for(i=1; i < array.length; i++) {
            if(i == array.length - 1) {
                array[i] = array[i].substring(0,array[i].indexOf("<form"));
                int breaker = array[i].indexOf("#");
                array[i] = "Number of Messages: " + array[i].substring(0,breaker)
                         + ", Field Size: " + array[i].substring(breaker+1, 
                        array[i].indexOf("#",breaker+1));
            }
            result = result + array[i].trim() + "\n";
        }
        return result;
    }
    
    public static String getInfo(String Pass, String Code) {
        String result = parseGet(sendGetRequest("http://geminionlinegs.appspot.com/service.jsp",
                "pass=" + Pass + "&code=" + Code));
        result = "Current Messages:\n\n" + result + 
                "\nEnter message to post. Click OK to continue.";
        return result;
    }
      
    public static String postInfo(String Message, String Pass, String Code) {
        String result = "";
        if(Message.startsWith("flush")) {
            result += "Flushing messages older than " + Message.substring(5);
        } else if(Message.startsWith("replace")) {
            result += "Replacing message " + Message.substring(7, Message.indexOf(","))
                    + " with new message";
        } else if(Message.startsWith("delete")) {
            result += "Deleting message " + Message.substring(6);
        } else if(Message.startsWith("setfieldsize")) {
            result += "Setting the account's field size to " + Message.substring(12);
        } else if(Message.startsWith("perform")) {
            String index = Message.substring(7, Message.indexOf(","));
            String op = Message.substring(Message.indexOf(",") + 3, Message.indexOf(",num"));
            String value = Message.substring(Message.indexOf(",num") + 4);
            switch(Integer.parseInt(op)) {
                case 1: result += "Adding " + value + " to message " + index; break;
                case 2: result += "Subtracting " + value + " from message " + index; break;
                case 3: result += "Multiplying message " + index + " by " + value; break;
                case 4: result += "Dividing message " + index + " by " + value; break;
                default: result += "Error, possibly invalid operation"; break;
            }
        } else if(Message.startsWith("append")) {
            result += "Appending new message to end of message " + 
                    Message.substring(6, Message.indexOf(","));
        } else if(Message.equals("terminate")) {
            result += "Terminating account. Warning: this cannot be undone";
        } else {
            result += "Adding new message..";
        }
        String serverReturn = postData("content=" + Message + "&checker=" + Pass + "&code=" + Code
                ,"http://geminionlinegs.appspot.com/sign");
        
        
        if(serverReturn.indexOf("ERROR: Account not found.") > 0) {
            result = "ERROR: Account not found.\nPlease check that your password is correct."
                    + "\n\nClick OK to try again.";
            //setError(true);
        } else if(serverReturn.indexOf("ERROR: Invalid code.") > 0) {
            result = "ERROR: Invalid code.\nPlease check that your code is correct."
                    + "\n\nClick OK to try again.";
            //setError(true);
        } else {
            result += ".\n\n" + parseGet(serverReturn)
                    + "\nOperation complete. Click OK to post another message.";
            //this.error = false;
            //setError(false);
        }
        return result;
    }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state1) {
        state = state1;
    }
    
    public boolean getError() {
        return error;
    }
    
    public void setError(boolean value) {
        error = value;
    }
}