package store_management;
import java.io.Serializable;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);

        Caixa caixa = new Caixa(1000.0, "Banco X", "123456");

        Estoque estoque = new Estoque();

        Operacao operacao = new Operacao(caixa, estoque);
        
 
        
    
        
        boolean executando = true;

        while (executando) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Registrar Produto");
            System.out.println("2. Nova Venda");
            System.out.println("3. Cancelar Venda");
            System.out.println("4. Adicionar Produto");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o código do produto:");
                    int codigoProduto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome do produto:");
                    String nomeProduto = scanner.nextLine();
                    System.out.println("Digite o preço de compra do produto:");
                    double precoCompra = scanner.nextDouble();
                    System.out.println("Digite o preço de venda do produto:");
                    double precoVenda = scanner.nextDouble();
                    operacao.registrarProduto(codigoProduto, nomeProduto, precoVenda, precoCompra);
                    break;
                case 2:
                    boolean novaVenda = true;
                    while (novaVenda) {
                        System.out.println("Menu de Venda:");
                        System.out.println("1. Adicionar Item ao carrinho");
                        System.out.println("2. Concluir Venda");
                        System.out.println("3. Voltar");

                        int vendaOpcao = scanner.nextInt();
                        scanner.nextLine();

                        switch (vendaOpcao) {
	                        case 1:
	                            System.out.println("Digite o código do produto:");
	                            int codigoPr = scanner.nextInt();
	                            System.out.println("Digite a quantidade:");
	                            int quantidadePr = scanner.nextInt();
	                            try {
	                                operacao.novaVenda(codigoPr, quantidadePr);
	                            } catch (ExcecaoIndisponivel e) {
	                            	System.out.println(e.getMessage());
	                            } catch (ExcecaoInsuficiente e) {
	                            	System.out.println(e.getMessage());
	                            } catch (ExcecaoNaoEncontrado e) {
	                            	System.out.println(e.getMessage());
	                            }
	                            break;
                            case 2:
                                System.out.println("Selecione o método de pagamento:");
                                System.out.println("1. Dinheiro");
                                System.out.println("2. Cartão de Crédito");
                                System.out.println("3. Cartão de Débito");
                                int metodoPagamento = scanner.nextInt();
                                scanner.nextLine();

                                Pagamento pagamento = null;
                                switch (metodoPagamento) {
                                    case 1:
                                        System.out.println("Digite o valor em dinheiro:");
                                        double valorDinheiro = scanner.nextDouble();
                                        double total = operacao.calcularValorTotal();
                                        pagamento = new Dinheiro(total, valorDinheiro);
                                        break;
                                    case 2:
                                        System.out.println("Digite o número do cartão de crédito:");
                                        String numeroCartaoCredito = scanner.nextLine();
                                    
                                        double valorCompraCredito = operacao.calcularValorTotal();
                                        scanner.nextLine();
                                        System.out.println("Digite a data de validade:");
                                        String dataValidadeCredito = scanner.nextLine();
                                        System.out.println("Digite o código de segurança:");
                                        String codigoSegurancaCredito = scanner.nextLine();
                                        System.out.println("Digite o número de parcelas:");
                                        int vezesCredito = scanner.nextInt();
                                        pagamento = new Credito(valorCompraCredito, vezesCredito, numeroCartaoCredito, dataValidadeCredito, codigoSegurancaCredito);
                                        break;
                                    case 3:
                                        System.out.println("Digite o banco:");
                                        String bancoDebito = scanner.nextLine();
                                        System.out.println("Digite a agência:");
                                        String agenciaDebito = scanner.nextLine();
                                        System.out.println("Digite a conta:");
                                        String contaDebito = scanner.nextLine();
                                     
                                        double valorCompraDebito = operacao.calcularValorTotal();
                                        scanner.nextLine();
                                        pagamento = new Debito(valorCompraDebito, bancoDebito, agenciaDebito, contaDebito);
                                        break;
                                    default:
                                        System.out.println("Opção inválida. Tente novamente.");
                                        break;
                                }
                                
                                if (pagamento != null) {
                                    operacao.concluirVenda(pagamento);
                                }
                                novaVenda = false;
                                break;
                            case 3:
                                operacao.cancelarVenda();
                                novaVenda = false;
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                    break;
                case 3:
                    operacao.cancelarVenda();
                    break;
                case 4:
                    System.out.println("Digite o código do produto:");
                    int codigoPr = scanner.nextInt();
                    System.out.println("Digite a quantidade:");
                    int quantidadePr = scanner.nextInt();
                    operacao.novaCompra(codigoPr, quantidadePr);
                    break;
                case 5:
                    executando = false;
                    operacao.Sair();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }      
        }
        scanner.close();
    }
}
