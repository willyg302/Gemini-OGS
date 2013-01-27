<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
<%
    if(request.getParameter("password") != null) {
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
        <form name="form1" action="/register" method="post">
            <input type="hidden" name="password" />
            <input type="hidden" name="code" />
            
            
            <input type="hidden" name="port" />
            <input type="hidden" name="name" />
            <input type="hidden" name="pass" />
            <input type="hidden" name="type" />
            <input type="hidden" name="list" />
            <input type="hidden" name="curr" />
            <input type="hidden" name="maxp" />
            <input type="hidden" name="rank" />
            <input type="hidden" name="bing" />
            
            
            
        </form>
<%
    if(request.getParameter("password") != null) {
%>
        <script language="JavaScript" type="text/javascript">

pvPassword = unescape(params["password"]);
if(pvPassword) document.form1.password.value = pvPassword;

pvCode = unescape(params["code"]);
if(pvCode) document.form1.code.value = pvCode;


pvPort = unescape(params["port"]);
if(pvPort) document.form1.port.value = pvPort;

pvName = unescape(params["name"]);
if(pvName) document.form1.name.value = pvName;

pvPass = unescape(params["pass"]);
if(pvPass) document.form1.pass.value = pvPass;

pvType = unescape(params["type"]);
if(pvType) document.form1.type.value = pvType;

pvList = unescape(params["list"]);
if(pvList) document.form1.list.value = pvList;

pvCurr = unescape(params["curr"]);
if(pvCurr) document.form1.curr.value = pvCurr;

pvMaxp = unescape(params["maxp"]);
if(pvMaxp) document.form1.maxp.value = pvMaxp;

pvRank = unescape(params["rank"]);
if(pvRank) document.form1.rank.value = pvRank;

pvBing = unescape(params["bing"]);
if(pvBing) document.form1.bing.value = pvBing;

document.form1.submit();
        </script>
<%
  }
%>
    </body>
</html>