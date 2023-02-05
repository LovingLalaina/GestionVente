<%@ page import="modele.Calendrier" %>
<%
	Calendrier aujourdhui = new Calendrier();
%>

<div class="modal fade" id="ModalConfirmerAchat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color:rgb( 0 , 157 , 255 );">
                <h4 class="modal-title" id="myModalLabel" style="color: maroon;font-family:cursive;font-style:bold;letter-spacing:4px;">CONFIRMATION D'ACHAT</h4>
            </div>
            <form method="POST" action="AchatMateriel" name="AchatMateriel">
            	<input name="NumeroMateriel" type="hidden" value="<c:out value="${ MaterielActuel.numero }"/>"/>
               	<input name="Design" type="hidden" value="<c:out value="${ MaterielActuel.design }"/>"/>
               	<input name="Pu" id="Pu" type="hidden" value="<c:out value="${ MaterielActuel.pu }"/>"/>
               	<input name="Stock" type="hidden" value="<c:out value="${ MaterielActuel.stock }"/>"/>
               	<input name="DateAchat" type="hidden" value="<% out.print( aujourdhui.afficherSurInput() ); %>"/>
               	<input name="QuantiteCache" id="QuantiteCache" type="hidden"/>
	            <div class="modal-body" style="background-color: white;">
	                <p class="text-center">Veuillez confirmer votre achat, s'il vous plait :</p>
	                <h4 class="text-center"><span class="Souligne">Produit :</span> ${ MaterielActuel.design }<br/><br/><span class="Souligne">Quantité :</span> <span id="SpanQuantite"></span><br/><br/><span class="Souligne">Prix Unitaire :</span>${ MaterielActuel.pu } Fmg<br/><br/><span class="Souligne">Total :</span><span id="SpanTotal"></span> Fmg</h4>
	            </div>
	            <div class="modal-footer" style="background-color:rgb( 0 , 157 , 255 );">
	            	<button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Annuler</button>
					<button type="submit" class="btn btn-success"><i class="fa fa-check"></i> Confirmer</button>
	            </div>
            </form>
        </div>
    </div>
</div>

<script>
	window.addEventListener( "load" , function()
	{
		var stock = ${ MaterielActuel.stock };
		document.getElementById( "BoutonAchat" ).addEventListener( "click" , function( e )
		{
			var inputQuantite = document.getElementById( "Quantite" );
			if( stock < inputQuantite.value )
			{
				e.preventDefault();
				alert( "Veuillez entrer une quantité inférieure au stock (<" + stock + ")" );
				window.history.back();
			}
			else
			{
				document.getElementById( "QuantiteCache" ).value = document.getElementById( "SpanQuantite" ).innerHTML = inputQuantite.value;
				document.getElementById( "SpanTotal" ).innerHTML = parseInt( inputQuantite.value ) * parseInt( document.getElementById( "Pu" ).value );
			}
		});
	});
</script>
