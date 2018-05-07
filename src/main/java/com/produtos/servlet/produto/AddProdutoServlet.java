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

@WebServlet(urlPatterns = "/add-produto.do")
public class AddProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ProdutoController produtoService = new ProdutoController();
	private CategoriaController categoriaService = new CategoriaController();
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categorias", categoriaService.retrieveCategorias());
		request.getRequestDispatcher("WEB-INF/views/add-produto.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String newNome = request.getParameter("nome");
		String newDescricao = request.getParameter("descricao");
		String newPrecoCompra = request.getParameter("precoCompra");
		String newPrecoVenda = request.getParameter("precoVenda");
		String newQuantidade = request.getParameter("quantidade");
		String[] newCategorias = request.getParameterValues("categorias");
		produtoService.addProduto(new Produto(0, newNome, newDescricao, newPrecoCompra, 
				newPrecoVenda, newQuantidade), newCategorias);
		response.sendRedirect("/list-produtos.do");
	}
	
}
