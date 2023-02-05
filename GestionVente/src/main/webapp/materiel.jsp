
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>HighTech Market</title>
        <%@ include file="ressources.jsp" %>
        <%@ include file="ressourcesTemp.jsp" %>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/style.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/navBarre.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/materiel.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/footer.css"/>
    </head>
    <body>
    
    	<%@ include file="navBarreClient.jsp" %>
        <section>
			<%@ include file="message.jsp" %>
	        <article class="container form-signin">
	        	<div class="container-fluid pt-5 pb-3">
			        <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Nos Produits Disponibles</span></h2>
			        <div class="row px-xl-5">
			        
			        	<c:forEach var="monMateriel" items="${ MaterielComplet }">
			        		<c:if test="${ monMateriel.stock >= 1 }">
					        	<div class="col-lg-3 col-md-4 col-sm-6 pb-1">
					                <div class="product-item bg-light mb-4 produit">
					                    <div class="product-img position-relative overflow-hidden">
											<img style="max-width:400px;width:400px;height:180px;" class="img-fluid w-100" src="${ monMateriel.urlImage }"/>
					                        <div class="product-action">
					                            <a class="btn btn-outline-dark btn-square" href="AchatMateriel?numero=<c:out value="${ monMateriel.numero }"/>"><i class="fa fa-shopping-cart"></i></a>
					                        </div>
					                    </div>
					                    <div class="text-center py-4">
					                        <a class="h6 text-decoration-none text-truncate" href="AchatMateriel?numero=${ monMateriel.numero }" style="text-transform:uppercase;"><c:out value="${ monMateriel.design }"/></a>
					                        <div  class="d-flex align-items-center justify-content-center mt-2">
					                            <p><c:out value="${ monMateriel.pu }"/> Fmg</p>
					                        </div>
					                        <p><c:out value="${ monMateriel.description }"/></p>
					                    </div>
					                </div>
					            </div>
				            </c:if>
		                </c:forEach>
			        </div>
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
    </body>
</html>
