
package controleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modele.FactureBean;

@WebServlet( name = "Facture" , urlPatterns = { "/Facture" } )

public class Facture extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession maSession = request.getSession();
        if( maSession.getAttribute("type") == null )
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request , response );
        else
        {
        	request.setAttribute( "Facture" , new FactureBean().facturer( request ) );
        	this.getServletContext().getRequestDispatcher( "/facture.jsp" ).forward( request , response );
        }
    }

    @Override
    protected void doPost( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException
    {
        //GROS TRAITEMENT BACK-END DE L'AFFICHAGE DE FACTURE
       this.getServletContext().getRequestDispatcher( "/facture.jsp" ).forward( request , response ); 
    }
}
