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
import br.com.loja.utils.Conversor;

@Controller
@RequestMapping("/tipoProduto")
public class TipoProdutoController {

	@Autowired
	ProdutoService service;

	@GetMapping()
	public ModelAndView tipoProduto(@RequestParam ("tipo") String tipo, @PageableDefault(sort = "descricao", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao) {
		ModelAndView modelAndView = new ModelAndView("tipoProduto");
		modelAndView.addObject("produtoPage",
				 service.obterPorTipo(Conversor.retornaTipoProdutoSelecionado(tipo), paginacao));

		return modelAndView;
	}
}
