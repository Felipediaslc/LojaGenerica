package br.com.loja.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.loja.model.Produto;

@Repository
public class JPQLQueries {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Produto> findUltimosLancamentos() {
		return entityManager.createQuery("SELECT p FROM Produto p ORDER BY p.dataInsercao desc",
				Produto.class).setMaxResults(5).getResultList();
	}
}