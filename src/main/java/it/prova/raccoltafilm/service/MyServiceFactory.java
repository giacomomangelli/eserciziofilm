package it.prova.raccoltafilm.service;

import it.prova.raccoltafilm.dao.film.FilmDAO;
import it.prova.raccoltafilm.dao.film.FilmDAOImpl;
import it.prova.raccoltafilm.dao.regista.RegistaDAO;
import it.prova.raccoltafilm.dao.regista.RegistaDAOImpl;
import it.prova.raccoltafilm.dao.ruolo.RuoloDAO;
import it.prova.raccoltafilm.dao.ruolo.RuoloDAOImpl;
import it.prova.raccoltafilm.dao.utente.UtenteDAO;
import it.prova.raccoltafilm.dao.utente.UtenteDAOImpl;
import it.prova.raccoltafilm.service.film.FilmService;
import it.prova.raccoltafilm.service.film.FilmServiceImpl;
import it.prova.raccoltafilm.service.regista.RegistaService;
import it.prova.raccoltafilm.service.regista.RegistaServiceImpl;
import it.prova.raccoltafilm.service.ruolo.RuoloService;
import it.prova.raccoltafilm.service.ruolo.RuoloServiceImpl;
import it.prova.raccoltafilm.service.utente.UtenteService;
import it.prova.raccoltafilm.service.utente.UtenteServiceImpl;

public class MyServiceFactory {

	private static RegistaService REGISTA_SERVICE_INSTANCE;
	private static RegistaDAO REGISTA_DAO_INSTANCE;
	private static UtenteService UTENTE_SERVICE_INSTANCE;
	private static RuoloService RUOLO_SERVICE_INSTANCE;
	private static UtenteDAO UTENTE_DAO_INSTANCE = null;
	private static RuoloDAO RUOLO_DAO_INSTANCE = null;
	private static FilmService FILM_SERVICE_INSTANCE = null;
	private static FilmDAO FILM_DAO_INSTANCE = null;

	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTE_DAO_INSTANCE == null)
			UTENTE_DAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDAO(UTENTE_DAO_INSTANCE);
		return UTENTE_SERVICE_INSTANCE;
	}

	public static RuoloService getRuoloServiceInstance() {
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		if (RUOLO_DAO_INSTANCE == null)
			RUOLO_DAO_INSTANCE = new RuoloDAOImpl();

		RUOLO_SERVICE_INSTANCE.setRuoloDAO(RUOLO_DAO_INSTANCE);
		return RUOLO_SERVICE_INSTANCE;
	}

	public static RegistaService getRegistaServiceInstance() {
		if (REGISTA_SERVICE_INSTANCE == null)
			REGISTA_SERVICE_INSTANCE = new RegistaServiceImpl();

		if (REGISTA_DAO_INSTANCE == null)
			REGISTA_DAO_INSTANCE = new RegistaDAOImpl();

		REGISTA_SERVICE_INSTANCE.setRegistaDAO(REGISTA_DAO_INSTANCE);

		return REGISTA_SERVICE_INSTANCE;
	}

	public static FilmService getFilmServiceInstance() {
		if (FILM_SERVICE_INSTANCE == null)
			FILM_SERVICE_INSTANCE = new FilmServiceImpl();

		if (FILM_DAO_INSTANCE == null)
			FILM_DAO_INSTANCE = new FilmDAOImpl();

		FILM_SERVICE_INSTANCE.setFilmDAO(FILM_DAO_INSTANCE);

		return FILM_SERVICE_INSTANCE;
	}

}
