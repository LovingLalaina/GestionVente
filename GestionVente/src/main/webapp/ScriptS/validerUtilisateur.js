
var nom = document.getElementById( "Nom" );
var adresseMail = document.getElementById( "AdresseMail" );
var numeroTelephone = document.getElementById( "NumeroTelephone" );
var motDePasse = document.getElementById( "MotDePasse" );
var confirmationMdp = document.getElementById( "ConfirmationMdp" );

var nom_message = document.getElementById( "Nom_message" );
var adresseMail_message = document.getElementById( "AdresseMail_message" );
var numeroTelephone_message = document.getElementById( "NumeroTelephone_message" );
var motDePasse_message = document.getElementById( "MotDePasse_message" );

var adresseMail_regex = /gmail.com$/;
var numeroTelephone_regex = /[+0-9]{13}/;

var boutonUtilisateur = document.getElementById( "BoutonUtilisateur" );

boutonUtilisateur.addEventListener( "click" , function( evenement )
{
	if( nom.value == "" )
    {
        evenement.preventDefault();
        nom_message.innerHTML = "Nom d'utilisateur manquant !!!";
        nom_message.style.color = "red";
        nom.focus();
        
    }
    else
    {
        nom_message.innerHTML = "";
        
        if( adresseMail.value == "" )
        {
            evenement.preventDefault();
            adresseMail_message.innerHTML = "Adresse E-Mail manquant !!!";
            adresseMail_message.style.color = "red";
            adresseMail.focus();
        }
        else if( adresseMail_regex.test( adresseMail.value ) === false )
        {
            evenement.preventDefault();
            adresseMail_message.innerHTML = "Format de l'adresse incorrect !!!";
            adresseMail_message.style.color = "orange";
            adresseMail.focus();
        }
        else
        {
            adresseMail_message.innerHTML = "";
            
            if( numeroTelephone.value == "" )
            {
                evenement.preventDefault();
                numeroTelephone_message.innerHTML = "Numero Telephone manquant !!!";
                numeroTelephone_message.style.color = "red";
                numeroTelephone.focus();
            }
            else if( numeroTelephone_regex.test( numeroTelephone.value ) == false )
            {
                evenement.preventDefault();
                numeroTelephone_message.innerHTML = "Format du numero telephone incorrect !!!";
                numeroTelephone_message.style.color = "orange";
                numeroTelephone.focus();
            }
            else
            {
                numeroTelephone_message.innerHTML = "";
                
                if( motDePasse.value == "" || confirmationMdp.value == "" )
	            {
	                evenement.preventDefault();
	                motDePasse_message.innerHTML = "Mot(s) de passe manquant(s) !!!";
	                motDePasse_message.style.color = "red";
	                motDePasse.focus();
	            }
	            else if( motDePasse.value != confirmationMdp.value )
	            {
					evenement.preventDefault();
                    motDePasse_message.innerHTML = "Mots de passe non correspondants !!!";
                    motDePasse_message.style.color = "red";
                    confirmationMdp.focus();
				}
				else motDePasse_message.innerHTML = "";
                
            }
        }
    }
});
