
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <%@ include file="navBarre.jsp" %>
        <%@ include file="asideMateriel.jsp" %>
        
        <section style="margin-bottom:200px;" class="SectionAvecAside">
        
        	<%@ include file="message.jsp" %>
        	
        	<article class="container form-signin">
	        	<form method="POST" action="BilanStock" name="FormulaireChoix">
		            <h6>Choisissez une des possibilités :</h6> 
		            <fieldset>
		                <input type="radio" name="Choix" value="DeuxDates" checked/> <span id="Choix1"><label>Entre deux dates : de </label> <input type="date" name="DateDebutBilan" id="DateDebutBilan" value="<% out.print( aujourdhui.afficherSurInput() ); %>"/> à <input type="date" name="DateFinBilan" id="DateFinBilan" value="<% out.print( aujourdhui.afficherSurInput() ); %>"/></span>
		            </fieldset>
		            <fieldset>
		                <input type="radio" name="Choix" value="Annee"/> <span id="Choix2"><label>Durant l'année : </label> <input type="number" name="AnneeBilan" id="AnneeBilan" value="<% out.print( aujourdhui.getAnnee() ); %>"/></span>
		            </fieldset>
		            <fieldset>
		                <input type="radio" name="Choix" value="Mois"/> <span id="Choix3"><label>Le mois de : </label>
		                <select name="MoisBilan" id="MoisBilan">
		                    <option value="1" <% if( aujourdhui.getMois() == 1 ) out.print( "selected"); %>>Janvier</option>
		                    <option value="2" <% if( aujourdhui.getMois() == 2 ) out.print( "selected"); %>>Février</option>
		                    <option value="3" <% if( aujourdhui.getMois() == 3 ) out.print( "selected"); %>>Mars</option>
		                    <option value="4" <% if( aujourdhui.getMois() == 4 ) out.print( "selected"); %>>Avril</option>
		                    <option value="5" <% if( aujourdhui.getMois() == 5 ) out.print( "selected"); %>>Mai</option>
		                    <option value="6" <% if( aujourdhui.getMois() == 6 ) out.print( "selected"); %>>Juin</option>
		                    <option value="7" <% if( aujourdhui.getMois() == 7 ) out.print( "selected"); %>>Juillet</option>
		                    <option value="8" <% if( aujourdhui.getMois() == 8 ) out.print( "selected"); %>>Août</option>
		                    <option value="9" <% if( aujourdhui.getMois() == 9 ) out.print( "selected"); %>>Septembre</option>
		                    <option value="10" <% if( aujourdhui.getMois() == 10 ) out.print( "selected"); %>>Octobre</option>
		                    <option value="11" <% if( aujourdhui.getMois() == 11 ) out.print( "selected"); %>>Novembre</option>
		                    <option value="12" <% if( aujourdhui.getMois() == 12 ) out.print( "selected"); %>>Décembre</option>
		                </select></span>
		            </fieldset>
		            <input class="btn btn-primary" type="submit" name="Afficher" value="Afficher"/>
	    		</form>
	    		<br/><br/>
		        <div class="row">
			        <table style="width:1000px;" class="table">
			        	<thead>
				            <tr>
				                <th>N° Materiel</th>
				                <th>Designation</th>
				                <th>Prix Unitaire</th>
				                <th>Nombre de ventes</th>
				                <th>Somme total vendu</th>
				            </tr>
			            </thead>
			            <tbody>
			                <c:forEach var="maVente" items="${ mesVentes }" >
			                    <tr>
			                        <td><c:out value="M${ maVente.numero }"/></td>
			                        <td><c:out value="${ maVente.design }"/></td>
			                        <td><c:out value="${ maVente.pu }"/> Fmg</td>
			                        <td><c:out value="${ maVente.nombreVente }"/></td>
			                        <td><c:out value="${ maVente.totalVendu }"/> Fmg</td>
			                    </tr>
			
			                </c:forEach>
			            </tbody>
			        </table>
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
       	<script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/dynamiserChoix.js"></script>
    </body>
</html>
