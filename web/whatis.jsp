<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>		
        <link type="text/css" rel="stylesheet" href="/stylesheets/beautiful.css" />
        <script type="text/javascript" src="/js/includes.js"></script>
    </head>
    <body id="login">
        <script>printHeader("Sign-up Information");</script>
        <form name="form1" method="post" action="/mainred">
            <fieldset class="form">
                <p>Gemini needs your name and email to verify that you are a legitimate user. 
                    We will send you an email to verify your account. After that, your 
                    personal information will never be stored or used in any way.</p><br>
                <button type="submit" class="positive" name="Submit">
                    <img src="images/key.png" alt="Announcement"/>Return to Sign-up
                </button>
            </fieldset>
        </form>
        <script>printFooter();</script>
    </body>
</html>