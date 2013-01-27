
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
	<link type="text/css" rel="stylesheet" href="/stylesheets/beautiful.css" />
   
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
    </head>
    <body id="login">
        <div id="wrappertop"></div>
        <div id="wrapper">
            <div id="content">
                <div id="header">
                    <h1><a href=""><img src="images/logo.png" alt="Gemini"></a></h1>
		</div>
		<div id="darkbanner" class="banner320">
                    <h2>Validation</h2>
		</div>
		<div id="darkbannerwrap"></div>
		<form name="form1" method="post" action="/createaccount">
                    <fieldset class="form">                     
<% 
    if (request.getParameter("error") != null) {
        if(request.getParameter("error").equals("1")) {
%>                 
                        <p class="error">
                            <img src="images/error.png" height="16px" width="16px">
                            Passwords must be between 3 and 15 characters and can only
                            contain letters or numbers. Please try again.
                        </p>
<%
       } else if(request.getParameter("error").equals("2")) {
%>              
                        <p class="error">
                            <img src="images/error.png" height="16px" width="16px">
                            The verification code you entered is invalid. Please re-enter
                            the code, or return to the sign-up page to try again.
                        </p>
<%
       } else if(request.getParameter("error").equals("3")) {
%>              
                        <p class="error">
                            <img src="images/error.png" height="16px" width="16px">
                            An account with that password already exists. Please enter
                            a different password.
                        </p>
<%
       }}
%>              
                        <p>
                            <label for="name">Code:</label>
                            <input name="name" id="name" type="text" />
                        </p>
                        <p>
                            <label for="email">Password:</label>
                            <input name="email" id="email" type="text" />
                        </p>
                        <input name="verify" type="hidden" />
			<button type="submit" class="positive" name="Submit">
                            <img src="images/key.png" alt="Announcement"/>Create Account</button>
                    </fieldset>
                </form>
                        
                <script language="JavaScript" type="text/javascript">
pvName = unescape(params["verify"]);
if(pvName) document.form1.verify.value = pvName;
                </script>             
            </div>
        </div>
        <div id="wrapperbottom_branding">
            <div id="wrapperbottom_branding_text">Copyright © 2011 <a href="http://willyg302.wordpress.com/" style='text-decoration:none'>WillyG Productions</a>.</div>
        </div>
    </body>
</html>