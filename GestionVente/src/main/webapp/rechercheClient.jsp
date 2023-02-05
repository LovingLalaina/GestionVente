
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row div-table-utilisateur">
	<table style="width:1000px;" class="table" id="TableUtilisateur">
	    <thead>
	        <tr>
	            <th>N° Client</th>
	            <th>Nom</th>
	            <th>Adresse Mail</th>
	            <th>N° Telephone</th>
	            <th>Chiffre d'affaire</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var="monUtilisateur" items="${ Utilisateurs }" >
	            <tr>
	                <td><c:out value="${ monUtilisateur.numeroChaine }"/></td>
	                <td><c:out value="${ monUtilisateur.nom }"/></td>
	                <td><c:out value="${ monUtilisateur.adresseMail }"/></td>
	                <td><c:out value="${ monUtilisateur.numeroTelephone }"/></td>
	                <td><c:out value="${ monUtilisateur.chiffreAffaire } Fmg"/></td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
</div>