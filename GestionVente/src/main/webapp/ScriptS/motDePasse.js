
document.getElementById( "AfficherMdp" ).addEventListener( "click" , function()
{
    document.FormulaireUtilisateur.MotDePasse.focus();
    if( this.className == "fas fa-eye-slash text-purple" )
    {
        document.FormulaireUtilisateur.MotDePasse.type = "text";
        this.className = "fas fa-eye text-purple";

        setTimeout( function()
        {
            document.FormulaireUtilisateur.MotDePasse.type = "password";
            document.getElementById( "AfficherMdp" ).className = "fas fa-eye-slash text-purple";
        } , 500 )
    }
    else
    {
        document.FormulaireUtilisateur.MotDePasse.type = "password";
        this.className = "fas fa-eye-slash text-purple";
    }
});
        