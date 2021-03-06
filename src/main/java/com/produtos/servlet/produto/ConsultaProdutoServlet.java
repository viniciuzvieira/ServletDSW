package com.produtos.servlet.produto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.produtos.controller.ProdutoCategoriaController;
import com.produtos.controller.ProdutoController;

@WebServlet(urlPatterns = "/consulta-produto.do")
public class ConsultaProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ProdutoController produtoService = new ProdutoController();
	private ProdutoCategoriaController produtoCategoriaService = new ProdutoCategoriaController();
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String nomeConsulta = request.getParameter("nomeConsulta");
		request.setAttribute("produtos", produtoService.consultaProdutos(nomeConsulta));
		request.setAttribute("categorias", produtoCategoriaService.getCategorias());
		request.getRequestDispatcher("WEB-INF/views/list-produtos.jsp").forward(request, response);
	}

}
