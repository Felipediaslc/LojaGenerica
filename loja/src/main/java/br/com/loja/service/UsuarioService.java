package br.com.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.loja.model.Usuario;
import br.com.loja.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
		return repository.findById(email).orElse(null);
	}
	
	public void salvar(Usuario usuario) {
		repository.save(usuario);
	}
}
