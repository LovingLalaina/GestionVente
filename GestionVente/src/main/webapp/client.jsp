
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        
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
        
        <section class="SectionAvecAside" style="margin-bottom:200px;">
	        <%@ include file="message.jsp" %>
	        
	        <article class="container form-signin">
		        <div class="row">
		            <div class="col-md-6"><input name="RechercheUtilisateur" id="RechercheUtilisateur" class="form-control" type=text placeholder="Numéro ou Nom..." maxlength="50" style="background-color: rgb(226, 226, 226);color: black;"/></div>
		        </div>
		        <br/><br/>
		        
		        <div class="row div-table-utilisateur">
			        <table style="width:1000px;" class="table" id="TableUtilisateur">
			            <thead>
			                <tr>
			                    <th>N° Client</th>
			                    <th>Nom</th>
			                    <th>Adresse Mail</th>
			                    <th>N° Telephone</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach var="monUtilisateur" items="${ Utilisateurs }" >
			                    <tr>
			                        <td><c:out value="${ monUtilisateur.numeroChaine }"/></td>
			                        <td><c:out value="${ monUtilisateur.nom }"/></td>
			                        <td><c:out value="${ monUtilisateur.adresseMail }"/></td>
			                        <td><c:out value="${ monUtilisateur.numeroTelephone }"/></td>
			                    </tr>
			                </c:forEach>
			            </tbody>
			        </table>
		        </div>
		    </article>
        </section>
	    
	    <div class="div-footer">
	    	<footer id="Footer">
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
        	
       	<script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/rechercheUtilisateurAjax.js"></script>
       	<script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/navBarre.js"></script>
    </body>
</html>
