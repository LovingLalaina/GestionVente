
package controleur;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.MaterielBean;
import modele.Vente;

@WebServlet( name = "MaterielClient" , urlPatterns = { "/MaterielClient" } )

public class MaterielClient extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	   
    /*
     * Chemin dans lequel les images seront sauvegardées.
     */
    public static final String CHEMIN_IMAGES = "/BaseDeDonnees/ImageS/Materiel/";
        
    public String uploadPath;
    public File uploadDir;
    
    /*
     * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa création.
     */ 
    @Override
    public void init() throws ServletException {
        uploadPath = getServletContext().getRealPath( CHEMIN_IMAGES );
        uploadDir = new File( uploadPath );
        if ( !uploadDir.exists() ) uploadDir.mkdir();
    }
    
    @Override
    protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession maSession = request.getSession();
        if( maSession.getAttribute("type") == null )
        {
            maSession.setAttribute( "message" , "Veuillez vous connecter en tant qu'administrateur!!" );
            maSession.setAttribute( "typeMessage" , "warning");
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
        }
        else if( maSession.getAttribute("type").equals( "client" ) )
        {
            maSession.setAttribute( "message" , "Veuillez vous connecter en tant qu'administrateur!!" );
            maSession.setAttribute( "typeMessage" , "warning" );
            this.getServletContext().getRequestDispatcher( "/materiel.jsp" ).forward( request , response );
        }
        else
        {
            //L'UTILISATEUR EST BEL ET BIEN UN ADMINISTRATEUR
            request.setAttribute( "MaterielComplet" , new MaterielBean( this.uploadPath ).afficher( request ) );
            this.getServletContext().getRequestDispatcher( "/materielClient.jsp" ).forward( request , response ); 
        }
            
    }
    
    @Override
    protected void doPost( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException
    {
    	request.setAttribute( "mesVentes" , new Vente().afficherVenteClient( request ) );
        this.getServletContext().getRequestDispatcher( "/materielClient.jsp" ).forward( request , response ); 
    }
    
}
