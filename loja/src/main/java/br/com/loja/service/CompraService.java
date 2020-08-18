package br.com.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.com.loja.model.Compra;
import br.com.loja.repository.CompraRepository;

@Service
public class CompraService {
 
	@Autowired
    private CompraRepository repository;
	
	@CacheEvict("ultimosLancamentos")
	public void salvar(Compra compra) {
		repository.save(compra);
	}

	 public Compra obterPorCodigo(Long codigo){
	        return repository.findById(codigo).orElse(null);
	    }
	
}
