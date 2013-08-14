package gemini;

import java.io.IOException;

import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

public class Register extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery("select from " + Account.class.getName() + " where password == param");
        query.declareParameters("String param");
        query.setUnique(true);

        Account g = (Account) query.execute(req.getParameter("password"));
        if (g != null) {
            if (g.getCode().equals(req.getParameter("code"))) {
                query = pm.newQuery("select from " + Server.class.getName() + " where addr == param");
                query.declareParameters("String param");
                query.setUnique(true);
                Server serv = (Server) query.execute(req.getRemoteAddr());
                Date date = new Date();
                if (serv != null) {
                    if (req.getParameter("pass").equals("delete")) {
                        pm.deletePersistent(serv);
                    } else {
                        serv.update(date,
                                Integer.parseInt(req.getParameter("port")),
                                req.getParameter("name"),
                                req.getParameter("pass"),
                                Integer.parseInt(req.getParameter("type")),
                                req.getParameter("list"),
                                Integer.parseInt(req.getParameter("curr")),
                                Integer.parseInt(req.getParameter("maxp")),
                                Integer.parseInt(req.getParameter("rank")),
                                Boolean.parseBoolean(req.getParameter("bing")));
                    }
                } else {
                    //Date date = new Date();
                    Server server = new Server(req.getParameter("password"), date,
                            req.getRemoteAddr(),
                            Integer.parseInt(req.getParameter("port")),
                            req.getParameter("name"),
                            req.getParameter("pass"),
                            Integer.parseInt(req.getParameter("type")),
                            req.getParameter("list"),
                            Integer.parseInt(req.getParameter("curr")),
                            Integer.parseInt(req.getParameter("maxp")),
                            Integer.parseInt(req.getParameter("rank")),
                            Boolean.parseBoolean(req.getParameter("bing")));
                    try {
                        pm.makePersistent(server);
                    } finally {
                        //pm.close();
                    }
                }
            }
        } else {
            //
        }
        pm.close();
        resp.sendRedirect("/browser.jsp?pass=" + req.getParameter("password")
                + "&code=" + req.getParameter("code") + "&view=true");
    }
}