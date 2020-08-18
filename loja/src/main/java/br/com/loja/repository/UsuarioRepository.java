package br.com.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
