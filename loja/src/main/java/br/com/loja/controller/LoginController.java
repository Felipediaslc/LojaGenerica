package br.com.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("paginaLogin", true);
		return modelAndView;
	}

	@RequestMapping("/login-error")  
    public String loginError(Model model) {  
        model.addAttribute("loginError", true);  
        return "login";  
    }  
	
}
