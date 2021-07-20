package br.com.juliocesarmj.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.juliocesarmj.entities.Funcionario;
import br.com.juliocesarmj.entities.Usuario;
import br.com.juliocesarmj.helpers.DateHelper;

public class FuncionarioReport {
	
	public ByteArrayInputStream generatePdfReport(Date dataInicio, Date dataFim, List<Funcionario> funcionarios, Usuario usuario) throws Exception {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		//desenhando o pdf com a biblioteca itext
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		
		//abrir o documento
		document.open();
		
		document.add(new Paragraph("Relatório de Funcionários"));
		document.add(new Paragraph("Data de geração: " + DateHelper.toStringPtBr(new Date())));
		
		document.add(new Paragraph("Período de admissão de funcionários: " + DateHelper.toStringPtBr(dataInicio) + " até: " + DateHelper.toStringPtBr(dataFim)));
		
		document.add(new Paragraph("Nome do usuário: " + usuario.getNome()));
		document.add(new Paragraph("Email: " + usuario.getEmail()));
		
		
		//desenhando a tabela no pdf
		
		PdfPTable table = new PdfPTable(5);
		
		//cabecalho da tabela
		table.addCell("Nome do Funcionário");
		table.addCell("CPF");
		table.addCell("Matrícula");
		table.addCell("Data de admissão");
		table.addCell("Situação");
		
		for(Funcionario funcionario : funcionarios ) {
			table.addCell(funcionario.getNome());
			table.addCell(funcionario.getCpf());
			table.addCell(funcionario.getMatricula());
			table.addCell(DateHelper.toStringPtBr(funcionario.getDataAdmissao()));
			table.addCell(funcionario.getSituacao().toString());
		}
		
		//insere a tabela no documento pdf...
		document.add(table);
		
		//finaliza o documento
		document.close();
		writer.close();
		
		//retornando o conteúdo do documento em bytes
		return new ByteArrayInputStream(out.toByteArray());
	}
}
