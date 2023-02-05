
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
	        
			    <div class="container-fluid">
			        <div class="row px-xl-5">
			            <div class="col-12">
			                <div class="breadcrumb bg-light mb-30">
			                    <i class="fa fa-angle-left" style="position:relative;top:5px;right:5px;"></i><a class="breadcrumb-item text-dark" href="Materiel">Tous les produits</a>
			                </div>
			            </div>
			        </div>
			    </div>
			    
			    <div class="container-fluid pb-5">
			        <div class="row px-xl-5">
			            <div class="col-lg-5 mb-30">
			                <div id="product-carousel" class="carousel slide" data-ride="carousel">
			                    <div class="carousel-inner bg-light">
			                        <div class="carousel-item active">
			                            <img class="w-100 h-100" src="${ MaterielActuel.urlImage }" alt="Image">
			                        </div>
			                    </div>
			                </div>
			            </div>
			
			            <div class="col-lg-7 h-auto mb-30">
			                <div class="h-100 bg-light p-30">
			                    <h3><c:out value="${ MaterielActuel.design }"/></h3>
			                    <h3 class="font-weight-semi-bold mb-4" style="color:maroon;"><c:out value="${ MaterielActuel.pu }"/> Fmg</h3>
			                    <p style="font-size:20px;" class="mb-4"><c:out value="${ MaterielActuel.description }"/> .<br/>Stock disponible : <c:out value="${ MaterielActuel.stock }"/> .</p>
			                    
			                    <div class="d-flex align-items-center mb-4 pt-2">
			                    	<label for="Quantite" style="position:relative;right:2px;">Quantité :</label>
			                        <div class="input-group quantity mr-3" style="width:130px;box-shadow: 5px -5px 5px #a8a8a8;border:1px solid silver;">
			                          	<input id="Quantite" name="Quantite" type="number" class="form-control bg-secondary border-0 text-center" value="1" min="1" max="<c:out value="${ MaterielActuel.stock }"/>"/>	
			                        </div>
			                        <a id="BoutonAchat" href="#ModalConfirmerAchat" data-toggle="modal" class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i> Acheter</a>
			                    </div>
			                    <div class="d-flex pt-2">
			                        <strong class="text-dark mr-2">Partager sur :</strong>
			                        <div class="d-inline-flex">
			                            <a class="text-dark px-2" href="https://www.facebook.com">
			                                <i class="fab fa-facebook-f"></i>
			                            </a>
			                            <a class="text-dark px-2" href="https://www.twitter.com">
			                                <i class="fab fa-twitter"></i>
			                            </a>
			                            <a class="text-dark px-2" href="https://www.linkedin.com">
			                                <i class="fab fa-linkedin-in"></i>
			                            </a>
			                            <a class="text-dark px-2" href="https://www.pinterest.com">
			                                <i class="fab fa-pinterest"></i>
			                            </a>
			                        </div>
			                    </div>
			                </div>
			            </div>
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
		
		<%@ include file="ModalConfirmerAchat.jsp" %>
        <script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/navBarre.js"></script>
    </body>
</html>
