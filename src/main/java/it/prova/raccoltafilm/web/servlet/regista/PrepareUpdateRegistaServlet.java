package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/PrepareUpdateRegistaServlet")
public class PrepareUpdateRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idRegistaParameter = request.getParameter("idRegista");
		
		try {
			
			Regista registaInstance = MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(Long.parseLong(idRegistaParameter));
			
			if (registaInstance == null) {
				request.setAttribute("errorMessage", "Errore caricamento elemento.");
				return;
			}

			request.setAttribute("update_regista_attr", registaInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}		
		
		request.getRequestDispatcher("/regista/update.jsp").forward(request, response);
	}

}
