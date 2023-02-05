
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% HttpSession maSession = request.getSession(); %>
<%@ page import="modele.Calendrier" %>

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
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/couleurs.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/footer.css"/>
        
    </head>
    <body>
    
        <%@ include file="navBarreClient.jsp" %>
        
        <section style="margin-bottom:300px;">
	        <article class="container form-signin">
		        <form class="container form-signin" method="GET" action="Facture">
		        	<div id="CachePourClient">
						<label for="NumUser"> Numero du Client : </label>
			        	<input class="" type="text" name="NumUser" id="NumUser" value="${ sessionScope.UtilisateurActuel.numero }" required /><br/><br/>
			        	<label for="Nom"> Nom : </label>
			        	<input type="text" name="Nom" id="Nom" value="${ sessionScope.UtilisateurActuel.nom }" required/><br/><br/>
		        	</div>
		        	<label for="Annee">Année : </label>
		        	<input type="number" name="Annee" id="Annee" value=<% out.println( new Calendrier().getAnnee() ); %> required/> 
		        	<button class="btn btn-primary"><i class="fas fa-search"></i> afficher</button>
		        </form>
		        <br/>
		        <div>
			        <table style="width:1000px;" class="table" id="TableFacture">
			            <thead>
			                <tr>
			                    <th>Designation</th>
			                    <th>Prix Unitaire</th>
			                    <th>Quantité</th>
			                    <th>Montant</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach var="monFacture" items="${ Facture }" >
			                    <tr>
			                        <td><c:out value="${ monFacture.designMateriel }"/></td>
			                        <td><c:out value="${ monFacture.pu }"/> Fmg</td>
			                        <td><c:out value="${ monFacture.qte }"/></td>
			                        <td><c:out value="${ monFacture.montant }"/> Fmg</td>
			                        <c:set var="total" value="${ monFacture.total }"/>
			                        <c:set var="totalLettre" value="${ monFacture.totalLettre }"/>
			                    </tr>
			                </c:forEach>
			                	<tr>
			                		<td colspan="2"></td>
				                	<td>Total</td>
				                	<td><c:out value="${ total }"/> Fmg</td>
			                	</tr>
			            </tbody>
			        </table>
			        <p style="font-family:Cursive; font-size:18px;">Arretée la présente facture à la somme de : <c:out value="${ totalLettre }"/> Fmg</p>
		        </div>
		    </article>
	     </section>
	     
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
	        
	   	<script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/navBarre.js"></script>
		<%
			if( maSession.getAttribute( "type" ).equals( "client" ) )
			{
				out.print( "<script src=\"ScriptS/cacherInput.js\"></script>" );
			}
		%>
    </body>
</html>
