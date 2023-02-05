
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
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/footer.css"/>
    </head>
    <body>
    
    	<%@ include file="navBarreClient.jsp" %>
    	
        <section style="margin-bottom:75px;">
	        <%@ include file="message.jsp" %>
	        <article class="container form-signin">
	        
		        <h1 style="font-family : Cursive;">MODIFICATION D'INFORMATIONS</h1>
		        
		        <form class="container form-signin" method="POST" action="MonCompte" name="FormulaireUtilisateur">
		            <label for="Nom">Nom Complet :</label>
		            <input value="${ sessionScope.UtilisateurActuel.nom }" type="text" name="Nom" id="Nom" placeholder="Charles Vane" required/><br/>
		            <span id="Nom_message"></span><br/>
		            
		            <label for="AdresseMail">E-mail :</label>
		            <input value="${ sessionScope.UtilisateurActuel.adresseMail }" type="text" name="AdresseMail" id="AdresseMail" placeholder="charlesvanee14@gmail.com"/><br/>
		            <span id="AdresseMail_message"></span><br/>
		            
		            <label for="NumeroTelephone">N° Telephone : </label>
		            <input value="${ sessionScope.UtilisateurActuel.numeroTelephone }" type="text" name="NumeroTelephone" id="NumeroTelephone" placeholder="+261386504586" maxLength="13" required/><br/>
		            <span id="NumeroTelephone_message"></span><br/>
		            
		            <label for="MotDePasse">Mot de passe : </label>
		            <input value="${ sessionScope.UtilisateurActuel.motDePasse }" type="password" name="MotDePasse" id="MotDePasse" required/> <i id="AfficherMdp" class="fas fa-eye-slash text-purple"></i><br/>
		            <span></span><br/>
		            
		            <label for="ConfirmationMdp">Confirmer votre mot de passe : </label>
		            <input value="${ sessionScope.UtilisateurActuel.motDePasse }" type="password" name="ConfirmationMdp" id="ConfirmationMdp" required/><br/>
		            <span id="MotDePasse_message"></span><br/>
		            
		            <div style="position:relative;top:10px;"><button type="submit" class="btn btn-primary btn-sm" name="BoutonUtilisateur" id="BoutonUtilisateur" value="Modifier"><i class='fas fa-edit'></i>Modifier<i style="position:relative;top:2px;right:1px" class="ni ni-single-02 text-black"></i></button>ou <a href="#ModalSupprimerUtilisateur" id="OuvrirModalSupprimerUtilisateur" class="btn btn-danger btn-sm" data-toggle="modal"><i class='fas fa-trash'></i> Supprimer <i style="position:relative;top:2px;right:1px" class="ni ni-single-02 text-white"></i></a><!--  -->
		        	</div>
		        	<%@ include file="ModalSupprimerUtilisateur.jsp" %>
		        	
		        </form>
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
        <script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/motDePasse.js"></script>
        <script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/validerUtilisateur.js"></script>
    </body>
</html>
