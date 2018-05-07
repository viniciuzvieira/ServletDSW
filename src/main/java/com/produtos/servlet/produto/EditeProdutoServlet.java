package com.produtos.servlet.produto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.produtos.controller.CategoriaController;
import com.produtos.controller.ProdutoController;

@WebServlet(urlPatterns = "/edite-produto.do")
public class EditeProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ProdutoController produtoService = new ProdutoController();
	private CategoriaController categoriaService = new CategoriaController();

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("produto", produtoService.getProduto(request.getParameter("nome")));
		request.setAttribute("categorias", categoriaService.retrieveCategorias());
		request.getSession().setAttribute("nomeAntigo", request.getParameter("nome"));
		request.getRequestDispatcher("WEB-INF/views/edite-produto.jsp").forward(request, response);
	}

}
