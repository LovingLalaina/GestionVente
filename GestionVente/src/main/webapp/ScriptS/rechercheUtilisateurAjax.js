
$( document ).ready( function() 
{
    $( "#RechercheUtilisateur" ).keyup( function()
    {
		document.getElementById( "Footer" ).className = "fixed-footer";
		var resultatRecherche = $(this).val();
        if( resultatRecherche != "" )
        {
            $.ajax({
                url: "RechercherUtilisateur" ,
                method: "GET",
                data: "search=" + $( this ).val(),
                success: function( data )
                {
                    $(".div-table-utilisateur").html( data );
                }
            });

        }
        else
        {
			document.getElementById( "Footer" ).removeAttribute( "class" );
            $(".div-table-utilisateur").html( "" );
            $.ajax({
                url: "RechercherUtilisateur" ,
                method: "GET",
                data: "search=" + $( this ).val(),
                success: function( data )
                {
                    $(".div-table-utilisateur").html( data );
                }
            });
        }
    });
});
