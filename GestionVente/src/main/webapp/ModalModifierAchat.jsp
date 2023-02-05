
<div class="modal fade" id="ModalModifierAchat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background: #1687a7;">
                <h4 class="modal-title" id="myModalLabel" style="color:white;">MODIFICATION D'ACHAT</h4>
            </div>
            <form method="POST" action="Achat">
                <div class="modal-body">
                    <div class="container-fluid">
                    	<input value="${ AchatActuel.id }" type="hidden" name="IdAchat"/>
                        <label for="Qte" class="w3-label w3-left">Quantité achetée :</label>
                        <input value="${ AchatActuel.qte }" type="number" min="1" name="Qte" required="required" class="Qte w3-input w3-border w3-round-large"/>
                        <span class="Qte_message"></span><br/>

                        <label for="DateAchat" class="w3-label w3-left">Date d'achat :</label>
                        <input value="${ AchatActuel.dateAchat }" type="date" name="DateAchat" required="required" class="DateAchat w3-input w3-border w3-round-large"/>
                    </div> 
                </div>
                <div class="modal-footer" style="background: #1687a7;">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Annuler</button>
                    <button type="submit" name="BoutonAchat" class="BoutonAchat btn btn-warning" value="Modifier"><i class='fas fa-edit'></i> Modifier</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- OUVERTURE DIRECTE DU MODAL ET A CHAQUE REACTUALISATION SI EDIT EST DONNEE -->
<a class="hidden" id="OuvrirModalModifierAchat" href="#ModalModifierAchat" data-toggle="modal">Ouvrir Modal Modifier Achat</a>

<script>
	window.addEventListener( "load", function()
	{
	    document.getElementById( "OuvrirModalModifierAchat" ).click();
	});
	
</script>

