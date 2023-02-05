
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
		<%@ include file="navBarre.jsp" %>
        
        <section style="margin-bottom:150px;">
        
	        <%@ include file="message.jsp" %>
	        
	        <article class="container form-signin">
	        
		        <div class="row">
			        <table style="width:1000px;" class="table">
			            <thead>
			                <tr>
			                    <th>N° Client</th>
			                    <th>N° Materiel</th>
			                    <th>Quantité</th>
			                    <th>Date d'achat</th>
			                    <th>Action</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach var="monAchat" items="${ Achat }" >
			                    <tr>
			                        <td><c:out value="C${ monAchat.numeroUser }"/></td>
			                        <td><c:out value="M${ monAchat.numeroMateriel }"/></td>
			                        <td><c:out value="${ monAchat.qte }"/></td>
			                        <td><c:out value="${ monAchat.dateAchat }"/></td>
			                        <td>
			                            <a href="Achat?edit=<c:out value="${ monAchat.id }"/>" class="btn btn-info btn-sm"><i class='fas fa-edit'></i> Modifier</a>
			                            <a href="Achat?delete=<c:out value="${ monAchat.id }"/>" class="btn btn-danger btn-sm"><i class='fas fa-trash'></i> Supprimer</a>
			                        </td>
			                    </tr>
			                </c:forEach>
			            </tbody>
			        </table>
		        </div>
	        </article>
	        <% if( request.getParameter( "edit" ) != null ) { %>
	            <%@ include file="ModalModifierAchat.jsp" %>
	        <% } %>
	        
	        <% if( request.getParameter( "delete" ) != null ) { %>
	            <%@ include file="ModalSupprimerAchat.jsp" %>
	        <% } %>
	        
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
        
        <script src="${ pageContext.request.contextPath }/ScriptS/navBarre.js" ></script>
    </body>
</html>
