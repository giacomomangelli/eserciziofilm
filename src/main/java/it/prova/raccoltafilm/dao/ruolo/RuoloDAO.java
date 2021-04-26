package it.prova.raccoltafilm.dao.ruolo;

import it.prova.raccoltafilm.dao.IBaseDAO;
import it.prova.raccoltafilm.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {
	
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception;

}
