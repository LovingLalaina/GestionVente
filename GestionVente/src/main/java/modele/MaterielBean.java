
package modele;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class MaterielBean
{
    protected int numero;
    protected String design;
    protected int pu;
    protected int stock;
    protected int totalVendu;
    protected Part image;
    protected String nomImage;
    protected String urlImage;//Adresse Web pour acceder a l'image
    protected String cheminImage = "";//Chemin du dossier ou se trouve l'image
    protected String description;

    private static final int TAILLE_TAMPON = 10240;
    
    //CONSTRUCTEUR PAR DEFAUT
    public MaterielBean()
    {
        this.numero = 0;
        this.design = "Indefini";
        this.pu = 0;
        this.stock = 0;
        this.totalVendu = 0;
        this.image = null;
        this.nomImage = "";
        this.urlImage = "";
        this.cheminImage = "";
        this.description = "(Pas de description)";
    }
    
    //CONSTRUCTEUR POUR INITIALISER LE CHEMIN DU DOSSIER OU STOCKER L'IMAGE
    public MaterielBean( String cheminImage )
    {
        this.numero = 0;
        this.design = "Indefini";
        this.pu = 0;
        this.stock = 0;
        this.totalVendu = 0;
        this.image = null;
        this.nomImage = "";
        this.urlImage = "";
        this.cheminImage = cheminImage;
        this.description = "(Pas de description)";
    }
    
    //CONSTRUCTEUR PAR COPIE
    public MaterielBean( MaterielBean materiel )
    {
    	this.numero = materiel.numero;
        this.design = materiel.design;
        this.pu = materiel.pu;
        this.stock = materiel.stock;
        this.totalVendu = materiel.totalVendu;
        this.image = materiel.image;
        this.nomImage = materiel.nomImage;
        this.urlImage = materiel.urlImage;
        this.cheminImage = materiel.cheminImage;
        this.description = materiel.description;
    }
    
    public MaterielBean( HttpServletRequest request , int numeroMateriel )
    {
    	try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            ResultSet resultatSELECT = execution.executeQuery( "SELECT * FROM materiel WHERE num_materiel='" + numeroMateriel + "'" );
        	resultatSELECT.next();
        	this.numero = numeroMateriel;
            this.design = resultatSELECT.getString( "design" );
            this.pu = resultatSELECT.getInt( "pu" );
            this.stock = resultatSELECT.getInt( "stock" );
            this.nomImage = resultatSELECT.getString( "url_image" );
            String adresse = new String( request.getRequestURL() );
            this.urlImage = adresse.substring( 0 , adresse.lastIndexOf( "/" ) ) + "/BaseDeDonnees/ImageS/Materiel/" + this.nomImage;
            
            this.description = resultatSELECT.getString( "description" );
        }
        catch( Exception e )
        {
        	this.numero = 0;
            this.design = "Indefini";
            this.pu = 0;
            this.stock = 0;
            this.totalVendu = 0;
            this.image = null;
            this.nomImage = "";
            this.urlImage = "";
            this.cheminImage = "";
            this.description = "(Pas de description)";
        }
    }
    
    public List<MaterielBean> afficher( HttpServletRequest request )
    {
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            ResultSet resultatSELECT;
            
            if( request.getParameter( "edit" ) != null || request.getParameter( "delete" ) != null )
            {
            	this.numero = ( request.getParameter( "edit" ) != null ) ? Integer.parseInt( request.getParameter( "edit" ) ) : Integer.parseInt( request.getParameter( "delete" ) );
            	resultatSELECT = execution.executeQuery( "SELECT * FROM materiel WHERE num_materiel='" + this.numero + "'" );
            	resultatSELECT.next();
                this.design = resultatSELECT.getString( "design" );
                this.pu = resultatSELECT.getInt( "pu" );
                this.stock = resultatSELECT.getInt( "stock" );
                this.nomImage = resultatSELECT.getString( "url_image" );
                String adresse = new String( request.getRequestURL() );
                this.urlImage = adresse.substring( 0 , adresse.lastIndexOf( "/" ) ) + "/BaseDeDonnees/ImageS/Materiel/" + this.nomImage;
                this.description = resultatSELECT.getString( "description" );
            	request.setAttribute( "MaterielActuel" , new MaterielBean( this ) );
            }
            
            List<MaterielBean> tableauMateriel = new ArrayList<MaterielBean>();

            resultatSELECT = execution.executeQuery( "SELECT *, sum(pu*qte) AS total_vendu FROM materiel LEFT OUTER JOIN achat ON materiel.num_materiel=achat.num_materiel GROUP BY materiel.num_materiel" );
            
            while( resultatSELECT.next() )
            {
                this.numero = resultatSELECT.getInt( "num_materiel" );
                this.design = resultatSELECT.getString( "design" );
                this.pu = resultatSELECT.getInt( "pu" );
                this.stock = resultatSELECT.getInt( "stock" );
                this.totalVendu = resultatSELECT.getInt( "total_vendu" );
                this.description = resultatSELECT.getString( "description" );
                this.nomImage = resultatSELECT.getString( "url_image" );
                String adresse = new String( request.getRequestURL() );
                this.urlImage = adresse.substring( 0 , adresse.lastIndexOf( "/" ) ) + "/BaseDeDonnees/ImageS/Materiel/" + this.nomImage;
                tableauMateriel.add( new MaterielBean( this ) );
            }
            
            return tableauMateriel;
            
        }
        catch( Exception e )
        {
            return null;
        }
    }
    
    public List<MaterielBean> rechercher( HttpServletRequest request )
    {
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            List<MaterielBean> tableauMateriel = new ArrayList<MaterielBean>();

            String recherche = request.getParameter( "search" );
            String requeteSQL = "SELECT *, sum(pu*qte) AS total_vendu FROM materiel LEFT OUTER JOIN achat ON materiel.num_materiel=achat.num_materiel  WHERE ( materiel.num_materiel LIKE '%" + recherche + "' OR materiel.num_materiel LIKE '" + recherche + "%' OR materiel.num_materiel LIKE '%" + recherche + "%' OR materiel.num_materiel='" + recherche + "' OR design LIKE '%" + recherche + "' OR design LIKE '" + recherche + "%' OR design LIKE '%" + recherche + "%' OR design='" + recherche + "' ) GROUP BY materiel.num_materiel LIMIT 100";
            ResultSet resultatSELECT = execution.executeQuery( requeteSQL );
            
            while( resultatSELECT.next() )
            {
            	this.numero = resultatSELECT.getInt( "num_materiel" );
                this.design = resultatSELECT.getString( "design" );
                this.pu = resultatSELECT.getInt( "pu" );
                this.stock = resultatSELECT.getInt( "stock" );
                this.totalVendu = resultatSELECT.getInt( "total_vendu" );
                this.nomImage = resultatSELECT.getString( "url_image" );
                String adresse = new String( request.getRequestURL() );
                this.urlImage = adresse.substring( 0 , adresse.lastIndexOf( "/" ) ) + "/BaseDeDonnees/ImageS/Materiel/" + this.nomImage;
                this.description = resultatSELECT.getString( "description" );
                tableauMateriel.add( new MaterielBean( this ) );
            }
            
            return tableauMateriel;
            
        }
        catch( Exception e )
        {
            return null;
        }
    }
    
    public String ajouter( HttpServletRequest request )
    {
    	try
        {
	    	HttpSession maSession = request.getSession();
	    	
	    	this.design = Identification.securiserDonnee( request.getParameter( "Design" ) );
	    	this.pu = Integer.parseInt( request.getParameter( "Pu" ) );
	    	this.stock = Integer.parseInt( request.getParameter( "Stock" ) );
	    	this.description = Identification.securiserDonnee( request.getParameter( "Description" ) );
	    	this.image = request.getPart( "Image" );
	    	this.nomImage = getNomImage( image );
	    	
	    	//POUR INTERNET EXPLORER ET MICROSOFT EDGE
	    	if( this.nomImage != null && !this.nomImage.isEmpty() )
	    		this.nomImage = this.nomImage.substring( this.nomImage.lastIndexOf("/") + 1 ).substring( this.nomImage.lastIndexOf( "\\" ) + 1 );
	    		
	    	
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            ResultSet resultatSELECT = execution.executeQuery( "SELECT * FROM materiel WHERE (design='" + this.design + "')" );
            
            if( resultatSELECT.next() )
            {
            	maSession.setAttribute( "message" , "Désolé, Ce nom de matériel est déjà utilisé, veuillez réessayer" );
                maSession.setAttribute( "typeMessage" , "warning" );
                return "echec";
            }
            
            if( this.nomImage == null || this.nomImage.equals("") )
            {
            	maSession.setAttribute( "message" , "Veuillez donner un fichier valide pour le materiel et de taille < 10Mo (.png .jpg ou .jpeg)" );
                maSession.setAttribute( "typeMessage" , "danger" );
                return "echec";
            }
            
            resultatSELECT = execution.executeQuery( "SELECT * FROM materiel WHERE (url_image='" + nomImage + "')" );
            
            if( resultatSELECT.next() )
            {
            	maSession.setAttribute( "message" , "Le nom du fichier est déjà utilisé, veuillez en choisir un autre ou le renommer" );
                maSession.setAttribute( "typeMessage" , "danger" );
                return "echec";
            }
            else
            {
            	if( importerImage( this.image , this.nomImage , request ).equals( "bug" ) )
                	return "bug";
                
                String requeteSQL = "INSERT INTO materiel ( design , pu , stock , description , url_image ) VALUES ( '" + this.design + "' , '" + this.pu + "' , '" + this.stock + "' , '" + this.description + "' , '" + this.nomImage + "' )";
            	execution.executeUpdate( requeteSQL );
            	
            	maSession.setAttribute( "message" , "Le materiel " + this.design + " a bien été ajouté" );
                maSession.setAttribute( "typeMessage" , "success" );
                
            	return "succes";
            }
        }
        catch( Exception e )
        {
        	request.setAttribute( "messageErreur" , e );
        	return "bug";
        }
    }
    
    public String modifier( HttpServletRequest request )
    {
    	HttpSession maSession = request.getSession();
    	
    	try
        {
	    	this.numero = Integer.parseInt( request.getParameter( "NumeroMateriel" ) );
	    	this.design = Identification.securiserDonnee( request.getParameter( "Design" ) );
	    	this.pu = Integer.parseInt( request.getParameter( "Pu" ) );
	    	this.stock = Integer.parseInt( request.getParameter( "Stock" ) );
	    	this.description = Identification.securiserDonnee( request.getParameter( "Description" ) );
	    	this.image = request.getPart( "Image" );
	    	this.nomImage = getNomImage( image );
	    	
	    	//POUR INTERNET EXPLORER ET MICROSOFT EDGE
	    	if( this.nomImage != null && !this.nomImage.isEmpty() )
	    		this.nomImage = this.nomImage.substring( this.nomImage.lastIndexOf("/") + 1 ).substring( this.nomImage.lastIndexOf( "\\" ) + 1 );
	    	
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            ResultSet resultatSELECT = execution.executeQuery( "SELECT * FROM materiel WHERE ( ( design='" + this.design + "' OR url_image='" + this.nomImage + "' ) AND num_materiel!='" + this.numero + "')" );
            
            if( resultatSELECT.next() )
            {
            	maSession.setAttribute( "message" , "Désolé, Ce nom de matériel est déjà utilisé par un autre produit, veuillez réessayer" );
                maSession.setAttribute( "typeMessage" , "warning" );
                return "echec";
            }
            String requeteSQL;
            
            if( this.nomImage != null && !this.nomImage.equals("") )
            {
            	
            	resultatSELECT = execution.executeQuery( "SELECT * FROM materiel WHERE num_materiel='" + this.numero + "'" );
            	resultatSELECT.next();
            	String ancienNomImage = resultatSELECT.getString( "url_image" );
            	File monImage = new File( cheminImage + ancienNomImage );
            	monImage.delete();
            	
            	if( importerImage( this.image , this.urlImage , request ).equals( "bug" ) )
                	return "bug";
            	
            	requeteSQL = "UPDATE materiel SET design='" + this.design + "' , pu='" + this.pu + "' , stock='" + this.stock + "' , url_image='" + this.nomImage + "' , description='" + this.description + "' WHERE num_materiel='" + this.numero + "'";
            }
            else
            	requeteSQL = "UPDATE materiel SET design='" + this.design + "' , pu='" + this.pu + "' , stock='" + this.stock + "' , description='" + this.description + "' WHERE num_materiel='" + this.numero + "'";
            
            execution.executeUpdate( requeteSQL );
            	
        	maSession.setAttribute( "message" , "Le materiel N° " + this.numero + " (" + this.design + ") a bien été modifié" );
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
	    	this.numero = Integer.parseInt( request.getParameter( "NumeroMateriel" ) );
	    	this.design = request.getParameter( "DesignMateriel" );
	    	this.urlImage = request.getParameter( "UrlImage" );
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            
            File monImage = new File( cheminImage + this.nomImage );
            monImage.delete();
            
            execution.executeUpdate( "DELETE FROM materiel WHERE num_materiel='" + this.numero + "'" );
            execution.executeUpdate( "DELETE FROM achat WHERE num_materiel='" + this.numero + "'" );
        	
        	maSession.setAttribute( "message" , "Le materiel N° " + this.numero + " (" + this.design + ") a bien été supprimé" );
            maSession.setAttribute( "typeMessage" , "danger" );
            
        	return "succes";
        }
        catch( Exception e )
        {
        	request.setAttribute( "messageErreur" , e );
        	return "bug";
        }
    }
    
    private static String getNomImage( Part image )
    {
    	for( String contentDisposition : image.getHeader( "content-disposition").split( ";" ) )
    	{
    		if( contentDisposition.trim().startsWith( "filename" ) )
	    		return contentDisposition.substring( contentDisposition.indexOf( "=" ) + 1 ).trim().replace("\"", "" );
    	}
    	
    	return null;
    }
    
    private String importerImage( Part image , String nomImage ,  HttpServletRequest request )
    {
    	BufferedInputStream entree = null;
    	BufferedOutputStream sortie = null;
    	
    	try
    	{
    		entree = new BufferedInputStream( image.getInputStream() , TAILLE_TAMPON );
    		sortie = new BufferedOutputStream( new FileOutputStream( new File( cheminImage +  nomImage ) ) , TAILLE_TAMPON );
   
    		byte tampon[] = new byte[TAILLE_TAMPON];
    		int longueur;
    		
    		while( (longueur = entree.read( tampon ) ) > 0 )
    			sortie.write( tampon , 0 , longueur );
    		
    		sortie.close();
    		entree.close();
    		return "succes";
    	}
    	catch( IOException e )
    	{
    		request.setAttribute( "messageErreur" , e );
    		return "bug";
    	}
    }
    
    // ACCESSEURS DU BEAN MATERIEL
    public int getNumero()
    {
        return numero;
    }
    
    public String getNumeroChaine()
    {
    	String resultat = ( numero < 10 ) ? "0" + numero : String.valueOf( numero );
    	return "M" + resultat;
    }
    
    public String getDesign()
    {
        return design;
    }
    
    public int getPu()
    {
        return pu;
    }
    
    public int getStock()
    {
        return stock;
    }
    
    public int getTotalVendu()
    {
        return totalVendu;
    }
    
    public String getUrlImage()
    {
        return urlImage;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getCheminImage()
    {
        return cheminImage;
    }
    
    public void setNumero( int numero )
    {
        this.numero = numero;
    }
    
    public void setDesign( String design )
    {
        this.design = design;
    }
    
    public void setPu( int pu )
    {
        this.pu = pu;
    }
    
    public void setStock( int stock )
    {
        this.stock =  stock;
    }
    
    public void setTotalVendu( int totalVendu )
    {
        this.totalVendu =  totalVendu;
    }
    
    public void setUrlImage( String urlImage )
    {
        this.urlImage =  urlImage;
    }

    public void setDescription( String description )
    {
        this.description =  description;
    }
    
    public void setCheminImage( String cheminImage )
    {
        this.cheminImage = cheminImage;
    }
}
