
<div class="modal fade" id="ModalSupprimerAchat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background: #cd5d7d;">
                <h4 class="modal-title" id="myModalLabel" style="color: white;">SUPPRESSION D'ACHAT</h4>
            </div>
            <form method="POST" action="Achat">
	            <div class="modal-body" style="background-color: white;">
	                <p class="text-center">Êtes-vous vraiment sûr de vouloir supprimer cet achat ??</p>
	                <h4 class="text-center">Materiel N° ${ AchatActuel.numeroMateriel } ( Client N° ${ AchatActuel.numeroUser } )</h4>
	                <input type="hidden" name="IdAchat" value="${ AchatActuel.id }"/>
	            </div>
	            <div class="modal-footer" style="background: #cd5d7d;">
	            	<button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Annuler</button>
					<button type="submit" name="BoutonAchat" class="BoutonAchat btn btn-danger" value="Supprimer"><i class='fas fa-trash'></i> Supprimer</button>
	            </div>
            </form>
        </div>
    </div>
</div>

<!-- OUVERTURE DIRECTE DU MODAL ET A CHAQUE REACTUALISATION SI DELETE EST DONNEE -->
<a class="hidden" id="OuvrirModalSupprimerAchat" href="#ModalSupprimerAchat" data-toggle="modal">Ouvrir Modal Supprimer Achat</a>

<script>
	window.addEventListener( "load", function()
	{
	    document.getElementById( "OuvrirModalSupprimerAchat" ).click();
	});
	
</script>
