<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Funcionários</title>
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
			<a class="navbar-brand" href="/projetoSpringMVC/home">Cadastro de
				Funcionários</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/projetoSpringMVC/home">Página Inicial</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Gerenciar Funcionários </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item"
								href="/projetoSpringMVC/funcionario-cadastro">Cadastrar
									Funcionários</a></li>
							<li><a class="dropdown-item"
								href="/projetoSpringMVC/funcionario-consulta">Consultar
									Funcionários</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item"
								href="/projetoSpringMVC/funcionario-relatorio">Relatório de
									Funcionários</a></li>
						</ul></li>
				</ul>
				<form class="d-flex">
					<span class="text-white mt-3" style="margin-right: 20px">
						<small>${usuario_autenticado.nome} (${usuario_autenticado.email})</small> 
					</span>
					<a href="/projetoSpringMVC/logout" class="btn btn-outline-secondary mt-2"
					onclick="return confirm('Deseja realmente sair do sistema?')">Sair do sistema</a>
				</form>
			</div>
		</div>
	</nav>
	<div class="nav-scroller bg-body shadow-sm">
		<nav class="nav nav-underline">
			<p class="px-3 mt-3">Sistema desenvolvido em Spring MVC com
				Spring JDBC, Bootstrap e Jquery</p>
		</nav>
	</div>

	<c:if test="${not empty mensagem_sucesso}">
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Sucesso!</strong> ${mensagem_sucesso}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<c:if test="${not empty mensagem_erro}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Erro!</strong> ${mensagem_erro}
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
					<form:input path="dto.nome" id="nome" name="nome" type="text"
						placeholder="Ex: João da Silva" class="form-control" />
					<br> <label>CPF:</label>
					<form:input path="dto.cpf" id="cpf" name="cpf" type="text"
						placeholder="Ex: 111.111.111-11" class="form-control" />
					<br> <label>Mátricula:</label>
					<form:input path="dto.matricula" id="matricula" name="matricula"
						type="text" placeholder="Ex: 1111-1111" class="form-control" />
					<br>
				</div>

				<div class="col-md-4">

					<label>Data de admissão:</label>
					<form:input path="dto.dataAdmissao" id="dataAdmissao"
						name="dataAdmissao" type="date" class="form-control" />
					<br> <label>Situação do funcionário:</label>
					<form:select path="dto.situacao" class="form-select" id="situacao"
						name="situacao">
						<option value="">Selecione uma opção</option>
						<form:options items="${situacoes}"/>
					</form:select>
					<br>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 d-grid gap-2">
					<button type="submit" class="btn btn-success">Realizar
						cadastro</button>
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

			$("#cpf").mask("999.999.999-99");
			$("#matricula").mask("9999-9999");

			$("#formcadastro").validate({
				rules : {
					"nome" : {
						required : true,
						minlength : 6,
						maxlength : 150
					},
					"cpf" : {
						required : true
					},
					"matricula" : {
						required : true
					},
					"dataAdmissao" : {
						required : true
					},
					"situacao" : {
						required : true
					}
				}
			})
		});
	</script>

</body>
</html>
