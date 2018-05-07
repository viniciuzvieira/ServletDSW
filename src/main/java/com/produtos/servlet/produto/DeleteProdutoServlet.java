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

@WebServlet(urlPatterns = "/delete-produto.do")
public class DeleteProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProdutoController produtoService = new ProdutoController();
	private CategoriaController categoriaService = new CategoriaController();

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {		
		Produto produto = new Produto(
				Integer.parseInt(request.getParameter("id")),
				request.getParameter("nome"),
				request.getParameter("descricao"), 
				request.getParameter("precoCompra"), 
				request.getParameter("precoVenda"),
				request.getParameter("quantidade"));
		categoriaService.deleteCategoria(produto);
		produtoService.deleteProduto(produto);
		response.sendRedirect("/list-produtos.do");
	}
}