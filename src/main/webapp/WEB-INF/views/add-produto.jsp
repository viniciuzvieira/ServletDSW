<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

	<div class="container">
		Novo Produto
		<form method="POST" action="/add-produto.do">
			<fieldset class="form-group">
				<label>Nome</label>
				<input name="nome" type="text" class="form-control"/>
			</fieldset>
			<fieldset class="form-group">
				<label>Descrição</label>
				<input name="descricao" type="text" class="form-control"/>
			</fieldset>
			<fieldset class="form-group">
				<label>Preço de compra</label>
				<input name="precoCompra" type="text" class="form-control"/>
			</fieldset>  
			<fieldset class="form-group">
				<label>Preço de venda</label>
				<input name="precoVenda" type="text" class="form-control"/>
			</fieldset> 
			<fieldset class="form-group">
				<label>Quantidade</label>
				<input name="quantidade" type="text" class="form-control"/>
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
			<input name="add" type="submit" value="Submit" class="btn btn-success"/>
			<p></p>
		</form>
	</div>

<%@ include file="../common/footer.jspf"%>
