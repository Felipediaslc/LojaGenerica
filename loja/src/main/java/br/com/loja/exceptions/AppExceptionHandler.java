
package br.com.loja.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleException(RuntimeException exception, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		
		if(exception.getLocalizedMessage() !=null && !exception.getLocalizedMessage().isEmpty()) {
			System.out.println(exception.getLocalizedMessage());
			modelAndView.addObject("message","Ocorreu um erro, favor informar ao desenvolvedor");
		}else{
			modelAndView.addObject("message", "Ocorreu um erro desconhecido, favor entrar em contato");
		}
		return modelAndView;
	}
}