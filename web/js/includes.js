function printHeader(title) {
    document.write("<div id=\"wrappertop\"></div><div id=\"wrapper\"><div id=\"content\"><div id=\"header\">");
    document.write("<h1><a href=\"\"><img src=\"images/logo.png\" alt=\"Gemini\"></a></h1></div>");
    document.write("<div id=\"darkbanner\" class=\"banner320\"><h2>" + title + "</h2></div><div id=\"darkbannerwrap\"></div>");
}

function printFooter() {
	document.write("</div></div><div id=\"wrapperbottom_branding\"><div id=\"wrapperbottom_branding_text\">");
	document.write("Copyright &copy; " + new Date().getFullYear() + " <a href=\"http://willyg302.wordpress.com/\" style='text-decoration:none'>WillyG Productions</a>.");
	document.write("<br><hr style=\"height:10px;border:none;\">Like Gemini? Then you'll love:<a href=\"http://sagittarius-ogs.appspot.com/\"><img width=\"310\" src=\"https://raw.github.com/willyg302/Sagittarius/master/Media/Sagittarius_Logo_922x260.png\" /></a></div></div>");
}