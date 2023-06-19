package store_management;

public class Credito extends Pagamento {
    private String numeroCartao;
    private String dataValidade;
    private String codigoSeguranca;
    private int vezes;

    public Credito(double valorTotal, int vezes, String numeroCartao, String dataValidade, String codigoSeguranca) {
        super(valorTotal);
        this.numeroCartao = numeroCartao;
        this.dataValidade = dataValidade;
        this.codigoSeguranca = codigoSeguranca;
        this.vezes = vezes;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    @Override
    public double calcularValor() {
        double valorcomJuros = 0.0;
        
        if (vezes > 6) {
            valorcomJuros = getValorTotal() * 1.05; 
        } else {
            valorcomJuros = getValorTotal() * 1.03; 
        }
        
        return valorcomJuros;
    }
    
    public double getValordoJuros() {
    	
    	return calcularValor() - getValorTotal();
    	
    }
    
    public double getPorcentagem() {
    	
    	return  getValordoJuros() / getValorTotal();
    	
    }

    @Override
    public void efetuarPagamento() {
    	
        System.out.println("Pagamento com cartão de crédito efetuado. Valor total: R$" + calcularValor());
        System.out.println("Juros de" + getValordoJuros() + "aplicado sobre a compra (%" + getPorcentagem() + ").");
        System.out.println("Volte sempre");
    }
}
