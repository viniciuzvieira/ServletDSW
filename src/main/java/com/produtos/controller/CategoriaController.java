package com.produtos.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import com.produtos.factory.ConnectionFactory;
import com.produtos.model.Categoria;
import com.produtos.model.Produto;

public class CategoriaController {
	
	public List<Categoria> retrieveCategorias() {
		
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			Statement myStmt = conexao.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM PRODUTOBD.CATEGORIA");

			List<Categoria> categorias = new ArrayList<Categoria>();
			
			if (myRs != null) {
				while (myRs.next()) {
					categorias.add(new Categoria(
								myRs.getInt("ID"),
								myRs.getString("NOME")
							));
				}
			} else {
				categorias.add(new Categoria(0, ""));
			}
			conexao.close();
			return categorias;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void deleteCategoria(Produto produto) {
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			Statement myStmt = conexao.createStatement();
			myStmt.executeUpdate("DELETE "
			           + "FROM "
			           + "PRODUTOBD.PRODUTO_CATEGORIA "
			           + "WHERE "
			           + "ID_PRODUTO = '" + produto.getId() + "'");
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addCategoria(String[] categorias) {
		Connection conexao_categoria = new ConnectionFactory().getConnection();
		
		if(categorias != null) {
			Integer ID_PRODUTO = new ProdutoController().getMaxProduto();
			int i = 0;
			try {
				while(categorias.length > i) {
					PreparedStatement myStmt = conexao_categoria.prepareStatement(
							"INSERT INTO "
						+	"PRODUTOBD.PRODUTO_CATEGORIA "
						+	"(ID_PRODUTO, ID_CATEGORIA) "
						+	"VALUES (?, ?)");
					myStmt.setInt(1, ID_PRODUTO);
					myStmt.setInt(2, Integer.parseInt(categorias[i]));
					myStmt.executeUpdate();
					i++;
				}
				conexao_categoria.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void atualizaCategoria(String[] categorias, Produto produto) {
		Connection conexao_categoria = new ConnectionFactory().getConnection();
		
		if(categorias != null) {
			int i = 0;
			try {
				while(categorias.length > i) {
					PreparedStatement myStmt = conexao_categoria.prepareStatement(
							"INSERT INTO "
						+	"PRODUTOBD.PRODUTO_CATEGORIA "
						+	"(ID_PRODUTO, ID_CATEGORIA) "
						+	"VALUES (?, ?)");
					myStmt.setInt(1, produto.getId());
					myStmt.setInt(2, Integer.parseInt(categorias[i]));
					myStmt.executeUpdate();
					i++;
				}
				conexao_categoria.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
