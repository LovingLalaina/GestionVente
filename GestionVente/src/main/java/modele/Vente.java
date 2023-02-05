
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

public class Vente extends MaterielBean
{
    private int nombreVente;
    private String numeroNom;
    private String dateDebut;
    private String dateFin;
    private int annee;
    private int mois;
    
    //CONSTRUCTEUR PAR DEFAUT
    public Vente()
    {
    	super();
        this.nombreVente = 0;
        this.numeroNom = "";
        this.dateDebut = "";
        this.annee = 0;
        this.mois = 0;
    }
    
    //CONSTRUCTEUR PAR COPIE
    public Vente( Vente maVente )
    {
    	super( maVente );
    	this.nombreVente = maVente.nombreVente;
        this.numeroNom = maVente.numeroNom;
        this.dateDebut = maVente.dateDebut;
        this.annee = maVente.annee;
        this.mois = maVente.mois;
    }
    
    public List<Vente> afficherVenteClient( HttpServletRequest request )
    {
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            ResultSet resultatSELECT;
            String requeteSQL;
            
            this.numeroNom = Identification.securiserDonnee( request.getParameter( "NumeroNom" ) );
            String choix = request.getParameter( "Choix" );
            
            if( choix.equals( "DeuxDates" ) )
            {
            	this.dateDebut = Identification.securiserDonnee( request.getParameter( "DateDebutBilan" ) );
            	this.dateFin = Identification.securiserDonnee(  request.getParameter( "DateFinBilan" ) );
            	requeteSQL = "SELECT * , sum(qte*pu) AS total_vendu , sum(qte) AS nombre_vente FROM achat JOIN materiel ON achat.num_materiel=materiel.num_materiel JOIN utilisateur ON achat.num_user=utilisateur.num_user WHERE ( date_achat BETWEEN '" + this.dateDebut + "' AND '" + this.dateFin + "' ) AND ( utilisateur.num_user='" + this.numeroNom + "' OR nom='" + this.numeroNom + "') GROUP BY materiel.num_materiel";
            }
            else if( choix.equals( "Annee" ) )
            {
            	this.annee = Integer.parseInt( request.getParameter( "AnneeBilan" ) );
            	requeteSQL = "SELECT * , sum(qte*pu) AS total_vendu , sum(qte) AS nombre_vente FROM achat JOIN materiel ON achat.num_materiel=materiel.num_materiel JOIN utilisateur ON achat.num_user=utilisateur.num_user WHERE ( year(date_achat)='" + this.annee + "' ) AND ( utilisateur.num_user='" + this.numeroNom + "' OR nom='" + this.numeroNom + "') GROUP BY materiel.num_materiel";
            }
            else
            {
            	this.mois = Integer.parseInt( request.getParameter( "MoisBilan" ) );
            	requeteSQL = "SELECT * , sum(qte*pu) AS total_vendu , sum(qte) AS nombre_vente FROM achat JOIN materiel ON achat.num_materiel=materiel.num_materiel JOIN utilisateur ON achat.num_user=utilisateur.num_user WHERE ( month(date_achat)='" + this.mois + "' ) AND ( year(date_achat)='" + new Calendrier().getAnnee() + "' ) AND ( utilisateur.num_user='" + this.numeroNom + "' OR nom='" + this.numeroNom + "') GROUP BY materiel.num_materiel";
            }
            
            List<Vente> tableauVente = new ArrayList<Vente>();

            resultatSELECT = execution.executeQuery( requeteSQL );
            
            while( resultatSELECT.next() )
            {
                this.numero = resultatSELECT.getInt( "num_materiel" );
                this.design = resultatSELECT.getString( "design" );
                this.pu = resultatSELECT.getInt( "pu" );
                this.nombreVente = resultatSELECT.getInt( "nombre_vente" );
                this.totalVendu = resultatSELECT.getInt( "total_vendu" );
                tableauVente.add( new Vente( this ) );
            }
            
            return tableauVente;
            
        }
        catch( Exception e )
        {
            return null;
        }
    }
    
    public List<Vente> afficherVenteMateriel( HttpServletRequest request )
    {
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            ResultSet resultatSELECT;
            String requeteSQL;
            
            String choix = request.getParameter( "Choix" );
            
            if( choix.equals( "DeuxDates" ) )
            {
            	this.dateDebut = Identification.securiserDonnee( request.getParameter( "DateDebutBilan" ) );
            	this.dateFin = Identification.securiserDonnee( request.getParameter( "DateFinBilan" ) );
            	requeteSQL = "SELECT * , sum(qte*pu) AS total_vendu , sum(qte) AS nombre_vente FROM achat JOIN materiel ON achat.num_materiel=materiel.num_materiel JOIN utilisateur ON achat.num_user=utilisateur.num_user WHERE ( date_achat BETWEEN '" + this.dateDebut + "' AND '" + this.dateFin + "' ) GROUP BY materiel.num_materiel";
            }
            else if( choix.equals( "Annee" ) )
            {
            	this.annee = Integer.parseInt( request.getParameter( "AnneeBilan" ) );
            	requeteSQL = "SELECT * , sum(qte*pu) AS total_vendu , sum(qte) AS nombre_vente FROM achat JOIN materiel ON achat.num_materiel=materiel.num_materiel JOIN utilisateur ON achat.num_user=utilisateur.num_user WHERE ( year(date_achat)='" + this.annee + "' ) GROUP BY materiel.num_materiel";
            }
            else
            {
            	this.mois = Integer.parseInt( request.getParameter( "MoisBilan" ) );
            	requeteSQL = "SELECT * , sum(qte*pu) AS total_vendu , sum(qte) AS nombre_vente FROM achat JOIN materiel ON achat.num_materiel=materiel.num_materiel JOIN utilisateur ON achat.num_user=utilisateur.num_user WHERE ( month(date_achat)='" + this.mois + "' ) AND ( year(date_achat)='" + new Calendrier().getAnnee() + "' ) GROUP BY materiel.num_materiel";
            }
            
            List<Vente> tableauVente = new ArrayList<Vente>();

            resultatSELECT = execution.executeQuery( requeteSQL );
            
            while( resultatSELECT.next() )
            {
                this.numero = resultatSELECT.getInt( "num_materiel" );
                this.design = resultatSELECT.getString( "design" );
                this.pu = resultatSELECT.getInt( "pu" );
                this.nombreVente = resultatSELECT.getInt( "nombre_vente" );
                this.totalVendu = resultatSELECT.getInt( "total_vendu" );
                tableauVente.add( new Vente( this ) );
            }
            
            return tableauVente;
            
        }
        catch( Exception e )
        {
            return null;
        }
    }
    
    
    // ACCESSEURS DU BEAN VENTE
    public int getNombreVente()
    {
        return nombreVente;
    }
    
    public String getNumeroNom()
    {
        return numeroNom;
    }
    
    public String getDateDebut()
    {
        return dateDebut;
    }
    
    public String getDateFin()
    {
        return dateFin;
    }
    
    public int getAnnee()
    {
        return annee;
    }
    
    public int getMois()
    {
        return mois;
    }
    
    public void setNombreVente( int nombreVente )
    {
        this.nombreVente = nombreVente;
    }
    
    public void setNumeroNom( String numeroNom )
    {
        this.numeroNom = numeroNom;
    }
    
    public void setDateDebut( String dateDebut )
    {
        this.dateDebut = dateDebut;
    }
    
    public void setDateFin( String dateFin )
    {
        this.dateFin =  dateFin;
    }
    
    public void setAnnee( int annee )
    {
        this.annee =  annee;
    }
    
    public void setMois( int mois )
    {
        this.mois =  mois;
    }
}
