
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UtilisateurBean
{
    private int numero;
    private String nom;
    private String adresseMail;
    private String numeroTelephone;
    private String motDePasse;
    private String type;
    private int chiffreAffaire;
    
    //CONSTRUCTEUR PAR DEFAUT
    public UtilisateurBean()
    {
        this.numero = 0;
        this.nom = "";
        this.adresseMail = "";
        this.numeroTelephone = "";
        this.motDePasse = "";
        this.type = "client";
        this.chiffreAffaire = 0;
    }
    
    //CONSTRUCTEUR PAR COPIE
    public UtilisateurBean( UtilisateurBean utilisateur )
    {
        this.numero = utilisateur.numero;
        this.nom = utilisateur.nom;
        this.adresseMail = utilisateur.adresseMail;
        this.numeroTelephone = utilisateur.numeroTelephone;
        this.motDePasse = utilisateur.motDePasse;
        this.type = utilisateur.type;
        this.chiffreAffaire = utilisateur.chiffreAffaire;
    }
    
    //CONSTRUCTEUR POUR AUTHENTIFICATION
    public UtilisateurBean( String mail_ou_tel , String motDePasse )
    {
        this.numero = 0;
        this.nom = "";
        this.adresseMail = mail_ou_tel;
        this.numeroTelephone = mail_ou_tel;
        this.motDePasse = motDePasse;
        this.type = "";
        this.chiffreAffaire = 0;
    }
    
    public List<UtilisateurBean> afficher( HttpServletRequest request )
    {
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            List<UtilisateurBean> tableauUtilisateur = new ArrayList<UtilisateurBean>();

            String requeteSQL;
            
            if( request.getParameter( "Annee" ) != null )
            	requeteSQL = "SELECT * , sum(pu*qte) AS chiffre_affaire FROM utilisateur LEFT OUTER JOIN achat ON utilisateur.num_user=achat.num_user LEFT OUTER JOIN materiel ON achat.num_materiel=materiel.num_materiel WHERE year(date_achat)='" + request.getParameter( "Annee" ) + "' GROUP BY utilisateur.num_user ORDER BY chiffre_affaire DESC LIMIT 6";
            else
            	requeteSQL = "SELECT * , sum(pu*qte) AS chiffre_affaire FROM utilisateur LEFT OUTER JOIN achat ON utilisateur.num_user=achat.num_user LEFT OUTER JOIN materiel ON achat.num_materiel=materiel.num_materiel GROUP BY utilisateur.num_user ORDER BY chiffre_affaire DESC LIMIT 6";
            ResultSet resultatSELECT = execution.executeQuery( requeteSQL );
            
            while( resultatSELECT.next() )
            {
                this.numero = resultatSELECT.getInt( "num_user" );
                this.nom = resultatSELECT.getString( "nom" );
                this.adresseMail = resultatSELECT.getString( "adresse_mail" );
                this.numeroTelephone = resultatSELECT.getString( "num_telephone" );
                this.motDePasse = resultatSELECT.getString( "mot_de_passe" );
                this.type = resultatSELECT.getString( "type" );
                this.chiffreAffaire = resultatSELECT.getInt( "chiffre_affaire" );
                
                tableauUtilisateur.add( new UtilisateurBean( this ) );
            }
            
            return tableauUtilisateur;
            
        }
        catch( Exception e )
        {
            return null;
        }
    }
    
    public List<UtilisateurBean> rechercher( HttpServletRequest request )
    {
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            List<UtilisateurBean> tableauUtilisateur = new ArrayList<UtilisateurBean>();

            //A MODIFIER POUR AFFICHER LE CHIFFRE D'AFFAIRE
            String recherche = request.getParameter( "search" );
            String requeteSQL = "SELECT * , sum(pu*qte) AS chiffre_affaire FROM utilisateur LEFT OUTER JOIN achat ON utilisateur.num_user=achat.num_user LEFT OUTER JOIN materiel ON achat.num_materiel=materiel.num_materiel WHERE ( utilisateur.num_user LIKE '%" + recherche + "' OR utilisateur.num_user LIKE '" + recherche + "%' OR utilisateur.num_user LIKE '%" + recherche + "%' OR utilisateur.num_user='" + recherche + "' OR nom LIKE '%" + recherche + "' OR nom LIKE '" + recherche + "%' OR nom LIKE '%" + recherche + "%' OR nom='" + recherche + "' ) GROUP BY utilisateur.num_user LIMIT 100";
            ResultSet resultatSELECT = execution.executeQuery( requeteSQL );
            
            while( resultatSELECT.next() )
            {
                this.numero = resultatSELECT.getInt( "num_user" );
                this.nom = resultatSELECT.getString( "nom" );
                this.adresseMail = resultatSELECT.getString( "adresse_mail" );
                this.numeroTelephone = resultatSELECT.getString( "num_telephone" );
                this.motDePasse = resultatSELECT.getString( "mot_de_passe" );
                this.type = resultatSELECT.getString( "type" );
                this.chiffreAffaire = resultatSELECT.getInt( "chiffre_affaire" );
                
                tableauUtilisateur.add( new UtilisateurBean( this ) );
            }
            
            return tableauUtilisateur;
            
        }
        catch( Exception e )
        {
            return null;
        }
    }
    
    public String ajouter( HttpServletRequest request )
    {
    	HttpSession maSession = request.getSession();
    	
    	this.nom = Identification.securiserDonnee( request.getParameter( "Nom" ) );
        this.adresseMail = Identification.securiserDonnee( request.getParameter( "AdresseMail" ) );
        this.numeroTelephone = Identification.securiserDonnee( request.getParameter( "NumeroTelephone" ) );
        this.motDePasse = Identification.securiserDonnee( request.getParameter( "MotDePasse" ) );
        this.type = "client";
    	
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            String requeteSQL = "SELECT * FROM utilisateur WHERE ( adresse_mail='" + this.adresseMail + "' OR num_telephone='" + this.numeroTelephone + "')";
            ResultSet resultatSELECT = execution.executeQuery( requeteSQL );
            
            if( resultatSELECT.next() )
            {
                maSession.setAttribute( "message" , "Désolé, Adresse Mail ou N° Telephone déjà utilisés" );
                maSession.setAttribute( "typeMessage" , "warning" );
                return "/creationClient.jsp";
            }
            
            requeteSQL = "INSERT INTO utilisateur ( nom , adresse_mail , num_telephone , mot_de_passe , type ) VALUES ( '" + this.nom + "' , '" + this.adresseMail + "' , '" + this.numeroTelephone + "' , '" + this.motDePasse + "' , '" + this.type + "')";
            execution.executeUpdate( requeteSQL );
            
            resultatSELECT = execution.executeQuery( "SELECT num_user FROM utilisateur WHERE ( nom='" + this.nom + "' AND adresse_mail='" + this.adresseMail + "' AND num_telephone='" + this.numeroTelephone + "' AND mot_de_passe='" + this.motDePasse + "' AND type='" + this.type + "' )" );
            resultatSELECT.next();
            this.numero = resultatSELECT.getInt( "num_user" );
            
            maSession.setAttribute( "type" ,this.type );
            maSession.setAttribute( "UtilisateurActuel" , this );

            maSession.setAttribute( "message" , "Bienvenu " + this.nom + " :)" );
            maSession.setAttribute( "typeMessage" , "success" );

            return "/materiel.jsp";
        }
        catch( Exception e )
        {
        	request.setAttribute( "messageErreur" , e );
            return "/pageErreur.jsp"; 
        }
    }
    
    public String modifier( HttpServletRequest request )
    {
    	HttpSession maSession = request.getSession();
    	
    	UtilisateurBean UtilisateurActuel = new UtilisateurBean( (UtilisateurBean) maSession.getAttribute( "UtilisateurActuel" ) );
    	
    	this.numero = UtilisateurActuel.numero;
        this.nom = Identification.securiserDonnee(  request.getParameter( "Nom" ) );
        this.adresseMail = Identification.securiserDonnee(  request.getParameter( "AdresseMail" ) );
        this.numeroTelephone = Identification.securiserDonnee( request.getParameter( "NumeroTelephone" ) );
        this.motDePasse = Identification.securiserDonnee( request.getParameter( "MotDePasse" ) );
        this.type = UtilisateurActuel.type;
        
        try
        {
           Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            String requeteSQL = "SELECT * FROM utilisateur WHERE ( ( num_user!='" + this.numero + "' ) AND ( ( adresse_mail='" + this.adresseMail + "' ) OR ( num_telephone='" + this.numeroTelephone + "' ) ) )";
            ResultSet resultatSELECT = execution.executeQuery( requeteSQL );
            
            if( resultatSELECT.next() )
            {
                maSession.setAttribute( "message" , "Modification impossible, un autre utilisateur utilise déjà ces identifiants" );
                maSession.setAttribute( "typeMessage" , "danger");
                return "echec";
            }
            else
            {
                requeteSQL = "UPDATE utilisateur SET nom='" + this.nom + "' , adresse_mail='" + this.adresseMail + "' , num_telephone='" + this.numeroTelephone + "' , mot_de_passe='" + this.motDePasse + "' WHERE num_user='" + this.numero + "'";

                execution.executeUpdate( requeteSQL );
                
                maSession.setAttribute( "message" , "Les informations ont bien été bien modifiées" );
                maSession.setAttribute( "typeMessage" , "success");
                        
                maSession.setAttribute( "UtilisateurActuel" , this );
                
                return "succes";
            }
        }
        catch( Exception e )
        {
        	request.setAttribute( "messageErreur" , e );
        	return "bug";
        }
    }
  
    public String supprimer( HttpServletRequest request )
    {
    	HttpSession maSession = request.getSession();
    	
    	UtilisateurBean UtilisateurActuel = new UtilisateurBean( (UtilisateurBean) maSession.getAttribute( "UtilisateurActuel" ) );
    	
    	this.numero = UtilisateurActuel.numero;
        this.nom = UtilisateurActuel.nom;
        this.adresseMail = UtilisateurActuel.adresseMail;
        this.numeroTelephone = UtilisateurActuel.numeroTelephone;
        this.motDePasse = UtilisateurActuel.motDePasse;
        this.type = UtilisateurActuel.type;
        
        try
        {
           Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            execution.executeUpdate( "DELETE FROM utilisateur WHERE ( num_user='" + this.numero + "' )" );
            execution.executeUpdate( "DELETE FROM achat WHERE ( num_user='" + this.numero + "' )" );
            
            maSession.setAttribute( "message" , "Votre compte a bien été supprimé" );
            maSession.setAttribute( "typeMessage" , "danger" );
                        
            maSession.setAttribute( "UtilisateurActuel" , null );
            maSession.setAttribute( "type" , null );
            maSession.invalidate();
                
            return "succes";
        }
        catch( Exception e )
        {
        	request.setAttribute( "messageErreur" , e );
        	return "bug";
        }
    }
    
    // ACCESSEURS DU BEAN UTILISATEUR (CLIENT)
    public int getNumero()
    {
        return numero;
    }
    
    public String getNumeroChaine()
    {
    	String resultat = ( numero < 10 ) ? "0" + numero : String.valueOf( numero );
    	return "CL" + resultat;
    }
    
    public String getNom()
    {
        return nom;
    }
    
    public String getAdresseMail()
    {
        return adresseMail;
    }
    
    public String getNumeroTelephone()
    {
        return numeroTelephone;
    }
    
    public String getMotDePasse()
    {
        return motDePasse;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getChiffreAffaire()
    {
        return chiffreAffaire;
    }
    
    public void setNumero( int numero )
    {
        this.numero = numero;
    }
    
    public void setNom( String nom )
    {
        this.nom = nom;
    }
    
    public void setAdresseMail( String adresseMail )
    {
        this.adresseMail = adresseMail;
    }
    
    public void setNumeroTelephone( String numeroTelephone )
    {
        this.numeroTelephone =  numeroTelephone;
    }
    
    public void setMotDePasse( String motDePasse )
    {
        this.motDePasse = motDePasse;
    }
    
    public void setType( String type )
    {
        this.type = type;
    }
    
    public void setChiffreAffaire( int chiffreAffaire )
    {
        this.chiffreAffaire = chiffreAffaire;
    }
    
}
