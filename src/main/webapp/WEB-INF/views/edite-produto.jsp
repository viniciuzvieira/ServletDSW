<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

	<div class="container">
		Editar Produto
		<form method="POST" action="/atualiza-produto.do">
			<fieldset class="form-group">
				<label>Nome</label>
				<input name="nome" type="text" value="${produto.nome}" class="form-control"/>
			</fieldset>
			<fieldset class="form-group">
				<label>Descricao</label>
				<input name="descricao" type="text" value="${produto.descricao}" class="form-control"/>
			</fieldset>
			<fieldset class="form-group">
				<label>Preco de compra</label>
				<input name="precoCompra" type="text" value="${produto.precoCompra}" class="form-control"/>
			</fieldset>  
			<fieldset class="form-group">
				<label>Preco de venda</label>
				<input name="precoVenda" value="${produto.precoVenda}" type="text" class="form-control"/>
			</fieldset> 
			<fieldset class="form-group">
				<label>Quantidade</label>
				<input name="quantidade" type="text" value="${produto.quantidade}" class="form-control"/>
			</fieldset>
			<fieldset class="form-group">
				<label>Categoria</label>
				<select name="categorias" multiple class="form-control"
					style="height: 100px">
					<c:forEach items="${categorias}" var="categoria">
						<option value="${categoria.id}">${categoria.nome}</option>
					</c:forEach>
				</select>
			</fieldset>   
			<input name="edit" type="submit" value="Submit" class="btn btn-success"/>
			<p></p>
		</form>
	</div>

<%@ include file="../common/footer.jspf"%>
