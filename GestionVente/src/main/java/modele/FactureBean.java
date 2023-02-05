
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.github.royken.converter.FrenchNumberToWords;

import jakarta.servlet.http.HttpServletRequest;

public class FactureBean extends AchatBean
{
	//ATTRIBUT DE ACHATBEAN
    //protected int id;
    //protected int numeroUser;
    //protected int numeroMateriel;
    //protected int qte;
    //protected String dateAchat;
	
    private String nomUser;
    private String designMateriel;
    private int pu;
    private int montant;
    private int total;
    private String totalLettre;
    
    
    //CONSTRUCTEUR PAR DEFAUT
    public FactureBean()
    {
    	super();
        this.nomUser = "";
        this.designMateriel = "";
        this.pu = 0;
        this.montant = 0;
        this.total = 0;
        this.totalLettre = "";
    }
    
  //CONSTRUCTEUR PAR COPIE
    public FactureBean( FactureBean facture )
    {
    	super( facture );
        this.nomUser = facture.nomUser;
        this.designMateriel = facture.designMateriel;
        this.pu = facture.pu;
        this.montant = facture.montant;
        this.total = facture.total;
        this.totalLettre = facture.totalLettre;
    }
    
    public List<FactureBean> facturer( HttpServletRequest request )
    {
    	try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection connexionBD = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hightech_market" , "root" , "" );
            Statement execution = connexionBD.createStatement();
            
            List<FactureBean> tableauFacture = new ArrayList<FactureBean>();
            
            ResultSet resultatSELECT = execution.executeQuery( "SELECT * , ( materiel.pu * achat.qte ) AS montant FROM achat JOIN utilisateur ON achat.num_user=utilisateur.num_user JOIN materiel ON achat.num_materiel=materiel.num_materiel WHERE (achat.num_user='" + request.getParameter( "NumUser" ) + "' AND year(date_achat)='" + request.getParameter( "Annee" ) + "')" );

            while( resultatSELECT.next() )
            {
                this.id = resultatSELECT.getInt( "id" );
                this.numeroUser = resultatSELECT.getInt( "num_user" );
                this.numeroMateriel = resultatSELECT.getInt( "num_materiel" );
                this.qte = resultatSELECT.getInt( "qte" );
                this.dateAchat = resultatSELECT.getString( "date_achat" );
                this.nomUser = resultatSELECT.getString( "nom" );
                this.designMateriel = resultatSELECT.getString( "design" );
                this.pu = resultatSELECT.getInt( "pu" );
                this.montant = resultatSELECT.getInt( "montant" );
                this.total += this.montant;
                this.totalLettre = FrenchNumberToWords.convert( this.total );
                
                tableauFacture.add( new FactureBean( this ) );
            }
            
            return tableauFacture;
        }
        catch( Exception e )
        {
        	return null;
        }
    }
    
    public String getNomUser()
    {
        return nomUser;
    }
    
    public String getDesignMateriel()
    {
        return designMateriel;
    }
    
    public int getPu()
    {
        return pu;
    }
    
    public int getMontant()
    {
        return montant;
    }
    
    public int getTotal()
    {
        return total;
    }
    
    public String getTotalLettre()
    {
        return totalLettre;
    }
    
    public void setNomUser( String nomUser )
    {
        this.nomUser = nomUser;
    }
    
    public void setDesignMateriel( String designMateriel )
    {
        this.designMateriel = designMateriel;
    }
    
    public void setPu( int pu )
    {
        this.pu = pu;
    }
    
    public void setMontant( int montant )
    {
        this.montant =  montant;
    }
    
    public void setTotal( int total )
    {
        this.total =  total;
    }
    
    public void setTotalLettre( String totalLettre )
    {
        this.totalLettre =  totalLettre;
    }
}
