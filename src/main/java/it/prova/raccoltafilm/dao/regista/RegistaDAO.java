package it.prova.raccoltafilm.dao.regista;

import java.util.List;

import it.prova.raccoltafilm.dao.IBaseDAO;
import it.prova.raccoltafilm.model.Regista;

public interface RegistaDAO extends IBaseDAO<Regista> {
	public List<Regista> findByExample(Regista example) throws Exception;

	public Regista getEagerProducer(Long id) throws Exception;
	
	public void removeAllMovies(Regista regista) throws Exception;
}
