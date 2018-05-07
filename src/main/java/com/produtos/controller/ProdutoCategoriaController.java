package com.produtos.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.produtos.factory.ConnectionFactory;
import com.produtos.model.ProdutoCategoria;

public class ProdutoCategoriaController {
	
	public List<ProdutoCategoria> getCategorias() {
		try {
			List<ProdutoCategoria> categorias = new ArrayList<ProdutoCategoria>();
			List<String> idProdutos = new ArrayList<String>();
			List<String> idCategorias = new ArrayList<String>();
			Connection conexao = new ConnectionFactory().getConnection();
			Statement myStmt = conexao.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM PRODUTOBD.PRODUTO_CATEGORIA");
			if (myRs != null) {
				while (myRs.next()) {
					idProdutos.add(myRs.getString("ID_PRODUTO"));
					idCategorias.add(myRs.getString("ID_CATEGORIA"));
				}
				myRs.close();
				myStmt.close();
				conexao.close();
				int i = 0;
				conexao = new ConnectionFactory().getConnection();
				while(idProdutos.size() > i) {
					Statement produtoStmt = conexao.createStatement();
					ResultSet produtoRs = produtoStmt.executeQuery("SELECT NOME FROM PRODUTOBD.PRODUTO "
							+ "WHERE ID = " + idProdutos.get(i));
					String produto = "";
					while(produtoRs.next()) {
						produto = produtoRs.getString("nome");
					}
					Statement categoriaStmt = conexao.createStatement();
					ResultSet categoriaRs = categoriaStmt.executeQuery(
							  "SELECT NOME FROM PRODUTOBD.CATEGORIA "
							+ "WHERE ID = " + idCategorias.get(i));
					String categoria = "";
					while(categoriaRs.next()) {
						categoria = categoriaRs.getString("nome");
					}
					categorias.add(new ProdutoCategoria(categoria, produto));
					produtoRs.close();
					produtoStmt.close();
					categoriaRs.close();
					categoriaStmt.close();
					i++;
				}
				conexao.close();
				return categorias;
			} else {
				categorias.add(new ProdutoCategoria("", ""));
				return categorias;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
