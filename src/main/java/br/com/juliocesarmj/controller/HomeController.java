package br.com.juliocesarmj.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.juliocesarmj.dtos.LoginDTO;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/")
	public ModelAndView login() {
		
		ModelAndView modelAndView = new ModelAndView("login");
		
		modelAndView.addObject("dto", new LoginDTO());
		return modelAndView;
	}
	@RequestMapping(value="/home")
	public ModelAndView home(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
}
