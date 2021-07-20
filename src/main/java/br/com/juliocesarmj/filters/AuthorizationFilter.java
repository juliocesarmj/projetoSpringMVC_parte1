package br.com.juliocesarmj.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
public class AuthorizationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//convertendo a request e a response em objetos mais atualizados
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		/*
		 * Iremos criar uma regra que verifica se o usuário está autenticado.
		 * Essa regra será aplicada em todas as páginas do sistema, exceto:
		 * pagina raiz '/' (raiz)
		 * autenticarUsuario
		 * logout*/
		
		String path = req.getServletPath();
		List<String> urlPermitidas = new ArrayList<String>();
		urlPermitidas.add("/");
		urlPermitidas.add("/autenticarUsuario");
		urlPermitidas.add("/logout");
				
		if(!urlPermitidas.contains(path)) {
			
			if(req.getSession().getAttribute("usuario_autenticado") == null) {
				resp.sendRedirect("/projetoSpringMVC/");
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
