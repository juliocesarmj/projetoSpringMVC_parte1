<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de Funcionários</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css">
<style>
input.error {
	border: 1px solid red;
}

label.error {
	color: red;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/projetoSpringExercicio/">Cadastro
				de Funcionários</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/projetoSpringExercicio/">Página
							inicial</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Gerenciar Funcionários </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item"
								href="/projetoSpringMVC001/funcionario-cadastro">Cadastrar
									funcionários</a></li>
							<li><a class="dropdown-item"
								href="/projetoSpringMVC001/funcionario-consulta">Consultar
									funcionários</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item"
								href="/projetoSpringMVC001/funcionario-relatorio">Relatório
									de funcionários</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="nav-scroller bg-body shadow-sm">
		<nav class="nav nav-underline">
			<p class="px-3 mt-3">Sistema desenvolvido em Spring MVC, Spring
				JDBC, Bootstrap e Jquery</p>
		</nav>
	</div>

	<c:if test="${not empty mensagem_sucesso }">
		<!-- Mensagem de sucesso -->
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Sucesso!</strong> ${mensagem_sucesso }
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<c:if test="${not empty mensagem_erro }">
		<!-- Mensagem de erro -->
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Erro!</strong> ${mensagem_erro }
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<div class="container mt-4">
		<h5>Consulta de funcionários</h5>
		<hr>
		<form id="formconsulta" action="consultarFuncionarios" method="post">

			<div class="row">

				<div class="col-md-3">
					<input type="date" id="dataInicio"
						name="dataInicio" class="form-control" />
				</div>

				<div class="col-md-3">
					<input type="date" id="dataFim"
						name="dataFim" class="form-control" />
				</div>

				<div class="col-md-4">
					<input type="submit" value="Realizar pesquisa"
						class="btn btn-success">
				</div>
			</div>

		</form>
		<table class="table table-striped" id="tabelafuncionarios">
			<thead>
				<tr>
					<th>Nome do funcionário</th>
					<th>CPF</th>
					<th>Matrícula</th>
					<th>Data de admissão</th>
					<th>Situação</th>
					<th>Operações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listagem_funcionarios}" var="funcionario">
					<tr>
						<td>${funcionario.nome}</td>
						<td>${funcionario.cpf}</td>
						<td>${funcionario.matricula}</td>
						<td><fmt:formatDate pattern="EEE dd/MM/yyyy"
								value="${funcionario.dataAdmissao}" /></td>
						<td>${funcionario.situacao}</td>
						<td><a
							href="/projetoSpringMVC001/funcionario-edicao?id=${funcionario.idFuncionario}"
							class="btn btn-primary btn-sm">Editar</a> <a
							href="/projetoSpringMVC001/excluirFuncionario?id=${funcionario.idFuncionario}"
							onclick="return confirm('Deseja realmente excluir o funcionário ${funcionario.nome}?');"
							class="btn btn-danger btn-sm">Excluir</a></td>
					</tr>
				</c:forEach>

			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">Quantidade de funcionários:
						${listagem_funcionarios.size()}</td>
				</tr>
			</tfoot>
		</table>
	</div>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery-3.6.0.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>
	<script src="resources/js/jquery.dataTables.min.js"></script>

	<script>
		$(document)
				.ready(
						function() {

							$("#formconsulta").validate({
								rules : {
									"dataInicio" : {
										required : true
									},
									"dataFim" : {
										required : true
									}

								}
							});

							$("#tabelafuncionarios")
									.DataTable(
											{
												language : {
													url : 'https://cdn.datatables.net/plug-ins/1.10.25/i18n/Portuguese-Brasil.json'
												}
											});

						})
	</script>
</body>
</html>
