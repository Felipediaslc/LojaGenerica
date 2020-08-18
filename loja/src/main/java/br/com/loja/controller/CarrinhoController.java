package br.com.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.model.CarrinhoCompras;
import br.com.loja.model.CarrinhoItem;
import br.com.loja.model.Produto;
import br.com.loja.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private ProdutoService service;

	@Autowired
	private CarrinhoCompras carrinho;

	private CarrinhoItem criaItem(Long codigo){
		Produto produto = service.obterPorCodigo(codigo);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto);
		return carrinhoItem;
	}

	@GetMapping
	public ModelAndView itens(ModelAndView modelAndView){
		modelAndView.setViewName("carrinho/itens");
		modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}

	@RequestMapping("/add")
	public ModelAndView add(Long codigo){
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(codigo);
		carrinho.add(carrinhoItem);
		modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}

	@RequestMapping("/remover")
	public ModelAndView remover(Long codigo){
		carrinho.remover(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}
	
	@RequestMapping("/diminuir")
	public ModelAndView diminuir(Long codigo){
		carrinho.reduzQuantidade(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}
	
	@RequestMapping("/aumentar")
	public ModelAndView aumentar(Long codigo){
		carrinho.aumentaQuantidade(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}

}
