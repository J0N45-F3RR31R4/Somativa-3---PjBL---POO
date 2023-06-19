package store_management;

public abstract class Pagamento {
	
    protected double valorTotal;

    public Pagamento(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    public abstract double calcularValor();
    
    public void efetuarPagamento() {
    	System.out.println("Pagamento em dinheiro efetuado. Valor total: R$" + getValorTotal());
    	System.out.println("Volte sempre");
    }
    
}