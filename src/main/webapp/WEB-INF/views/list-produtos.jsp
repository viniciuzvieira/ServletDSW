<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

	<div class="container">
		<H1>Welcome</H1>
		<p>
		<H4>Produtos do Sistema</H4>
			<c:forEach items="${produtos}" var="produto">
				<div class="col-lg-3 col-sm-12">
					<br>
					<h4 class="item"><b>${produto.nome}</b></h4>
					<h5 class="item">${produto.descricao}</h5>
					<h5 class="item">${produto.precoCompra} - ${produto.precoVenda}</h5>
					<h5 class="item">${produto.quantidade}</h5>
					<h5 class="item">
						<c:set var = "nome" scope = "session" value = "${produto.nome}"/>
						<c:forEach items="${categorias}" var="categoria">
							<c:set var = "nomeProduto" scope = "session" value ="${categoria.nomeProduto}"/>
							<c:if test = "${nome == nomeProduto}">
								${categoria.nomeCategoria}
							</c:if>
						</c:forEach>
					</h5>
					<h5 class="item">
						<a class="btn btn-warning"
						href="edite-produto.do?id=${produto.id}&nome=${produto.nome}
						&descricao=${produto.descricao}&precoCompra=${produto.precoCompra}
						&precoVenda=${produto.precoVenda}&quantidade=${produto.quantidade}">Editar</a>
						<a class="btn btn-danger" 
						href="/delete-produto.do?id=${produto.id}&nome=${produto.nome}
						&descricao=${produto.descricao}&precoCompra=${produto.precoCompra}
						&precoVenda=${produto.precoVenda}&quantidade=${produto.quantidade}">Delete</a>
					</h5>
				</div>
			</c:forEach>
	</div>
	<p>
		<font color="red">${errorMessage}</font>
	</p>
	<div class="col-lg-12 col-sm-12">
		<form method="POST" action="/consulta-produto.do">
			<a class="btn btn-success col-lg-2 col-sm-3"  href="/add-produto.do">Adicionar Novo Produto</a>
			<span></span>
			<fieldset class="form-group col-lg-4 col-sm-6">
				<input name="nomeConsulta" type="text" class="form-control"/>
			</fieldset>
			<fieldset class="col-lg-1 col-sm-1">
				<input name="consulta" type="submit" value="Consultar" class="btn btn-success"/>
			</fieldset>
			<p></p>
		</form>
	</div>
	
<%@ include file="../common/footer.jspf"%>