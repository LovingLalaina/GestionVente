
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AchatBean
{
    protected int id;
    protected int numeroUser;
    protected int numeroMateriel;
    protected int qte;
    protected String dateAchat;
    
    //CONSTRUCTEUR PAR DEFAUT
    public AchatBean()
    {
        this.id = 0;
        this.numeroUser = 0;
        this.numeroMateriel = 0;
        this.qte = 0;
        this.dateAchat = "";
    }
    
  //CONSTRUCTEUR PAR COPIE
    public AchatBean( AchatBean achat )
    {
        this.id = achat.id;
        this.numeroUser = achat.numeroUser;
        this.numeroMateriel = achat.numeroMateriel;
        this.qte = achat.qte;
        this.dateAchat = achat.dateAchat;
    }
    
    public String acheter( UtilisateurBean Client , HttpServletRequest request )
    {
    	HttpSession maSession = request.getSession();
    	
    	try
    	{
    		this.numeroUser = Client.getNumero();
    		this.numeroMateriel = Integer.parseInt( request.getParameter( "NumeroMateriel" ) );
    		this.qte = Integer.parseInt( request.getParameter( "QuantiteCache" ) );
    		this.dateAchat = Identification.securiserDonnee( request.getParameter( "DateAchat" ) );
    		String designMateriel = Identification.securiserDonnee( request.getParameter( "Design" ) );
    		
    		Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
        	ResultSet resultatSELECT = execution.executeQuery( "SELECT * FROM materiel WHERE num_materiel='" + this.numeroMateriel + "'" );
        	resultatSELECT.next();
    		final int stockInitial = resultatSELECT.getInt( "stock" );
    		
    		if( this.qte > stockInitial )
    		{
    			maSession.setAttribute( "message" , "Desolé, vous avez saisie une quantité supérieure à nos stocks, veuillez réessayer" );
                maSession.setAttribute( "typeMessage" , "danger" );
                return "echec";
    		}
    		
            execution.executeUpdate( "INSERT INTO achat ( num_user , num_materiel , qte , date_achat ) VALUES ( '" + this.numeroUser + "' , '" + this.numeroMateriel + "' , '" + this.qte + "' , '" + this.dateAchat + "' )" );
            final int stockFinal = stockInitial - this.qte;
            execution.executeUpdate( "UPDATE materiel SET stock='" + stockFinal + "' WHERE num_materiel='" + this.numeroMateriel + "'" );
            
            final int prixAchat = this.qte * Integer.parseInt( request.getParameter( "Pu" ) );
            maSession.setAttribute( "message" , "Merci pour votre achat " + Client.getNom() + " :)<br/>_Produit : " + designMateriel + "<br/>_Total : " + prixAchat + "Fmg (" + this.qte + ")");
            maSession.setAttribute( "typeMessage" , "success" );
            
    		return "succes";
    	}
    	catch( Exception e )
        {
        	request.setAttribute( "messageErreur" , e );
        	return "bug";
        }
    }
    
    public List<AchatBean> afficher( HttpServletRequest request )
    {
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            if( request.getParameter( "edit" ) != null || request.getParameter( "delete" ) != null )
            {
            	this.id = ( request.getParameter( "edit" ) != null ) ? Integer.parseInt( request.getParameter( "edit" ) ) : Integer.parseInt( request.getParameter( "delete" ) );
            	ResultSet resultatSELECT = execution.executeQuery( "SELECT * FROM achat WHERE id='" + this.id + "'" );
            	resultatSELECT.next();
                this.numeroUser = resultatSELECT.getInt( "num_user" );
                this.numeroMateriel = resultatSELECT.getInt( "num_materiel" );
                this.qte = resultatSELECT.getInt( "qte" );
                this.dateAchat = resultatSELECT.getString( "date_achat" );
                
            	request.setAttribute( "AchatActuel" , new AchatBean( this ) );
            }
            
            List<AchatBean> tableauAchat = new ArrayList<AchatBean>();
            
            ResultSet resultatSELECT = execution.executeQuery( "SELECT * FROM achat" );
            
            while( resultatSELECT.next() )
            {
                this.id = resultatSELECT.getInt( "id" );
                this.numeroUser = resultatSELECT.getInt( "num_user" );
                this.numeroMateriel = resultatSELECT.getInt( "num_materiel" );
                this.qte = resultatSELECT.getInt( "qte" );
                this.dateAchat = resultatSELECT.getString( "date_achat" );
                
                tableauAchat.add( new AchatBean( this ) );
            }
            
            return tableauAchat;
            
        }
        catch( Exception e )
        {
            return null;
        }
    }
    
    public String modifier( HttpServletRequest request )
    {
    	HttpSession maSession = request.getSession();
    	
    	try
        {
	    	this.id = Integer.parseInt( request.getParameter( "IdAchat" ) );
	    	this.qte = Integer.parseInt( request.getParameter( "Qte" ) );
	    	this.dateAchat = Identification.securiserDonnee( request.getParameter( "DateAchat" ) );
    	
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            ResultSet resultatSELECT = execution.executeQuery( "SELECT * FROM achat JOIN materiel ON achat.num_materiel=materiel.num_materiel WHERE id='" + this.id + "'" );
            resultatSELECT.next();
            AchatBean AncienAchat = new AchatBean();
            
            AncienAchat.id = this.id;
            AncienAchat.numeroUser = resultatSELECT.getInt( "num_user" );
            AncienAchat.numeroMateriel = resultatSELECT.getInt( "num_materiel" );
            AncienAchat.qte = resultatSELECT.getInt( "qte" );
            AncienAchat.dateAchat = resultatSELECT.getString( "date_achat" );
            
            int stockVirtuel = resultatSELECT.getInt( "stock" ) + AncienAchat.qte;
            
            if( this.qte >= stockVirtuel )
            {
            	maSession.setAttribute( "message" , "Echec de la modification, stock insuffisant !!! " );
                maSession.setAttribute( "typeMessage" , "danger" );
                return "echec";
            }
            
            int stockFinal = stockVirtuel - this.qte;
            
            execution.executeUpdate( "UPDATE materiel SET stock='" + stockFinal + "' WHERE num_materiel='" + AncienAchat.numeroMateriel + "'" );
            execution.executeUpdate( "UPDATE achat SET qte='" + this.qte + "' , date_achat='" + this.dateAchat + "' WHERE id='" + this.id + "'" );

        	maSession.setAttribute( "message" , "L'achat du materiel N° " + AncienAchat.numeroMateriel + " a bien été modifié" );
            maSession.setAttribute( "typeMessage" , "success" );
            
        	return "succes";
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
    	
    	try
        {
	    	this.id = Integer.parseInt( request.getParameter( "IdAchat" ) );
	    	
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            ResultSet resultatSELECT = execution.executeQuery( "SELECT * , materiel.num_materiel AS numeroMateriel FROM achat JOIN materiel ON achat.num_materiel=materiel.num_materiel WHERE id='" + this.id + "'" );
            resultatSELECT.next();
            
            this.numeroMateriel = resultatSELECT.getInt( "numeroMateriel" );
            this.qte = resultatSELECT.getInt( "qte" );
            int stockInitial = resultatSELECT.getInt( "stock" );
            
            int stockFinal = stockInitial + this.qte;
            
            
            execution.executeUpdate( "UPDATE materiel SET stock='" + stockFinal + "' WHERE num_materiel='" + this.numeroMateriel + "'" );
            execution.executeUpdate( "DELETE FROM achat WHERE id='" + this.id + "'" );
        	
        	maSession.setAttribute( "message" , "L'achat a bien été retiré" );
            maSession.setAttribute( "typeMessage" , "danger" );
            
        	return "succes";
        }
        catch( Exception e )
        {
        	request.setAttribute( "messageErreur" , e );
        	return "bug";
        }
    }
    
    // ACCESSEURS DU BEAN ACHAT
    public int getId()
    {
        return id;
    }
    
    public int getNumeroUser()
    {
        return numeroUser;
    }
    
    public int getNumeroMateriel()
    {
        return numeroMateriel;
    }
    
    public int getQte()
    {
        return qte;
    }
    
    public String getDateAchat()
    {
        return dateAchat;
    }
    
    public void setId( int id )
    {
        this.id = id;
    }
    
    public void setNumeroUser( int numeroUser )
    {
        this.numeroUser = numeroUser;
    }
    
    public void setNumeroMateriel( int numeroMateriel )
    {
        this.numeroMateriel = numeroMateriel;
    }
    
    public void setQte( int qte )
    {
        this.qte =  qte;
    }
    
    public void setDateAchat( String dateAchat )
    {
        this.dateAchat = dateAchat;
    }
}
