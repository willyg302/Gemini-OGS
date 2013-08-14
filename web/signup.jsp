<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="/stylesheets/beautiful.css" />
        <script type="text/javascript" src="/js/includes.js"></script>
    </head>
    <body id="login">
        <script>printHeader("Sign Up");</script>
        <form name="form1" method="post" action="/sendmail">
            <fieldset class="form">                     
                <%
                    if (request.getParameter("error") != null) {
                        if (request.getParameter("error").equals("1")) {
                %>                 
                <p class="error">
                    <img src="images/error.png" height="16px" width="16px">
                    The email address you entered is invalid. Please enter
                    a valid email address.
                </p>
                <%} else if (request.getParameter("error").equals("2")) {
                %>              
                <p class="error">
                    <img src="images/error.png" height="16px" width="16px">
                    An error occurred while sending the message. Please
                    try again.
                </p>
                <%        }
                    }
                %>              
                <p>
                    <label for="name">Name:</label>
                    <input name="name" id="name" type="text" />
                </p>
                <p>
                    <label for="email">Email:</label>
                    <input name="email" id="email" type="text" />
                </p>
                <button type="submit" class="positive" name="Submit">
                    <img src="images/key.png" alt="Announcement"/>Submit
                </button>
                <ul id="forgottenpassword">
                    <li class="boldtext">|</li>
                    <li><a href="whatis.jsp">What is this?</a></li>
                </ul>
            </fieldset>
        </form>
        <script>printFooter();</script>
    </body>
</html>