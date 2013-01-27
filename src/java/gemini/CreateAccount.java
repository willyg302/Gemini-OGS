package gemini;

import javax.jdo.PersistenceManager;
import java.io.UnsupportedEncodingException; 
import javax.servlet.http.*;
import java.io.IOException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jdo.Query;

public class CreateAccount extends HttpServlet{
    
    
    
    private static boolean Validate(String password) {
        if((password.length() > 2) && (password.length() < 16)) {
            Pattern p = Pattern.compile("\\w+");
            Matcher m = p.matcher(password);
            return m.matches();
        } else{
            return false;
        }  
    }
    
    private static boolean CheckUnique(String password) {
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
    
        Query query = pm.newQuery("select from " + Account.class.getName() + 
            " where password == param");
        query.declareParameters("String param");
        List<Account> results = (List<Account>) query.execute(password);
    
        if(results.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * Tries to create the account.
     * 
     * Return ERROR specifies if there was an error.
     * 0 - no error
     * 1 - invalid password
     * 2 - invalid verification code
     * 3 - account with that password already exists
     *  
     */
    public static int accountCreator(String code, String pass, String email) 
            throws UnsupportedEncodingException {
        int error = 0;
        
        if(Validate(pass)) {
            if(CheckUnique(pass)) {
                if(code.equals(EmailMethod.Perform(email + /*SECRET SALT...obviously not gonna release this!*/).substring(0,15))) {
                    PersistenceManager pm = PMF.get().getPersistenceManager();
                    Account account = new Account(pass, code.substring(0,7));
                    try {
                        pm.makePersistent(account);
                    } finally {
                        pm.close();
                    }
                } else { error = 2; }
            } else { error = 3; }
        } else { error = 1; }
        return error;
    }
    
    
    
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        int error = accountCreator(req.getParameter("name"),
                req.getParameter("email"),req.getParameter("verify"));
        
        if(error > 0) {
            resp.sendRedirect("/validate.jsp?verify=" + req.getParameter("verify")
                    + "&error=" + error);
        } else {
            resp.sendRedirect("/success.jsp");
            //resp.sendRedirect("/service.jsp?pass=" + req.getParameter("email"));
        }
        
    }
}
