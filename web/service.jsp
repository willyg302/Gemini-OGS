<%@ page import="javax.jdo.Query" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="gemini.Account" %>
<%@ page import="gemini.PMF" %>

<html>
    <head>
<%
    if(request.getParameter("content") != null) {
%>	
        <script language="JavaScript" type="text/javascript">
function getParams() {
    var idx = document.URL.indexOf('?');
    if (idx != -1) {
        var tempParams = new Object();
        var pairs = document.URL.substring(idx+1,document.URL.length).split('&');
        for (var i=0; i<pairs.length; i++) {
            nameVal = pairs[i].split('=');
            tempParams[nameVal[0]] = nameVal[1];
        }
    return tempParams;
    }
}
var params = getParams();
        </script>  
<%
  }
%>
    </head>
    
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
            ArrayList<String> Msg = g.getMessages();
            if(Msg.size() == 0) {
                out.println("This account has no messages.");
            } else {
                int i;
                int iter = Math.min(Msg.size(), g.getFieldSize());
                for(i = 0; i < iter; i++)
                    out.println(Msg.get(i));
            }
            out.println("\n<@>" + Msg.size() + "#" + g.getFieldSize() + "#");
        }
    }
    pm.close();
%>
        <form name="form1" action="/sign" method="post">
            <input type="hidden" name="content" />
            <input type="hidden" name="checker" />
            <input type="hidden" name="code" />
        </form>
<%
    if(request.getParameter("content") != null) {
%>
        <script language="JavaScript" type="text/javascript">
pvName = unescape(params["pass"]);
if(pvName) document.form1.checker.value = pvName;
pvMsg = unescape(params["content"]);
if(pvMsg) document.form1.content.value = pvMsg;
pvCode = unescape(params["code"]);
if(pvCode) document.form1.code.value = pvCode;
document.form1.submit();
        </script>
<%
  }
%>
    </body>
</html>