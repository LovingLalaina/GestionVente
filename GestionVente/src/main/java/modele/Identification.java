package modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.*;

public class Identification
{
	public String identifier( String uploadPath , HttpServletRequest request )
	{
		HttpSession maSession = request.getSession();
		
        if( maSession.getAttribute( "UtilisateurActuel" ) != null )
        {
            maSession.setAttribute( "message" , "Compte déjà connecté pour ce navigateur" );
            maSession.setAttribute( "typeMessage" , "warning" );
            
            request.setAttribute( "MaterielComplet" , new MaterielBean( uploadPath ).afficher( request ) );
            
            if( maSession.getAttribute("type").equals( "client" ) )
            	//ECHEC DU LOGIN, UNE SESSION A DEJA ETE OUVERTE SUR LE NAVIGATEUR ET CE FUT UN CLIENT
            	return "/materiel.jsp";
            //ECHEC DU LOGIN, UNE SESSION A DEJA ETE OUVERTE SUR LE NAVIGATEUR ET CE FUT UN ADMINISTRATEUR
            return "/materielComplet.jsp";
            
        }
        
        //AUCUN AUTRE UTILISATEUR N'ETAIT CONNECTE AU NAVIGATEUR , VRAI AUTHENTIFICATION 
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();

            UtilisateurBean monUtilisateur = new UtilisateurBean( request.getParameter( "AdresseMail" ) , request.getParameter( "MotDePasse" ) );
            
            String requeteSQL = "SELECT * FROM utilisateur WHERE ( ( adresse_mail='" + monUtilisateur.getAdresseMail() + "') OR (num_telephone='" + monUtilisateur.getNumeroTelephone() + "') )";
            ResultSet resultatSELECT = execution.executeQuery( requeteSQL );

            if( !resultatSELECT.next() )
            {
            	//ECHEC DU LOGIN, L'UTILISATEUR N'EXISTE PAS
                maSession.setAttribute( "message" , "L'utilisateur entré n'existe pas" );
                maSession.setAttribute( "typeMessage" , "danger" );
                return "/index.jsp"; 
            }
            
            requeteSQL = "SELECT * FROM utilisateur WHERE ( ( ( adresse_mail='" + monUtilisateur.getAdresseMail() + "') OR (num_telephone='" + monUtilisateur.getNumeroTelephone() + "')) AND (mot_de_passe='" + monUtilisateur.getMotDePasse() + "') )";
            resultatSELECT = execution.executeQuery( requeteSQL );

            if( !resultatSELECT.next() )
            {
            	//ECHEC DU LOGIN, L'UTILISATEUR EXISTE MAIS MOT DE PASSE INCORRECTE
                maSession.setAttribute( "message" , "Le mot de passe est incorrect");
                maSession.setAttribute( "typeMessage" , "danger");
                return "/index.jsp";
                
            }

            //LOGIN REUSSI, MAIS ON NE CONNAIT PAS ENCORE TOUS LES INFORMATIONS COMME LE TYPE D'UTILISATEUR, PAR EXEMPLE
            monUtilisateur.setNumero( resultatSELECT.getInt( "num_user" ) );
            monUtilisateur.setNom( resultatSELECT.getString( "nom" ) );
            monUtilisateur.setAdresseMail( resultatSELECT.getString( "adresse_mail" ) );
            monUtilisateur.setNumeroTelephone( resultatSELECT.getString( "num_telephone" ) );
            monUtilisateur.setType( resultatSELECT.getString( "type" ) );
            
            maSession.setAttribute( "message" , "Bienvenu " + monUtilisateur.getNom() );
            maSession.setAttribute( "typeMessage" , "success");
            
            maSession.setAttribute( "type" , monUtilisateur.getType() );
            maSession.setAttribute( "UtilisateurActuel" , monUtilisateur );

            request.setAttribute( "MaterielComplet" , new MaterielBean().afficher( request ) );
            
            if( monUtilisateur.getType().equals( "client" ) )
            	//LOGIN REUSSI, C'EST UN CLIENT
                return "/materiel.jsp";
        	//LOGIN REUSSI , C'EST UN ADMINISTRATEUR
        	return "/materielComplet.jsp";
        }
        catch( CommunicationsException a )
        {
        	request.setAttribute( "messageErreur" , "Accès à la base de données refusée : " + a );
        	return "/pageErreur.jsp";
            //BASE DE DONNEES NON ACTIVEE :(
        }
        catch( Exception e )
        {
        	request.setAttribute( "messageErreur" , e );
        	return "/pageErreur.jsp";
            //GROS BUG DE SQL, DRIVER OU SYNTAXIQUE :(
        }
	}

	public String verifierSession( String uploadPath , HttpServletRequest request )
	{
		HttpSession maSession = request.getSession();
		
        if( maSession.getAttribute( "type" ) != null )
        {
        	request.setAttribute( "MaterielComplet" , new MaterielBean( uploadPath ).afficher( request ) );
        	
            if( maSession.getAttribute( "type" ).equals( "client" ) )	return "/materiel.jsp";
            return "/materielComplet.jsp";
        }
        
        return "/index.jsp";
	}
	
	public static String securiserDonnee( String donnee )
	{

		String resultat = donnee.trim();
		resultat = resultat.strip();
		resultat = resultat.replace( "'" , "\\'");
		resultat = resultat.replace( "\"" , "\"");
		return resultat;
	}
}
