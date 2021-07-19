<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/projetoSpringMVC/">Cadastro de
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
						aria-current="page" href="/projetoSpringMVC/">Página Inicial</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Gerenciar Funcionários </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/projetoSpringMVC/funcionario-cadastro">Cadastrar
									Funcionários</a></li>
							<li><a class="dropdown-item" href="/projetoSpringMVC/funcionario-consulta">Consultar
									Funcionários</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/projetoSpringMVC/funcionario-relatorio">Relatório de
									Funcionários</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="nav-scroller bg-body shadow-sm">
		<nav class="nav nav-underline">
			<p class="px-3 mt-3">Sistema desenvolvido em Spring MVC com Spring
				JDBC, Bootstrap e Jquery</p>
		</nav>
	</div>
	<div class="container mt-4">
		<h5>Seja bem vindo ao projeto</h5>
		<hr>
	</div>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
