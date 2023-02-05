
$( document ).ready( function() 
{
    $( "#RechercheMateriel" ).keyup( function()
    {
		var resultatRecherche = $(this).val();
        
        if( resultatRecherche != "" )
        {
			document.getElementById( "Footer" ).className = "fixed-footer";
            $.ajax({
                url: "RechercherMateriel" ,
                method: "GET",
                data: "search=" + $( this ).val(),
                success: function( data )
                {
                    $(".div-table-materiel").html( data );
                }
            });
        }
        else
        {
			document.getElementById( "Footer" ).removeAttribute( "class" );
            $(".div-table-materiel").html( "" );
            $.ajax({
                url: "RechercherMateriel" ,
                method: "GET",
                data: "search=" + $( this ).val(),
                success: function( data )
                {
                    $(".div-table-materiel").html( data );
                }
            });
        }
    });
});
