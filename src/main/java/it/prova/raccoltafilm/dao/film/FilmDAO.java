package it.prova.raccoltafilm.dao.film;

import java.util.List;
import java.util.Optional;

import it.prova.raccoltafilm.dao.IBaseDAO;
import it.prova.raccoltafilm.model.Film;

public interface FilmDAO extends IBaseDAO<Film> {
	public Optional<Film> findOneEager(Long id) throws Exception;
	
	public List<Film> findByExample(Film filmExample) throws Exception;
}
