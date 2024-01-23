<%-- 
    Document   : debit
    Created on : 26 oct. 2009, 17:10:18
    Author     : zalila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="compte" uri="/WEB-INF/tlds/utilities.tld"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Débit</h1>
        Votre ancien solde est de <b>${compte:solde()}</b> dinars.
   
        <c:choose>
            <c:when test="${compte:debiter(param.debit)== false}">
                Votre solde de <b>${compte:solde()}</b> est insuffisant pour
                effectuer un débit de ${param.debit} dinars.
            </c:when>
            <c:otherwise>
                Après un débit de ${param.debit}, votre nouveau solde est de
                <b>${compte:solde()}</b> dinars.
            </c:otherwise>
        </c:choose>
    </body>
</html>
