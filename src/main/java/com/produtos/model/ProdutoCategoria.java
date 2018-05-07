package com.produtos.model;

public class ProdutoCategoria {
	
	private String nomeCategoria;
	private String nomeProduto;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoCategoria other = (ProdutoCategoria) obj;
		if (nomeCategoria == null) {
			if (other.nomeCategoria != null)
				return false;
		} else if (!nomeCategoria.equals(other.nomeCategoria))
			return false;
		if (nomeProduto == null) {
			if (other.nomeProduto != null)
				return false;
		} else if (!nomeProduto.equals(other.nomeProduto))
			return false;
		return true;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public ProdutoCategoria(String nomeCategoria, String nomeProduto) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.nomeProduto = nomeProduto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCategoria == null) ? 0 : nomeCategoria.hashCode());
		result = prime * result + ((nomeProduto == null) ? 0 : nomeProduto.hashCode());
		return result;
	}

}
