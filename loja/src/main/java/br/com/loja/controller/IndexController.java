package br.com.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.service.ProdutoService;

@Controller
@RequestMapping({ "/", "/index" })
public class IndexController {

	@Autowired
	ProdutoService service;
	
	@GetMapping
	public String index (Model model) {
		model.addAttribute("produtos", service.obterUltimosLançamentos());
		return "index";
	}
}
