
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/login.scss"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/couleurs.css"/>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/StyleS/footer.css"/>
        
    </head>
    <body class="couleur-blancCasse">
    
    	<section style="margin-top:0px;">
		    <%@ include file="message.jsp" %>
		    <div class="wrapper chemise couleur-blancCasse" style="margin-left:500px;margin-top:20px;padding:20px 20px;text-align:center; width:450px;"><br/>
		        <form method="POST" name="FormulaireUtilisateur" action="Authentification"  class="container form-signin" style="box-shadow: 0 5px 10px #a8a8a8; height:400px;">
		        	<br/>
		        	<h2 class="form-signin-heading">CONNEXION</h2><br/>
		        	
		            <div class="row">
	                    <div class="col-md-11"><input type="text" class="form-control" name="AdresseMail" placeholder="E-mail ou Tel" required/><br/></div>
	                    <div class="col-md-1" style="position:relative;top:10px;right:10px;"><i class="fa fa-user text-purple" onclick="document.FormulaireLogin.AdresseMail.focus();"></i></div>
	                </div>
		            
		            <div class="row">
	                    <div class="col-md-11"><input type="password" class="form-control" name="MotDePasse" placeholder="Mot de Passe" required/></div>
	                    <div class="col-md-1" style="position:relative;top:10px;right:10px;"><i id="AfficherMdp" class="fas fa-eye-slash text-purple"></i></div>
	                </div><br/><br/>
	                
	                <div class="row container">
	                	<div class="col-md-12"><button class="btn btn-lg btn-primary btn-block" type="submit" name="SeConnecter">Se connecter</button></div>
	                </div>
	                	ou
	                <div class="row">
		            	 <a href="${ pageContext.request.contextPath }/creationClient.jsp">S'inscrire</a>
		            </div>
		        </form>
		    </div>
	    </section>
	    
	    <script src="${ pageContext.request.contextPath }/ScriptS/motDePasse.js" ></script>
    </body>
</html>