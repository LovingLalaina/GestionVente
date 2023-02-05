
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="modele.MaterielBean" %>

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
        
        <section style="margin-bottom:50px;" class="SectionAvecAside">
        <%@ include file="message.jsp" %>
        	<article class="container form-signin">
		        <div class="row">
		            <div class="col-md-6"><input name="RechercheMateriel" id="RechercheMateriel" class="form-control" type=text placeholder="Numero ou Design..." maxlength="50" style="background-color: rgb(226, 226, 226);color: black;"/></div>
		        	<div class="col-md-2"></div>
		        	<div class="col-md-4"><a class="btn btn-success" id="OuvrirModalAjoutMateriel" href="#ModalAjoutMateriel" data-toggle="modal"><span style="position:relative;"><i class="fas fa-plus text-white"></i> AJOUTER <i class="fas fa-server text-white"></i></span></a></div>
		        </div>
		        <br/><br/>
		        
		        <div class="row div-table-materiel">
				    <table style="width:1000px;" class="table" id="TableMateriel">
			            <thead>
			                <tr>
			                    <th>N° Materiel</th>
			                    <th>Designation</th>
			                    <th>Prix unitaire</th>
			                    <th>Stock</th>
			                    <th>Somme total des ventes</th>
			                    <th>Action</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach var="monMateriel" items="${ MaterielComplet }" >
			                    <tr>
			                        <td><c:out value="${ monMateriel.numeroChaine }"/></td>
			                        <td><c:out value="${ monMateriel.design }"/></td>
			                        <td><c:out value="${ monMateriel.pu }"/> Fmg</td>
			                        <td><c:out value="${ monMateriel.stock }"/></td>
			                        <td><c:out value="${ monMateriel.totalVendu }"/> Fmg</td>
			                        <td>
			                            <a href="MaterielComplet?edit=<c:out value="${ monMateriel.numero }"/>" class="btn btn-info btn-sm"><i class='fas fa-edit'></i> Modifier</a>
			                            <a href="MaterielComplet?delete=<c:out value="${ monMateriel.numero }"/>" class="btn btn-danger btn-sm"><i class='fas fa-trash'></i> Supprimer</a>
			                        </td>
			                    </tr>
			
			                </c:forEach>
			            </tbody>
			        </table>
			 	</div>
	        </article>
	        
	        <%@ include file="ModalAjoutMateriel.jsp" %>
	        
			<% if( request.getParameter( "edit" ) != null ) { %>
	            <%@ include file="ModalModifierMateriel.jsp" %>
	        <% } %>
	        
	        <% if( request.getParameter( "delete" ) != null ) { %>
	            <%@ include file="ModalSupprimerMateriel.jsp" %>
	        <% } %>
	        
	        <!--------------------- FIN VUE DETAILLEE, CRUD ------------------------------>
	        
	        <!--------------------- DEBUT BILAN  ------------------------------>
	
	        
	        <script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/navBarre.js"></script>   
	        <script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/rechercheMaterielAjax.js"></script>    
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
	    
    </body>
</html>
