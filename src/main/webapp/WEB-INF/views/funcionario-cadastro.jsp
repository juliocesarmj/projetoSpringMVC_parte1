<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de funcionários</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<style>
input.error, select.error {
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
		<h5>Cadastro de funcionários</h5>
		<hr>
		<form id="formcadastro" action="cadastrarFuncionario" method="post">
			<div class="row">
				<div class="col-md-4">

					<label>Nome do funcionário:</label>
					<form:input path="dto.nome" id="nome" name="nome"
						class="form-control" type="text" placeholder="Ex: João da Silva" />
					<br> <label>CPF:</label>
					<form:input path="dto.cpf" id="cpf" name="cpf" class="form-control"
						type="text" placeholder="Ex: 111.111.111-11" />
					<br> <label>Matrícula:</label>
					<form:input path="dto.matricula" id="matricula" name="matricula"
						class="form-control" type="text" placeholder="Ex: 2021-0001" />
					<br>
				</div>
				<div class="col-md-4">
					<label>Data de admissão:</label>
					<form:input path="dto.dataAdmissao" id="dataadmissao"
						name="dataadmissao" class="form-control" type="date" />
					<br> <label>Situação do funcionário:</label>
					<form:select path="dto.situacao" id="situacao" name="situacao"
						class="form-select">
						<option value="">Selecione uma opção</option>
						<form:options items="${situacoes}"/>
						
					</form:select>
					<br>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="d-grid gap-2">
						<button class="btn btn-success" type="submit">Realizar
							cadastro</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery-3.6.0.min.js"></script>
	<script src="resources/js/jquery.maskedinput.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>

	<script>
		$(document).ready(function() {

			$("#cpf").mask('999.999.999-99');
			$("#matricula").mask("9999-9999");

			$("#formcadastro").validate({
				rules : {
					"nome" : {
						required : true,
						minlength : 6,
						maxlength : 150,
					},
					"cpf" : {
						required : true
					},
					"matricula" : {
						required : true
					},
					"dataadmissao" : {
						required : true
					},
					"situacao" : {
						required : true
					}

				}
			})
		})
	</script>

</body>
</html>
