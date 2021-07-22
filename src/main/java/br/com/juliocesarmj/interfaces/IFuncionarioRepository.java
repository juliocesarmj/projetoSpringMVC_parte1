package br.com.juliocesarmj.interfaces;

import java.util.Date;
import java.util.List;

import br.com.juliocesarmj.entities.Funcionario;
import br.com.juliocesarmj.enums.SituacaoFuncionario;

public interface IFuncionarioRepository extends IBaseRepository<Funcionario> {
	
	Funcionario findByCpf(String cpf) throws Exception;
	
	Funcionario findByMatricula(String matricula) throws Exception;
	
	List<Funcionario> findByDataAdmissao(Date dataInicio, Date dataFim) throws Exception;
	
	Integer countBySituacao(SituacaoFuncionario situacao) throws Exception;
}
