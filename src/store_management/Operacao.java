package store_management;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.io.File;
import java.io.Serializable;

public class Operacao  {

    /**
	 * 
	 */
	
	private Caixa caixa;
    private Estoque estoque;
    private List<Produto> carrinho;
    private int venda;

    public Operacao(Caixa caixa, Estoque estoque) {
        this.caixa = caixa;
        this.estoque = estoque;
        carrinho = new ArrayList<>();
        estoque.carregar();

    }
    

	public void salvarDados() {
	    try {
	        FileOutputStream fileOut = new FileOutputStream("estoque.ser");
	        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	        objectOut.writeObject(carrinho);
	        objectOut.close();
	        fileOut.close();
	        System.out.println("Dados salvos.");
	    } catch (IOException e) {
	        System.out.println("Erro ao salvar os dados: " + e.getMessage());
	    }
	}

    public void registrarProduto(int codigo, String nome, double precoV, double precoC) {
        int quantidade = 0;
        estoque.adicionarProduto(codigo, nome, precoV, precoC, quantidade);
    }

    public void novaCompra(int codigo, int quantidade) {
        Produto produto = estoque.pesquisarProduto(codigo);
        if (produto != null) {
            double valorCompra = produto.getPrecoCompra() * quantidade;
            caixa.retirarValor(valorCompra);
            estoque.adicionarQuantidade(codigo, quantidade);
        } else {
            System.out.println("Produto não encontrado no estoque. É preciso registrá-lo primeiro.");
        }
    }

    public void novaVenda(int codigo, int quantidade) throws ExcecaoIndisponivel,
            ExcecaoInsuficiente, ExcecaoNaoEncontrado {

        adicionarProdutoNoCarrinho(codigo, quantidade);
        

    }

    private void adicionarProdutoNoCarrinho(int codigo, int quantidade) throws ExcecaoIndisponivel,
            ExcecaoInsuficiente, ExcecaoNaoEncontrado {

        Produto produto = estoque.pesquisarProduto(codigo);

        if (produto != null) {

            int estoqueDisponivel = produto.getQuantidade();

            if (estoqueDisponivel > 0 && quantidade > estoqueDisponivel) {

                throw new ExcecaoInsuficiente();

            } else if (estoqueDisponivel == 0) {

                throw new ExcecaoIndisponivel();

            } else {

            	Produto produtoCarrinho = new Produto(produto.getCodigo(), produto.getNome(), produto.getPrecoVenda(), produto.getPrecoCompra(), quantidade);

                carrinho.add(produtoCarrinho);
                
                System.out.println("Total: "+calcularValorTotal());

            }

        } else {

            throw new ExcecaoNaoEncontrado();
        }
    }

    public void concluirVenda(Pagamento pagamento) {

        if (!carrinho.isEmpty()) {
        	
            processarPagamento(pagamento);
            double valorTotal = calcularValorTotal();
            this.venda += 1; 
            gerarNotaVenda(valorTotal);
            salvarDados();
            carrinho.clear();

        } else {

            System.out.println("Carrinho vazio. Não é possível concluir a venda.");
        }
    }

    public void processarPagamento(Pagamento pagamento) {

        double valorPago = pagamento.calcularValor();
        System.out.println("Total: "+calcularValorTotal()+" R$");
        pagamento.efetuarPagamento();
        caixa.setTotalVendas(caixa.getTotalVendas() + valorPago);
        caixa.setLucro(caixa.getLucro() + calcularLucro(valorPago));


    }

    
    
    public double calcularValorTotal() {
    	
        double valorTotal = 0;
        
        for (Produto produto : carrinho) {
        	
            valorTotal += produto.getPrecoVenda() * produto.getQuantidade();
            
        }
        
        return valorTotal;
    }
    
    private double calcularLucro(double valorPago) {

        double valorLucro = valorPago;

        for (Produto produto : carrinho) {

            valorLucro -= produto.getPrecoCompra() * produto.getQuantidade();

        }

        return valorLucro;
    }

    public void gerarNotaVenda(double valorTotal) {
        int codigoVenda = venda;
        String nomeArquivo = "venda#"+codigoVenda+".txt";
        Path caminho = Paths.get(nomeArquivo);

        try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(caminho))) {
            printWriter.println("----- NOTA DE VENDA -----");
            printWriter.println("Código da venda: " + codigoVenda);
            printWriter.println("Data: " + new Date());
            printWriter.println("Total de produtos no carrinho: " + carrinho.size());
            printWriter.println();
            
            
            
            for (Produto produto : carrinho) {
            
                printWriter.println("Produto: " + produto.getNome());
                printWriter.println("Quantidade: " + produto.getQuantidade());
                printWriter.println("Preço unitário: " + produto.getPrecoVenda());
                printWriter.println("Total: " + produto.getPrecoVenda() * produto.getQuantidade());
                printWriter.println();
            }

            printWriter.println("Valor total da venda: " + valorTotal);
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }

    public void cancelarVenda() {
        if (!carrinho.isEmpty()) {

            for (Produto produto : carrinho) {
                estoque.adicionarQuantidade(produto.getCodigo(), produto.getQuantidade());
            }

            carrinho.clear();

            System.out.println("Venda cancelada. Carrinho esvaziado e estoque atualizado.");
        } else {
            System.out.println("Não há venda para cancelar. O carrinho está vazio.");
        }
    }
    
    public void Sair() {
    	estoque.salvarDados();
    	System.out.println("Programa finalizado.");
    }
    
    
}