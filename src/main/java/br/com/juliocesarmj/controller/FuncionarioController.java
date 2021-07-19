package br.com.juliocesarmj.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.juliocesarmj.dtos.FuncionarioCadastroDTO;
import br.com.juliocesarmj.dtos.FuncionarioConsultaDTO;
import br.com.juliocesarmj.dtos.FuncionarioEdicaoDTO;
import br.com.juliocesarmj.entities.Funcionario;
import br.com.juliocesarmj.enums.SituacaoFuncionario;
import br.com.juliocesarmj.helpers.DateHelper;
import br.com.juliocesarmj.repositories.FuncionarioRepository;

@Controller
public class FuncionarioController {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@RequestMapping("funcionario-cadastro")
	public ModelAndView cadastro() {

		ModelAndView modelAndView = new ModelAndView("funcionario-cadastro");
		
		modelAndView.addObject("situacoes", SituacaoFuncionario.values());
		modelAndView.addObject("dto", new FuncionarioCadastroDTO());

		return modelAndView;
	}

	@RequestMapping(value = "cadastrarFuncionario", method = RequestMethod.POST)
	public ModelAndView cadastrarFuncionario(FuncionarioCadastroDTO dto) {

		ModelAndView modelAndView = new ModelAndView("funcionario-cadastro");

		String erros = "";

		try {

			if (funcionarioRepository.findByCpf(dto.getCpf()) != null) {
				erros += "O CPF já encontra-se cadastrado. Tente outro. ";
			}
			if (funcionarioRepository.findByMatricula(dto.getMatricula()) != null) {
				erros += "A matrícula já encontra-se cadastrada. Tente outra. ";
			}
			if (erros != "") {
				throw new Exception(erros);
			}

			Funcionario funcionario = new Funcionario();
			funcionario.setNome(dto.getNome());
			funcionario.setCpf(dto.getCpf());
			funcionario.setMatricula(dto.getMatricula());
			funcionario.setDataAdmissao(DateHelper.toDate(dto.getDataAdmissao()));
			funcionario.setSituacao(SituacaoFuncionario.valueOf(dto.getSituacao()));

			funcionarioRepository.create(funcionario);

			modelAndView.addObject("mensagem_sucesso", "Funcionário cadastrado com sucesso!");

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", erros);
		}

		modelAndView.addObject("dto", new FuncionarioCadastroDTO());
		
		modelAndView.addObject("situacoes", SituacaoFuncionario.values());
		
		return modelAndView;
	}

	@RequestMapping("funcionario-consulta")
	public ModelAndView consulta() {

		ModelAndView modelAndView = new ModelAndView("funcionario-consulta");

		try {

			modelAndView.addObject("listagem_funcionarios", funcionarioRepository.findAll());

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Ocorreu um erro! " + e.getMessage());
		}
		modelAndView.addObject("dto", new FuncionarioConsultaDTO());

		return modelAndView;
	}
	
	@RequestMapping(value = "consultarFuncionarios", method = RequestMethod.POST)
	public ModelAndView consultarFuncionarios(FuncionarioConsultaDTO dto) {
		
		ModelAndView modelAndView = new ModelAndView("funcionario-consulta");
		
		try {
			
			Date dataInicio = DateHelper.toDate(dto.getDataInicio());
			Date dataFim = DateHelper.toDate(dto.getDataFim());
			
			modelAndView.addObject("listagem_funcionarios", funcionarioRepository.findByDataAdmissao(dataInicio, dataFim));
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Ocorreu um erro! " + e.getMessage());
		}
		
		modelAndView.addObject("dto", new FuncionarioConsultaDTO());

		return modelAndView;
	}
	
	@RequestMapping(value = "excluirFuncionario")
	public ModelAndView excluirFuncionario(Integer id) {

		ModelAndView modelAndView = new ModelAndView("funcionario-consulta");

		try {

			Funcionario funcionario = funcionarioRepository.findById(id);

			funcionarioRepository.delete(funcionario);

			modelAndView.addObject("mensagem_sucesso",
					"Funcionário: " + funcionario.getNome() + ", removido com sucesso!");

			modelAndView.addObject("listagem_funcionarios", funcionarioRepository.findAll());

		} catch (Exception e) {
			
			modelAndView.addObject("mensagem_erro", "Ocorreu um erro! " + e.getMessage());
		}
		
		modelAndView.addObject("dto", new FuncionarioConsultaDTO());

		return modelAndView;

	}
	
	@RequestMapping("funcionario-edicao")
	public ModelAndView funcionarioEdicao(Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("funcionario-edicao");
		
		try {
			
			Funcionario funcionario = funcionarioRepository.findById(id);
			
			FuncionarioEdicaoDTO dto = new FuncionarioEdicaoDTO();
			
			dto.setIdFuncionario(funcionario.getIdFuncionario());
			dto.setNome(funcionario.getNome());
			dto.setCpf(funcionario.getCpf());
			dto.setMatricula(funcionario.getMatricula());
			dto.setDataAdmissao(DateHelper.toString(funcionario.getDataAdmissao()));
			dto.setSituacao(funcionario.getSituacao().toString());
			
			modelAndView.addObject("dto", dto);
			
			modelAndView.addObject("situacoes", SituacaoFuncionario.values());
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Ocorreu um erro! " + e.getMessage());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "alterarFuncionario", method = RequestMethod.POST)
	public ModelAndView alterarFuncionario(FuncionarioEdicaoDTO dto) {
		
		ModelAndView modelAndView = new ModelAndView("funcionario-consulta");
		
		try {
			Funcionario funcionario = funcionarioRepository.findById(dto.getIdFuncionario());
			
			funcionario.setNome(dto.getNome());
			funcionario.setDataAdmissao(DateHelper.toDate(dto.getDataAdmissao()));
			funcionario.setSituacao(SituacaoFuncionario.valueOf(dto.getSituacao()));
			
			funcionarioRepository.update(funcionario);
			
			modelAndView.addObject("mensagem_sucesso", "Funcionário: " + funcionario.getNome() + ", atualizado com sucesso!");

			modelAndView.addObject("listagem_funcionarios", funcionarioRepository.findAll());
			
		}catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Ocorreu um erro! " + e.getMessage());
		}
		
		modelAndView.addObject("dto", new FuncionarioConsultaDTO());
		return modelAndView;
	}

	@RequestMapping("funcionario-relatorio")
	public String relatorio() {
		return "funcionario-relatorio";
	}
}
