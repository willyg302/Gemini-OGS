<%@ page import="java.util.Date"%>
<%@ page import="javax.jdo.Query" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="gemini.Account" %>
<%@ page import="gemini.Server" %>
<%@ page import="gemini.PMF" %>

<html>
    <body>
<%
    PersistenceManager pm = PMF.get().getPersistenceManager();
    Query query = pm.newQuery("select from " + Account.class.getName() + 
            " where password == param");
    query.declareParameters("String param");
    query.setUnique(true);
    Account g = (Account) query.execute(request.getParameter("pass"));
    if(g == null) {
        out.println("ERROR: Account not found.");
    } else if(request.getParameter("code") == null) {
        out.println("ERROR: You must provide your 7-digit code.");
    } else {    
        if(!request.getParameter("code").equals(g.getCode())) {
            out.println("ERROR: Invalid code.");
        } else {
            
            query = pm.newQuery("select from " + Server.class.getName() +
                    " where account == parampass order by date desc range 0,20");
            query.declareParameters("String parampass");
            query.setUnique(false);
            
            List<Server> mservers = (List<Server>) query.execute(request.getParameter("pass"));
            
            ArrayList<Server> servers = new ArrayList<Server>();
            int servsize = mservers.size();
                
            for(int i = 0; i < mservers.size(); i++) {
                
                
                if(request.getParameter("type") != null) {
                    if(mservers.get(i).getType() != Integer.parseInt(request.getParameter("type"))) {
                        continue;
                    }
                }
                if(request.getParameter("rank") != null) {
                    if(mservers.get(i).getRank() != Integer.parseInt(request.getParameter("rank"))) {
                        continue;
                    }
                }
                if(request.getParameter("bing") != null) {
                    if(mservers.get(i).getBing() != Boolean.parseBoolean(request.getParameter("bing"))) {
                        continue;
                    }
                }
                if(request.getParameter("maxp") != null) {
                    if(mservers.get(i).getMaxp() != Integer.parseInt(request.getParameter("maxp"))) {
                        continue;
                    }
                }
                servers.add(mservers.get(i));
                        
            }
            if(request.getParameter("clean") != null) {
                Date date = new Date();
                date.setTime(date.getTime() - 60000*Integer.parseInt(request.getParameter("clean")));
                for(int i = 0; i < servers.size(); i++) {
                    if(servers.get(i).getDate().before(date)) {
                        pm.deletePersistent(servers.get(i));
                        servers.remove(i);
                        i--;
                    }
                }
            }
            
            
            
            
            
            if(servers.isEmpty()) {
                out.println("No servers found.");
            } else {
                if(request.getParameter("view").equals("false")) {
                    for (Server s : servers) {
                        out.println("<@>" + s.getName() + "~" + s.getAddr() + "~" + 
                                s.getPort() + "~" + s.getPass() + "~" + s.getType() + "~" +
                                s.getList() + "~" + s.getCurr() + "~" + s.getMaxp() + "~" +
                                s.getRank() + "~" + s.getBing());
                    }
                } else { 
%>
        <table border="1">
            <tr>
                <td width="170"><b>Name</b></td>
                <td width="150"><b>IP Address</b></td>
                <td width="50"><b>Port</b></td>
                <td width="150"><b>Gametype</b></td>
                <td width="100"><b># Players</b></td>
                <td width="100"><b>Max Players</b></td>
                <td width="80"><b>Ranked?</b></td>
                <td width="80"><b>In Game?</b></td>
            </tr>
<%
                    for (Server s : servers) { 
%>
            <tr>
                <td width="170"><%=s.getName()%></td>
                <td width="150"><%=s.getAddr()%></td>
                <td width="50"><%=s.getPort()%></td>
                <td width="150"><%
                    switch(s.getType()) {
                        case 0: out.print("Deathmatch"); break;
                        case 1: out.print("Team Deathmatch"); break;
                        case 2: out.print("Capture the Flag"); break;
                        case 3: out.print("Vehicle CTF"); break;
                        default: out.print("Unknown Type"); break;
                    }%></td>
                <td width="100"><%=s.getCurr()%></td>
                <td width="100"><%=s.getMaxp()%></td>
                <td width="80"><%out.print((s.getRank() == 0) ? "No" : "Yes");%></td>
                <td width="80"><%out.print((s.getBing()) ? "Yes" : "No");%></td>
            </tr>
<% 
                    } 
%>
        </table>    
<%                
                }
            }       
            out.println("\n<@>Number of servers: " + servers.size());
        }
    }
    pm.close();
%>
    </body>
</html>