package br.com.juliocesarmj.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.juliocesarmj.dtos.LoginDTO;
import br.com.juliocesarmj.entities.Usuario;
import br.com.juliocesarmj.enums.SituacaoFuncionario;
import br.com.juliocesarmj.repositories.FuncionarioRepository;
import br.com.juliocesarmj.repositories.UsuarioRepository;

@Controller
public class HomeController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/")
	public ModelAndView login() {
		
		ModelAndView modelAndView = new ModelAndView("login");
		
		modelAndView.addObject("dto", new LoginDTO());
		return modelAndView;
	}
	
	@RequestMapping(value = "autenticarUsuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(LoginDTO dto, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("login");
		
		try {
			
			Usuario usuario = usuarioRepository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());
			
			if(usuario != null) {
				
				//TODO
				//armazenar os dados do usuário em sessão.
				request.getSession().setAttribute("usuario_autenticado", usuario);
				//redireciono para a página inicial do sistema
				modelAndView.setViewName("redirect:home");
			} else {
				modelAndView.addObject("mensagem_erro", "Acesso negado! Informe um email e senha, válidos.");
			}
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", new LoginDTO());
		return modelAndView;
	}
	
	@RequestMapping(value="/home")
	public ModelAndView home(HttpServletResponse response) throws IOException{
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		try {
			
			modelAndView.addObject("qtd_admitido", funcionarioRepository.countBySituacao(SituacaoFuncionario.Admitido));
			modelAndView.addObject("qtd_afastado", funcionarioRepository.countBySituacao(SituacaoFuncionario.Afastado));
			modelAndView.addObject("qtd_ferias", funcionarioRepository.countBySituacao(SituacaoFuncionario.Ferias));
			modelAndView.addObject("qtd_demitido", funcionarioRepository.countBySituacao(SituacaoFuncionario.Demitido));
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		//remover o usuario autenticado da sessao
		request.getSession().removeAttribute("usuario_autenticado");
		
		//redireciona para a página raiz(login)
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		return modelAndView;
	}
}
