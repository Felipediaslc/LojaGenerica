package br.com.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.Compra;


public interface CompraRepository  extends JpaRepository<Compra, Long>{

}
