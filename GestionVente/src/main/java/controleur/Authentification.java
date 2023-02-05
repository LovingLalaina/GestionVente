package controleur;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modele.Identification;

@WebServlet( name = "Authentification" , urlPatterns = { "/Authentification" } )

public class Authentification extends HttpServlet
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
	protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
	{
    	//REMARQUE : CE CODE N'EST EXECUTE QUE SI ON TAPE L'URL AUTHENTIFICATION
    	final String redirection = new Identification().verifierSession( uploadPath , request );
    	this.getServletContext().getRequestDispatcher( redirection ).forward( request , response );
	}
	
    @Override
	protected void doPost( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
	{
    	final String redirection = new Identification().identifier( uploadPath , request );
    	this.getServletContext().getRequestDispatcher( redirection ).forward( request , response );
	}

}
