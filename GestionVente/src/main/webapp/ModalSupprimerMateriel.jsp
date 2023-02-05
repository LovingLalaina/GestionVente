
<div class="modal fade" id="ModalSupprimerMateriel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background: #cd5d7d;">
                <h4 class="modal-title" id="myModalLabel" style="color: white;">SUPPRESSION DE PRODUIT</h4>
            </div>
            <form method="POST" action="MaterielComplet">
	            <div class="modal-body" style="background-color: white;">
	                <p class="text-center">Êtes-vous vraiment sûr de vouloir supprimer ce produit ainsi que tous ses achats effectués ??</p>
	                <h4 class="text-center">Materiel N° ${ MaterielActuel.numero } ( ${ MaterielActuel.design } )</h4>
	                <input type="hidden" name="NumeroMateriel" value="${ MaterielActuel.numero }"/>
	                <input type="hidden" name="DesignMateriel" value="${ MaterielActuel.design }"/>
	                <input type="hidden" name="UrlImage" value="${ MaterielActuel.urlImage }"/>
	            </div>
	            <div class="modal-footer" style="background: #cd5d7d;">
	            	<button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Annuler</button>
					<button type="submit" name="BoutonMateriel" id="BoutonSupprimerMateriel" class="btn btn-danger" value="Supprimer"><i class='fas fa-trash'></i> Supprimer</button>
	            </div>
            </form>
        </div>
    </div>
</div>

<!-- OUVERTURE DIRECTE DU MODAL ET A CHAQUE REACTUALISATION SI DELETE EST DONNEE -->
<a class="hidden" id="OuvrirModalSupprimerMateriel" href="#ModalSupprimerMateriel" data-toggle="modal">Ouvrir Modal Supprimer Materiel</a>

<script>
	window.addEventListener( "load", function()
	{
	    document.getElementById( "OuvrirModalSupprimerMateriel" ).click();
	});
	
</script>
