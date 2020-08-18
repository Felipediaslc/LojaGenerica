package br.com.loja.controller;

import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.loja.infra.FileSaver;
import br.com.loja.model.Produto;
import br.com.loja.model.ProdutoBuilder;
import br.com.loja.model.TipoProduto;
import br.com.loja.model.Usuario;
import br.com.loja.service.ProdutoService;
import br.com.loja.service.UsuarioService;
import br.com.loja.utils.Conversor;

@Controller
@RequestScope
@RequestMapping("/cadastro")

public class CadastroController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	ServletContext request;

	@Autowired
	private FileSaver fileSaver;

	@GetMapping
	public String cadastro(RedirectAttributes redirectAttributes) {
		return "cadastro/cadastro";
	}

	@PostMapping
	public ModelAndView salvar(MultipartFile file, String descricao, Long estoque, String preco, String tipoProduto) {

		TipoProduto tipoProdutoNovo = Conversor.retornaTipoProdutoSelecionado(tipoProduto);

		String urlImagem = fileSaver.write(file);

		BigDecimal precoBigDecimal = Conversor.stringParaBigdecimal(preco);

		ProdutoBuilder produtoBuilder = new ProdutoBuilder();
		Produto produto = produtoBuilder.comDescricao(descricao).comEstoque(estoque).comPreco(precoBigDecimal)
				.comUrlImagem(urlImagem).comTipoProduto(tipoProdutoNovo)
				.comDataInsercao(new Date(System.currentTimeMillis())).build();
		
		produtoService.salvar(produto);
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro");
		modelAndView.addObject("message", "Produto salvo com sucesso");
		return modelAndView;
	}

	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public String cadastrarUsuarioTela() {
		return "cadastro/usuario";
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public Model cadastrarUsuario(String email, String nome, String senha, Model model) {
		Usuario usuario = new Usuario(email, nome, retornaSenhaCriptografada(senha));
		return cadastraUsuario(model, usuario);
	}

	private String retornaSenhaCriptografada(String senha) {
		return passwordEncoder.encode(senha);
	}
	
	private Model cadastraUsuario(Model model, Usuario usuario) {
		if (usuarioService.loadUserByUsername((usuario.getEmail())) != null) {
			model.addAttribute("falha", "Este email já está cadastrado");
		} else {
			usuarioService.salvar(usuario);
			model.addAttribute("message", "Cadastro realizado com sucesso");
		}
		return model;
	}
}
