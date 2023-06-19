package store_management;
import java.io.File;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class Estoque implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Produto> listaProdutos;

    public Estoque() {
        listaProdutos = new ArrayList<>();

    }
    
    public void carregar () {
    	File arquivoEstoque = new File("estoque.ser");
        if (!arquivoEstoque.exists()) {
            try {
                arquivoEstoque.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo de estoque: " + e.getMessage());
            }
        }

        try {
            FileInputStream fileIn = new FileInputStream("estoque.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            List<Produto> produtos = (List<Produto>) objectIn.readObject();
            listaProdutos.addAll(produtos);
            objectIn.close();
            fileIn.close();
       
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os produtos do estoque: " + e.getMessage());
        }
    }
    
    public void salvarDados() {
	    try {
	        FileOutputStream fileOut = new FileOutputStream("estoque.ser");
	        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	        objectOut.writeObject(listaProdutos);
	        objectOut.close();
	        fileOut.close();
	        System.out.println("Dados salvos.");
	    } catch (IOException e) {
	        System.out.println("Erro ao salvar os dados: " + e.getMessage());
	    }
	}

    public void adicionarProduto(int codigo, String nome, double precoV, double precoC, int quantidade) {
        Produto produto = new Produto(codigo, nome, precoV, precoC, quantidade);
        listaProdutos.add(produto);
    }

    public void removerProduto(Produto produto) {
        listaProdutos.remove(produto);
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public Produto pesquisarProduto(int codigo) {
        for (Produto produto : listaProdutos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public void adicionarQuantidade(int cod, int qnt) {
        Produto produto = pesquisarProduto(cod);
        if (produto != null) {
            produto.setQuantidade(produto.getQuantidade() + qnt);
        }
    }
}