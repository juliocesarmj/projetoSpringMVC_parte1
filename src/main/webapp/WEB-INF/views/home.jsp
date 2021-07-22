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
							<li><a class="dropdown-item" href="/projetoSpringMVC/funcionario-cadastro">Cadastrar
									Funcionários</a></li>
							<li><a class="dropdown-item" href="/projetoSpringMVC/funcionario-consulta">Consultar
									Funcionários</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/projetoSpringMVC/funcionario-relatorio">Relatório de
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
			<p class="px-3 mt-3">Sistema desenvolvido em Spring MVC com Spring
				JDBC, Bootstrap e Jquery</p>
		</nav>
	</div>
	<div class="container mt-4">
		<div id="grafico">
		
		</div>
	</div>
	<script src="resources/js/bootstrap.min.js"></script>
	
	<!-- Referencia Jquery -->
	<script src="resources/js/jquery-3.6.0.min.js" ></script>
	
	<!-- Referencia dos arquivos Highcharts -->
	<script src="resources/js/highcharts.js" ></script>
	<script src="resources/js/highcharts-3d.js" ></script>
	<script src="resources/js/exporting.js" ></script>
	<script src="resources/js/export-data.js" ></script>
	
	<script>
	
	$(document).ready(function() {
		
		var dados = [
			{data: [${qtd_admitido}], name: 'Funcionários Admitidos'},
			{data: [${qtd_afastado}], name: 'Funcionários Afastados'},
			{data: [${qtd_ferias}], name: 'Funcionários de Férias'},
			{data: [${qtd_demitido}], name: 'Funcionários Demitidos'},
			
		];
		
		var array = [];
		
		for(var i = 0; i < dados.length; i++) {
			array.push([dados[i].name, dados[i].data[0]])
		}
		
		new Highcharts.chart({
			chart: {
				type: 'pie',
				renderTo: 'grafico'
			},
			title: {
				text: 'Gráfico de Funcionários por situação' 
			},
			subtitle: {
				text: 'Total de funcionários por situação cadastrada'
			},
			credits: { enabled: false},
			plotOptions: {
				pie: {
					innerSize: '60%'
				}
			},
			series: [
				{data: array},
			]
		})
		
	})
	</script>
</body>
</html>
