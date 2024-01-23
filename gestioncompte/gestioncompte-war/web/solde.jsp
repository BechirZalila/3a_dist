<%-- 
    Document   : solde
    Created on : 26 oct. 2009, 13:37:37
    Author     : zalila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="compte" uri="/WEB-INF/tlds/utilities.tld"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Consultation du Solde</h1>
        Votre solde est de <b>${compte:solde()}</b> dinars.
    </body>
</html>
