package com.produtos.servlet.produto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.produtos.controller.CategoriaController;
import com.produtos.controller.ProdutoController;
import com.produtos.model.Produto;

@WebServlet(urlPatterns = "/atualiza-produto.do")
public class AtualizaProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ProdutoController produtoService = new ProdutoController();
	private CategoriaController categoriaService = new CategoriaController();
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String oldNome = request.getSession().getAttribute("nomeAntigo").toString();
		Integer id = produtoService.getProduto(oldNome).getId();
		String newNome = request.getParameter("nome");
		String newDescricao = request.getParameter("descricao");
		String newPrecoCompra = request.getParameter("precoCompra");
		String newPrecoVenda = request.getParameter("precoVenda");
		String newQuantidade = request.getParameter("quantidade");
		String[] newCategorias = request.getParameterValues("categorias");
		Produto produto = new Produto(
								id,
								newNome, 
								newDescricao, 
								newPrecoCompra, 
								newPrecoVenda, 
								newQuantidade);
		categoriaService.deleteCategoria(produto);
		categoriaService.atualizaCategoria(newCategorias, produto);
		produtoService.editeProduto(produto, oldNome);
		request.getSession().invalidate();
		response.sendRedirect("/list-produtos.do");
	}

}
