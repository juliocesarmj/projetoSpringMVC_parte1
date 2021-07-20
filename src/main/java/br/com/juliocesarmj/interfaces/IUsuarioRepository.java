package br.com.juliocesarmj.interfaces;

import br.com.juliocesarmj.entities.Usuario;

public interface IUsuarioRepository extends IBaseRepository<Usuario> {
	
	Usuario findByEmail(String email) throws Exception;
	
	Usuario findByEmailAndSenha(String email, String senha) throws Exception;
}
