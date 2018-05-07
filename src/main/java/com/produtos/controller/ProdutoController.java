package com.produtos.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.produtos.factory.ConnectionFactory;
import com.produtos.model.Produto;

public class ProdutoController {
	
	CategoriaController categoriaService = new CategoriaController();

	public Produto getProduto(String nome) {
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			Statement myStmt = conexao.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"SELECT * FROM PRODUTOBD.PRODUTO " 
				  + "WHERE NOME = '" + nome + "'");
			while (myRs.next()) {
				return new Produto(
								myRs.getInt("ID"),
								myRs.getString("NOME"), 
								myRs.getString("DESCRICAO"), 
								myRs.getString("PRECO_COMPRA"),
								myRs.getString("PRECO_VENDA"), 
								myRs.getString("QUANTIDADE"));
			}
			conexao.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public List<Produto> retrieveProdutos() {
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			Statement myStmt = conexao.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM PRODUTOBD.PRODUTO");

			List<Produto> produtos = new ArrayList<Produto>();

			if (myRs != null) {
				while (myRs.next()) {
					produtos.add(
							new Produto(
									myRs.getInt("ID"),
									myRs.getString("NOME"), 
									myRs.getString("DESCRICAO"), 
									myRs.getString("PRECO_COMPRA"),
									myRs.getString("PRECO_VENDA"), 
									myRs.getString("QUANTIDADE")));
				}
			} else {
				produtos.add(new Produto(0, "", "", "", "", ""));
			}
			conexao.close();
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Produto> consultaProdutos(String nome) {
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			Statement myStmt = conexao.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM PRODUTOBD.PRODUTO "
					+ "WHERE NOME = '" + nome + "'");

			List<Produto> produtos = new ArrayList<Produto>();

			if (myRs != null) {
				while (myRs.next()) {
					produtos.add(
							new Produto(
									myRs.getInt("ID"),
									myRs.getString("NOME"), 
									myRs.getString("DESCRICAO"), 
									myRs.getString("PRECO_COMPRA"),
									myRs.getString("PRECO_VENDA"), 
									myRs.getString("QUANTIDADE")));
				}
			} else {
				produtos.add(new Produto(0, "", "", "", "", ""));
			}
			conexao.close();
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void editeProduto(Produto produto, String nomeAntigo) {
		try {
			Produto oldProduto = getProduto(nomeAntigo);
			Connection conexao = new ConnectionFactory().getConnection();
			PreparedStatement myStmt = conexao.prepareStatement(
					"UPDATE "
				  + "PRODUTOBD.PRODUTO " 
				  + "SET NOME = ?, "
				  + "DESCRICAO = ?, "
				  + "PRECO_COMPRA = ?, "
				  + "PRECO_VENDA = ?, "
				  + "QUANTIDADE = ? "
			      + "WHERE ID = ?");

			myStmt.setString(1, produto.getNome());
			myStmt.setString(2, produto.getDescricao());
			myStmt.setString(3, produto.getPrecoCompra());
			myStmt.setString(4, produto.getPrecoVenda());
			myStmt.setString(5, produto.getQuantidade());
			myStmt.setInt(6, oldProduto.getId());

			myStmt.executeUpdate();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addProduto(Produto produto, String[] categorias) {
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			PreparedStatement myStmt = conexao.prepareStatement(
					"INSERT INTO "
				  + "PRODUTOBD.PRODUTO "
				  + "(NOME, DESCRICAO, PRECO_COMPRA, PRECO_VENDA, QUANTIDADE, DT_CADASTRO) "
				  + "VALUES (?, ?, ?, ?, ?, ?)");

			myStmt.setString(1, produto.getNome());
			myStmt.setString(2, produto.getDescricao());
			myStmt.setString(3, produto.getPrecoCompra());
			myStmt.setString(4, produto.getPrecoVenda());
			myStmt.setString(5, produto.getQuantidade());
			myStmt.setDate(6, new Date(System.currentTimeMillis()));

			myStmt.executeUpdate();
			conexao.close();
			
			categoriaService.addCategoria(categorias);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteProduto(Produto produto) {
		try {
			System.out.println(produto.getNome());
			Connection conexao = new ConnectionFactory().getConnection();
			Statement myStmt = conexao.createStatement();
			myStmt.executeUpdate("DELETE "
					           + "FROM "
					           + "PRODUTOBD.PRODUTO "
					           + "WHERE "
					           + "NOME = '" + produto.getNome() + "'");
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getMaxProduto() {
		try {
			Connection conexao = new ConnectionFactory().getConnection();
			Statement myStmt = conexao.createStatement();
			ResultSet rs = myStmt.executeQuery("SELECT max(ID) FROM PRODUTOBD.PRODUTO");
			int value = 0;
			if(rs.next()) {
				value = rs.getInt(1);
			} else {
				value = 0;
			}
			conexao.close();
			return value;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

}
