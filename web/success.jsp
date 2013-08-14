<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>		
        <link type="text/css" rel="stylesheet" href="/stylesheets/beautiful.css" />
        <script type="text/javascript" src="/js/includes.js"></script>
    </head>
    <body id="login">
        <script>printHeader("Success!");</script>
        <form name="form1" method="post" action="/mainred">
            <fieldset class="form">
                <p>Your account has been successfully created. Please visit the 
                    <a href="http://willyg302.wordpress.com/gemini/">Gemini page</a> to
                    learn more about using our service.</p><br>	
                <button type="submit" class="positive" name="Submit">
                    <img src="images/key.png" alt="Announcement"/>Return to Sign-up
                </button>
            </fieldset>
        </form>
        <script>printFooter();</script>
    </body>
</html>