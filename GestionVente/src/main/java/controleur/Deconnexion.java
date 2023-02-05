package controleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet( name = "Deconnexion" , urlPatterns = { "/Deconnexion" } )

public class Deconnexion extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
    {
    	HttpSession maSession = request.getSession();
    	maSession.setAttribute( "type" , null );
        maSession.setAttribute( "UtilisateurActuel" , null );
        this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
	}

    @Override
	protected void doPost( HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException
    {
    	
	}

}
