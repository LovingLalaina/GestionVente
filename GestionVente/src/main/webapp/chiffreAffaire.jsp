
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="modele.Calendrier" %>
<%
	Calendrier aujourdhui = new Calendrier();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>HighTech Market</title>
        <%@ include file="ressources.jsp" %>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/style.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/navBarre.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/table.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/footer.css"/>
        
    </head>
    <body>
    
        <%@include file="navBarre.jsp"%>
        <%@ include file="asideUtilisateur.jsp" %>
        
        <section style="margin-bottom:400px;" class="SectionAvecAside">
	        <%@ include file="message.jsp" %>
	        
	        <article class="container form-signin">
		        <form method="GET" action="ChiffreAffaire" class="container form-signin">
		        	<label for="Annee"><span style="font-family:Cursive;">Chiffre d'affaire durant l'année : </span></label>
		        	<input type="number" name="Annee" value="<% out.print( aujourdhui.getAnnee() ); %>" required/>
		        	<input id="AfficherChiffreAffaire" class="btn btn-primary" type="submit" value="afficher"/>
		        </form>
		        <br/>
		        <div class="row">
			        <table style="width:1000px;" class="table">
			            <thead>
			                <tr>
			                    <th>N° Client</th>
			                    <th>Nom</th>
			                    <th>Chiffre d'affaire</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<script> var nomClients = new Array(); var chiffreAffaire = new Array(); </script>
			                <c:forEach var="monUtilisateur" items="${ Utilisateurs }" varStatus="index" >
			                    <tr>
			                        <td><c:out value="C${ monUtilisateur.numero }"/></td>
			                        <td><c:out value="${ monUtilisateur.nom }"/></td>
			                        <td><c:out value="${ monUtilisateur.chiffreAffaire } Fmg"/></td>
			                    </tr>
			                    <script>
			                    	nomClients[${ index.count - 1 }] = "${ monUtilisateur.nom }";
			                    	chiffreAffaire[${ index.count - 1 }] = ${ monUtilisateur.chiffreAffaire };             
			                    </script>
			                </c:forEach>
			            </tbody>
			        </table>
			        
			        <%@ include file="histogramme.jsp" %>
		        </div>
		        
		        
		    </article>
	     </section>
         
		 <div class="div-footer">
	    	 <footer>
		    <div class="contact">
		        <h3>Contact</h3>
		        <p>Tanambao Fianarantsoa 301 RN7</p>
		    </div>
		    <div class="telephone">
		        <h3>Telephone</h3>
		        <p>+261347886545</p>
		    </div>
		    <div class="email">
		        <h3>Email</h3>
		        <p>info@hightech_market.mg</p>
		    </div>
		</footer>
	     </div>
	        
	   	 <script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/navBarre.js"></script>
    </body>
</html>
