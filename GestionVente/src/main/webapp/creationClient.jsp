
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
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/login.scss"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/couleurs.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/footer.css"/>
        <style>
        	label , input
        	{
        		font-family : cursive;
        	}
        </style>
    </head>
    
    <body class="couleur-blancCasse">
    
    	<section>
	        <%@ include file="message.jsp" %>
	        
	        <div class="wrapper chemise couleur-blancCasse" style="margin-left:450px;padding:10px 10px;text-align:center; width:500px;"><br/>
		        <form method="POST" name="FormulaireUtilisateur" action="Client"  class="container form-signin" style="box-shadow: 0 5px 10px #a8a8a8;height:450px;">
		        	<br/>
		        	
		            <label for="Nom">Nom Complet :</label>
		            <input type="text" name="Nom" id="Nom" placeholder="Charles Vane" required/><br/>
		            <span id="Nom_message"></span><br/>
		            
		            <label for="AdresseMail">E-mail :</label>
		            <input type="text" name="AdresseMail" id="AdresseMail" placeholder="charlesvanee14@gmail.com" required/><br/>
		            <span id="AdresseMail_message"></span><br/>
		            
		            <label for="NumeroTelephone">N° Telephone :  </label>
		            <input type="text" name="NumeroTelephone" id="NumeroTelephone" placeholder="+261386504586" maxLength="13"/><br/>
		            <span id="NumeroTelephone_message"></span><br/>
		            
		            <label for="MotDePasse">Mot de passe : </label>
		            <input type="password" name="MotDePasse" id="MotDePasse" required/> <i id="AfficherMdp" class="fas fa-eye-slash text-purple"></i><br/>
		            <span></span><br/>
		            
		            <label for="ConfirmationMdp">Confirmation : </label>
		            <input type="password" name="ConfirmationMdp" id="ConfirmationMdp" required/><br/>
		            <span id="MotDePasse_message"></span><br/>
		            
		            <input type="submit" name="SInscrire" id="BoutonUtilisateur" class="btn btn-lg btn-success" value="S'inscrire"/> ou <a href="/HighTech_Market/index.jsp">Se Connecter</a>
	
		        </form>
	        </div>
        </section>
        
        <script src="${ pageContext.request.contextPath }/ScriptS/motDePasse.js" ></script>
        <script type="text/javascript" src="${ pageContext.request.contextPath }/ScriptS/validerUtilisateur.js"></script>
    </body>
</html>
