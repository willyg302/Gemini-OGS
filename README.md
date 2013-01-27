![alt text](https://raw.github.com/willyg302/Gemini-OGS/master/Media/Gemini_Logo.png "Gemini Logo")
Authors: William Gaul  
Version: 1.0

---

## About

[Gemini](http://www.geminionlinegs.appspot.com/) is an online game service created specifically for use with UDK. It was born out of frustration with Steamworks, lack of documentation, and a primitive existing TCP-Link system. Gemini streamlines the development process by allowing you to set up an online base in minutes. No more messing with complicated SQL and domains!

You can find out more about Gemini at the relevant page [on my blog](http://willyg302.wordpress.com/gemini/).

## Open Source?

Because I have no more time to maintain Gemini, I have decided to release its sources for the UDK community to study and improve. My initial goal with the project was just to see if it was possible, and it is. But I also want it to be _useful_, and hopefully as an open source project it will be infinitely more beneficial than it ever was as a product.

## Disclaimer

__You can download, modify, and redistribute this code in any way you see fit, as long as:__
- __you give proper attribution, either by saying "code by William Gaul" or linking to my blog or something like that__
- __you do not sell this code or otherwise profit directly from it__
- __you inform everyone that you distribute this code to about the above clauses__

I am also not responsible if your computer explodes, or anything like that :)

## Packages

- [Media](https://github.com/willyg302/Gemini-OGS/tree/master/Media): contains all media related to this project (logos, documentation, etc.)
- [src](https://github.com/willyg302/Gemini-OGS/tree/master/src): contains the Java source for Gemini's backend
- [web](https://github.com/willyg302/Gemini-OGS/tree/master/web): contains all web pages, stylesheets, and assets for the Gemini frontend

## More Info

Gemini is built in Java with a JSP (HTML + embedded Java) frontend, using the Google App Engine framework. I used NetBeans to compile the project, so the provided sources will not work plug-and-play. If you are porting to other platforms consider using the sources as a guide rather than copy-pasting.