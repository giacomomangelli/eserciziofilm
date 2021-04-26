package it.prova.raccoltafilm.service.film;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.raccoltafilm.dao.film.FilmDAO;
import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.web.listener.LocalEntityManagerFactoryListener;

public class FilmServiceImpl implements FilmService {

	private FilmDAO filmDAO;

	@Override
	public void setFilmDAO(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}

	@Override
	public List<Film> listAllElements() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return filmDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Film caricaSingoloElemento(Long id) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
		
			filmDAO.setEntityManager(entityManager);
			return filmDAO.findOne(id).orElse(null);
		
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		
		}finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	
	}

	@Override
	public Film caricaSingoloElementoEager(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return filmDAO.findOneEager(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Film filmInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			filmDAO.update(filmInstance);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	@Override
	public void inserisciNuovo(Film filmInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			// grazie al fatto che il regista ha un id viene eseguito il merge automaticamente
			// se quell'id non ha un corrispettivo in tabella viene lanciata una eccezione
			filmDAO.insert(filmInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Film filmInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			
			entityManager.getTransaction().begin();
			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);
			filmInstance = entityManager.merge(filmInstance);
			filmDAO.delete(filmInstance);
			// eseguo quello che realmente devo fare
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Film> findByExample(Film example) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			filmDAO.setEntityManager(entityManager);

			return filmDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}