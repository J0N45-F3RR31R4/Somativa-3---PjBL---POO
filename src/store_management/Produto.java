package store_management;

import java.io.Serializable;

public class Produto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codigo;
    private String nome;
    private double precoVenda;
    private double precoCompra;
    private int quantidade;

    public Produto(int codigo, String nome, double precoVenda, double precoCompra, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }



    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void AumentaQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

	public int getQuantidade() {
		// TODO Auto-generated method stub
		return quantidade;
	}
}

