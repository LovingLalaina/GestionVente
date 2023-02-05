
<div class="modal fade" id="ModalSupprimerUtilisateur" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background: #cd5d7d;">
                <h4 class="modal-title" id="myModalLabel" style="color: white;">SUPPRESSION DE COMPTE</h4>
            </div>
                <div class="modal-body" style="background-color: white;">
                    <p class="text-center">Êtes-vous vraiment sûr de vouloir nous quitter ?? :(</p>
                    <h4 class="text-center">${ sessionScope.UtilisateurActuel.nom } , vous allez nous manquer !!</h4>
                </div>
                <div class="modal-footer" style="background: #cd5d7d;">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Annuler</button>
				<button type="submit" name="BoutonUtilisateur" id="BoutonSupprimerClient" class="btn btn-danger" value="Supprimer"><i class="fas fa-user-times"></i> Oui</button>
                </div>
        </div>
    </div>
</div>