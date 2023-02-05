
package controleur;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.UtilisateurBean;

@WebServlet( name = "MonCompte" , urlPatterns = { "/MonCompte" } )

public class MonCompte extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession maSession = request.getSession();
        if( maSession.getAttribute("type") == null )
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
        else
            this.getServletContext().getRequestDispatcher( "/monCompte.jsp" ).forward( request , response );
    }
    
    @Override
    protected void doPost( HttpServletRequest request , HttpServletResponse response )throws ServletException, IOException
    {
    	//TRAITEMENT BACK-END POUR LA MODIFICATION OU SUPPRESSION D'UN CLIENT
    	UtilisateurBean monUtilisateur = new UtilisateurBean();
    	
    	if( ( (String) request.getParameter( "BoutonUtilisateur" ) ).equals( "Modifier" ) )
    	{
    		
    		String message = monUtilisateur.modifier( request );
        	
        	if( message.equals( "bug" ) )
        		this.getServletContext().getRequestDispatcher( "/pageErreur.jsp" ).forward( request , response );
        	else
                this.getServletContext().getRequestDispatcher( "/monCompte.jsp" ).forward( request , response );
    	}
    	else // SUPPRESSION
    	{
    		String message = monUtilisateur.supprimer( request );
        	
        	if( message.equals( "bug" ) )
        		this.getServletContext().getRequestDispatcher( "/pageErreur.jsp" ).forward( request , response );
        	else
                this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
    	}
    	
    }
    
}
