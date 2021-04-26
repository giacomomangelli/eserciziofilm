package it.prova.raccoltafilm.dao.utente;

import java.util.List;
import java.util.Optional;

import it.prova.raccoltafilm.dao.IBaseDAO;
import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public List<Utente> findAllByRuolo(Ruolo ruoloInput);
	public Optional<Utente> findByUsernameAndPassword(String username,String password);
	public Optional<Utente> login(String username,String password);

}
