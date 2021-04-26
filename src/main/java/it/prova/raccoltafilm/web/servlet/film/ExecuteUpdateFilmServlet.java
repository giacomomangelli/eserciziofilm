package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.film.FilmService;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteUpdateFilmServlet")
public class ExecuteUpdateFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idFilmParameter = request.getParameter("idToUpdate");
		String titoloFilmParamter = request.getParameter("titolo");
		String genereFilmParamter = request.getParameter("genere");
		String dataDiPubblicazioneFilmParamter = request.getParameter("dataPubblicazione");
		String minutiDurataFilmParameter = request.getParameter("minutiDurata");
		String registaParameter = request.getParameter("regista.id");

		Film filmInstance = UtilityForm.createFilmFromParams(titoloFilmParamter, genereFilmParamter,
				minutiDurataFilmParameter, dataDiPubblicazioneFilmParamter, registaParameter);

		if (!UtilityForm.validateFilmBean(filmInstance)) {
			request.setAttribute("update_film_attr", filmInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/film/update.jsp").forward(request, response);
			return;
		}

		try {
			FilmService filmServiceInstance = MyServiceFactory.getFilmServiceInstance();

			Film filmUpdateInstance = filmServiceInstance.caricaSingoloElementoEager(Long.parseLong(idFilmParameter));

			filmUpdateInstance.setTitolo(filmInstance.getTitolo());
			filmUpdateInstance.setGenere(filmInstance.getGenere());
			filmUpdateInstance.setMinutiDurata(filmInstance.getMinutiDurata());
			filmUpdateInstance.setDataPubblicazione(filmInstance.getDataPubblicazione());
			filmUpdateInstance.setRegista(filmInstance.getRegista());

			filmServiceInstance.aggiorna(filmUpdateInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/list.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");

	}

}
