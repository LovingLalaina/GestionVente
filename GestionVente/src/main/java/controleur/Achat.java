
package controleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.AchatBean;

@WebServlet( name = "Achat" , urlPatterns = { "/Achat" } )

public class Achat extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession maSession = request.getSession();
        if( maSession.getAttribute("type") == null )
        {
            maSession.setAttribute( "message" , "Veuillez vous connecter en tant qu'administrateur!!" );
            maSession.setAttribute( "typeMessage" , "danger");
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
        }
        else if( maSession.getAttribute("type").equals( "client" ) )
        {
            maSession.setAttribute( "message" , "Veuillez vous connecter en tant qu'administrateur!!" );
            maSession.setAttribute( "typeMessage" , "danger");
            this.getServletContext().getRequestDispatcher( "/materiel.jsp" ).forward( request , response );
        }
        else
        {
            //L'UTILISATEUR EST BEL ET BIEN UN ADMINISTRATEUR
            request.setAttribute( "Achat" , new AchatBean().afficher( request ) );
            this.getServletContext().getRequestDispatcher( "/achat.jsp" ).forward( request , response );
        }
    }
    
    @Override
    protected void doPost( HttpServletRequest request , HttpServletResponse response )throws ServletException, IOException
    {
    	AchatBean monAchat = new AchatBean();
    	String message;
    	
    	if( request.getParameter( "BoutonAchat" ).equals( "Modifier" ) )
    	{
    		//TRAITEMENT BACK-END DE LA MODIFICATION D'UN ACHAT
    		message = monAchat.modifier( request );
        	
        	if( message.equals( "bug" ) )
        		this.getServletContext().getRequestDispatcher( "/pageErreur.jsp" ).forward( request , response );
        	else
        	{
        		request.setAttribute( "Achat" , monAchat.afficher( request ) );
                this.getServletContext().getRequestDispatcher( "/achat.jsp" ).forward( request , response ); 
        	}
    	}
    	else
    	{
    		message = monAchat.supprimer( request );
    		
    		if( message.equals( "bug" ) )
        		this.getServletContext().getRequestDispatcher( "/pageErreur.jsp" ).forward( request , response );
        	else
        	{
        		request.setAttribute( "Achat" , monAchat.afficher( request ) );
                this.getServletContext().getRequestDispatcher( "/achat.jsp" ).forward( request , response );
        	}
    	}
    }
    
}
