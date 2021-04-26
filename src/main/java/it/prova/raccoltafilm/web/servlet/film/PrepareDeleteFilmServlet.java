package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/PrepareDeleteFilmServlet")
public class PrepareDeleteFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idFilmParameter = request.getParameter("idFilm");
		
		try {
			
			Film filmInstance = MyServiceFactory.getFilmServiceInstance().caricaSingoloElementoEager(Long.parseLong(idFilmParameter));
			
			if (filmInstance == null) {
				request.setAttribute("errorMessage", "Errore caricamento elemento.");
				return;
			}

			request.setAttribute("delete_film_attr", filmInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}		
		
		request.getRequestDispatcher("/film/delete.jsp").forward(request, response);
	}

}
