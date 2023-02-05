
<div class="modal fade" id="ModalAjoutMateriel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background: #1687a7;">
                <h4 class="modal-title" id="myModalLabel" style="color:white;">AJOUT DE MATERIEL</h4>
            </div>
            <form method="POST" action="MaterielComplet" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="container-fluid">
                        <label for="Design" class="w3-label w3-left">Design du produit :</label>
                        <input type="text" name="Design" required="required" class="Design w3-input w3-border w3-round-large"  maxLength=24/>
                        <span class="Design_message"></span><br/>

                        <label for="Pu" class="w3-label w3-left">Prix Unitaire :</label>
                        <input type="number" min="0" name="Pu" required="required" class="Pu w3-input w3-border w3-round-large"/>
                        <span class="Pu_message"></span><br/>

                        <label for="Stock" class="w3-label w3-left">Stock :</label>
                        <input type="number" min="1" name="Stock" required="required" class="Stock w3-input w3-border w3-round-large"/>
                        <span class="Stock_message"></span><br/>
                        
                        <label for="Image" class="w3-label w3-left">Image :</label>
                        <input type="file" accept=".jpg,.png,.jpeg" name="Image" required="required" class="Image w3-input w3-border w3-round-large" id="Image"/><br/>
                        <p class="chemise alert alert-danger">ATTENTION !!!  Taille maximale de l'image : 10 Mo. Dépasser ce seuil risque de redémarrer le serveur</p><br/>
                        
                        <label for="Description" class="w3-label w3-left">Description :</label>
                        <textarea name="Description" class="Description w3-input w3-border w3-round-large"></textarea>
                        <span class="Description_message"></span><br/>
                        
                    </div>
                </div>
                <div class="modal-footer" style="background: #1687a7;">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Annuler</button>
                    <button type="submit" name="BoutonMateriel" class="BoutonMateriel btn btn-success" value="Ajouter"><i class="fas fa-plus text-white"></i> Ajouter</button>
                </div>
            </form>
        </div>
    </div>
</div>