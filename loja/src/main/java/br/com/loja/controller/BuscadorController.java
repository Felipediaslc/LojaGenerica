package br.com.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.service.ProdutoService;

@Controller
@RequestMapping("/buscador")
public class BuscadorController {

	@Autowired
	ProdutoService service;

	@GetMapping()
	public ModelAndView busca(@RequestParam("busca") String busca,
			@PageableDefault(sort = "descricao", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao,
			@RequestParam(required = false) boolean usaBusca) {
		ModelAndView modelAndView = new ModelAndView("buscador");
		modelAndView.addObject("produtoPage", service.buscador(busca, paginacao));
		if (usaBusca) {
			modelAndView.addObject("buscaAtual", busca);
		}

		return modelAndView;
	}

}
