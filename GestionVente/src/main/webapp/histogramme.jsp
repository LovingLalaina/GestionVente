<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="modele.UtilisateurBean" %>
<script src="${ pageContext.request.contextPath }/Ressources/ScriptS/chart.min.js"></script>

<div id="DivHistogramme" style="width:1000px;">
    <canvas id="Histogramme"></canvas>
</div>

<script>
	window.addEventListener( "load" , function( e )
	{
	    const histogramme = document.getElementById( "Histogramme" ).getContext('2d');
	    const COULEURS_TRANSPARENTES = [ "rgba( 255 , 99 , 132 , 0.2 )" , "rgba( 54 , 162 , 235 , 0.2 )" , "rgba( 255 , 206 , 86 , 0.2 )" , "rgba( 75 , 192 , 192 , 0.2 )" , "rgba( 153 , 102 , 255 , 0.2 )" , "rgba( 255 , 159 , 64 , 0.2 )" ];
	    const COULEURS = [ "rgba( 255 , 99 , 132 , 1 )" , "rgba( 54 , 162 , 235 , 1 )" , "rgba( 255 , 206 , 86 , 1 )" , "rgba( 75 , 192 , 192 , 1 )" , "rgba( 153 , 102 , 255 , 1 )" , "rgba( 255 , 159 , 64 , 1 )" ];
	    const monChart = new Chart( histogramme , {
	        type: "bar" ,
	        data: {
	            labels: nomClients ,
	            datasets: [{
	                label: "CHIFFRE D'AFFAIRE" ,
	                data: chiffreAffaire ,
	                backgroundColor: COULEURS_TRANSPARENTES ,
	                borderColor: COULEURS ,
	                borderWidth: 2
	            }]
	        },
	        options: {
	            scales: {
	                y: {
	                    beginAtZero: true
	                }
	            }
	        }
	    });
	});
</script>