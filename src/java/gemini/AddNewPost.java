package gemini;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

public class AddNewPost extends HttpServlet{
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery("select from " + Account.class.getName() + 
                " where password == param");
        query.declareParameters("String param");
        query.setUnique(true);

        Account g = (Account) query.execute(req.getParameter("checker"));
        if(g != null) {
            if(g.getCode().equals(req.getParameter("code"))) {
                ArrayList<String> Msg = g.getMessages();
                String content = req.getParameter("content");
            
            
                if(content.startsWith("flush")) {
                    g.flushMessages(Integer.parseInt(content.substring(5,content.length())));
                } else if(content.startsWith("setfieldsize")) {
                    g.setFieldSize(Integer.parseInt(content.substring(12,content.length())));
                } else if(content.startsWith("delete")) {
                    g.removeMessage(Integer.parseInt(content.substring(6,content.length())));
                } else if(content.equals("terminate")) {
                    pm.deletePersistent(g);
                } else if(content.startsWith("replace")) {
                    g.replaceMessage(Integer.parseInt(content.substring(7,content.indexOf(','))),
                            content.substring(content.indexOf(',')+1,content.length()));
                } else if(content.startsWith("append")) {
                    g.appendMessage(Integer.parseInt(content.substring(6,content.indexOf(','))),
                            content.substring(content.indexOf(',')+1,content.length()));
                } else if(content.startsWith("perform")) {
                    g.performOp(Integer.parseInt(content.substring(7,content.indexOf(','))),
                            Integer.parseInt(content.substring(content.indexOf("op")+2,
                            content.indexOf(",num"))),
                            Integer.parseInt(content.substring(content.indexOf("num")+3,
                            content.length())));
                } else {
                    g.addMessage("<@>" + content);
                }
            }
        } else {
            //
        }
        pm.close();
                
        resp.sendRedirect("/service.jsp?pass=" + req.getParameter("checker")
                + "&code=" + req.getParameter("code"));
    }
}