package it.prova.raccoltafilm.dao.film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.raccoltafilm.model.Film;

public class FilmDAOImpl implements FilmDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Film> list() throws Exception {
		return entityManager.createQuery("from Film", Film.class).getResultList();
	}

	@Override
	public Optional<Film> findOne(Long id) throws Exception {
		if (id == null || id < 1) {
			throw new Exception("Problema valore in input");
		}
		Film result = entityManager.find(Film.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Film filmInstance) throws Exception {
		if (filmInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.merge(filmInstance);
	}

	@Override
	public void insert(Film filmInstance) throws Exception {
		if (filmInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(filmInstance);
	}

	@Override
	public void delete(Film filmInstance) throws Exception {
		if (filmInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(filmInstance));
	}

	@Override
	public Optional<Film> findOneEager(Long id) throws Exception {
		return entityManager.createQuery("from Film f left join fetch f.regista where f.id=:idFilm", Film.class)
				.setParameter("idFilm", id).getResultList().stream().findFirst();
	}

	@Override
	public List<Film> findByExample(Film filmExample) throws Exception {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();
		StringBuilder queryBuilder;

		if (filmExample.getRegista() != null) {
			queryBuilder = new StringBuilder("select f from Film f join fetch f.regista r where f.id = f.id ");
			whereClauses.add(" r = :regista ");
			parameterMap.put("regista", filmExample.getRegista());
		} else {
			queryBuilder = new StringBuilder("select f from Film f where f.id = f.id ");
		}

		if (StringUtils.isNotEmpty(filmExample.getTitolo())) {
			whereClauses.add(" f.titolo like :titolo ");
			parameterMap.put("titolo", "%" + filmExample.getTitolo() + "%");
		}
		if (StringUtils.isNotEmpty(filmExample.getGenere())) {
			whereClauses.add(" f.genere like :genere ");
			parameterMap.put("genere", "%" + filmExample.getGenere() + "%");
		}
		if (filmExample.getMinutiDurata() != null && filmExample.getMinutiDurata() > 0) {
			whereClauses.add(" f.minutiDurata = :minutiDurata ");
			parameterMap.put("minutiDurata", filmExample.getMinutiDurata());
		}
		if (filmExample.getDataPubblicazione() != null) {
			whereClauses.add(" f.dataPubblicazione >= :dataPubblicazione ");
			parameterMap.put("dataPubblicazione", filmExample.getDataPubblicazione());
		}
		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Film> typedQuery = entityManager.createQuery(queryBuilder.toString(), Film.class);

		for (String key : parameterMap.keySet()) {
			typedQuery.setParameter(key, parameterMap.get(key));
		}
		return typedQuery.getResultList();
	}

}
