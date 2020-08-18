package br.com.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.service.ProdutoService;

@Controller

public class DetalhesController {

	@Autowired
	public ProdutoService service;
	
	@RequestMapping("/detalhes/{codigo}")
	public String carrinho(@PathVariable("codigo") Long codigo, Model model) {
		
		model.addAttribute("produto", service.obterPorCodigo(codigo)); 
		
		return "detalhes";
	}
}
