package br.com.juliocesarmj.controller;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.juliocesarmj.dtos.FuncionarioCadastroDTO;
import br.com.juliocesarmj.dtos.FuncionarioConsultaDTO;
import br.com.juliocesarmj.dtos.FuncionarioEdicaoDTO;
import br.com.juliocesarmj.entities.Funcionario;
import br.com.juliocesarmj.entities.Usuario;
import br.com.juliocesarmj.enums.SituacaoFuncionario;
import br.com.juliocesarmj.helpers.DateHelper;
import br.com.juliocesarmj.reports.FuncionarioReport;
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
				erros += "O CPF j� encontra-se cadastrado. Tente outro. ";
			}
			if (funcionarioRepository.findByMatricula(dto.getMatricula()) != null) {
				erros += "A matr�cula j� encontra-se cadastrada. Tente outra. ";
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

			modelAndView.addObject("mensagem_sucesso", "Funcion�rio cadastrado com sucesso!");

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

			modelAndView.addObject("listagem_funcionarios",
					funcionarioRepository.findByDataAdmissao(dataInicio, dataFim));

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
					"Funcion�rio: " + funcionario.getNome() + ", removido com sucesso!");

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

			modelAndView.addObject("mensagem_sucesso",
					"Funcion�rio: " + funcionario.getNome() + ", atualizado com sucesso!");

			modelAndView.addObject("listagem_funcionarios", funcionarioRepository.findAll());

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Ocorreu um erro! " + e.getMessage());
		}

		modelAndView.addObject("dto", new FuncionarioConsultaDTO());
		return modelAndView;
	}

	@RequestMapping("funcionario-relatorio")
	public ModelAndView relatorio() {

		ModelAndView modelAndView = new ModelAndView("funcionario-relatorio");

		modelAndView.addObject("dto", new FuncionarioConsultaDTO());
		return modelAndView;
	}

	@RequestMapping(value = "gerarRelatorioFuncionarios", method = RequestMethod.POST)
	public ModelAndView gerarRelatorioFuncionarios(FuncionarioConsultaDTO dto, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("funcionario-relatorio");

		try {

			Date dataInicio = DateHelper.toDate(dto.getDataInicio());
			Date dataFim = DateHelper.toDate(dto.getDataFim());

			// obtendo o usuario autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

			// consulta no banco dos funcionarios
			List<Funcionario> funcionarios = funcionarioRepository.findByDataAdmissao(dataInicio, dataFim);

			// gerando o relatorio byte
			FuncionarioReport report = new FuncionarioReport();
			ByteArrayInputStream stream = report.generatePdfReport(dataInicio, dataFim, funcionarios, usuario);

			// parametrizacao do download
			response.setContentType("application/pdf"); // tipo de arquivo

			// maneira de baixar o arquivo ao clicar em gerar relatorio
			response.addHeader("Content-Disposition", "attachment; filename=funcionarios.pdf");

			// gerando o arquivo para download
			byte[] dados = stream.readAllBytes();

			OutputStream out = response.getOutputStream();
			out.write(dados, 0, dados.length);
			out.flush();
			out.close();

			response.getOutputStream().flush();

			return null;

		} catch (Exception e) {

			modelAndView.addObject("mensagem_erro", "Ocorreu um erro! " + e.getMessage());
		}

		modelAndView.addObject("dto", new FuncionarioConsultaDTO());
		return modelAndView;
	}
}
