package gemini;

import java.io.IOException;
import javax.servlet.http.*;

/**
 * A rather stupid class that does nothing except redirect to the main sign-up
 * page. Used to avoid difficulties with form submission when hitting a
 * "return to main"-type button.
 * 
 * @author William
 */

public class MainRedirect extends HttpServlet{    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        resp.sendRedirect("/signup.jsp");
    }
}