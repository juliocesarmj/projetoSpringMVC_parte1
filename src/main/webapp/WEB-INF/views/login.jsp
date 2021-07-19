<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Funcionários</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<style>
input.error {
	border: 1px solid red;
}

label.error {
	color: red;
}
</style>
</head>
<body style="background: #eee">
	<div class="container mt-4">

		<div class="row">

			<div class="col-md-4 offset-4">
				<div class="card mt-5">
					<div class="card-body text-center">
						<img
							src="https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png"
							style="width: 260px;" class="mb-2" />
						<hr>
						<h5 class="card-title">Acesso ao sistema</h5>

						<form id="formlogin">

							<label>Email de acesso:</label>
							<form:input path="dto.email" id="email" name="email" type="text"
								class="form-control" />
							<br> <label>Senha de acesso:</label>
							<form:input path="dto.senha" id="senha" name="senha"
								type="password" class="form-control" />
							<br>

							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-dark">Entrar</button>
							</div>


						</form>
						<c:if test="${not empty mensagem_erro}">
							<div class="alert alert-danger alert-dismissible fade show"
								role="alert">
								<strong>Erro!</strong> ${mensagem_erro}
								<button type="button" class="btn-close" data-bs-dismiss="alert"
									aria-label="Close"></button>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>


	</div>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery-3.6.0.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>
	<script>
		$(document).ready(function() {

			$("#formlogin").validate({
				rules : {
					"email" : {
						required : true,
						email: true
					},
					"senha" : {
						required : true,
						minlength: 8,
						maxlength: 20
					}
				}
			})
		});
	</script>

</body>
</html>
