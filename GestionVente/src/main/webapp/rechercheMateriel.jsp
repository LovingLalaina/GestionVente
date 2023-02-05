
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	                     <a href="MaterielComplet?edit=<c:out value="${ monMateriel.numero }"/>" class="btn btn-info btn-sm"><i class='fas fa-edit'></i>Modifier</a>
	                     <a href="MaterielComplet?delete=<c:out value="${ monMateriel.numero }"/>" class="btn btn-danger btn-sm"><i class='fas fa-trash'></i>Supprimer</a>
	                 </td>
	             </tr>
	         </c:forEach>
	     </tbody>
	 </table>
</div>