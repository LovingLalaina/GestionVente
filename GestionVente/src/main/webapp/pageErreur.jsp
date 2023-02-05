
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% HttpSession maSession = request.getSession(); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>HighTech Market</title>
        <%@ include file="ressources.jsp" %>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/style.css"/>
    </head>
    <body>
    	<section>
	        <h1>OUPPPSS !!!</h1>
	        <p>UNE ERREUR EST SURVENUE :( <br/><br/><c:out value="${ messageErreur }"/></p>
        </section>
    </body>
</html>
