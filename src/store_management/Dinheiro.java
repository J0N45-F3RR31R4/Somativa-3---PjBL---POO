package store_management;

public class Dinheiro extends Pagamento {
    private double valorPago;

    public Dinheiro(double valorTotal, double valorPago) {
        super(valorTotal);
        this.valorPago = valorPago;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public double calcularValor() {
        double valorDesconto = getValorTotal() * 0.05;
        double valorFinal = getValorTotal() - valorDesconto;
        return valorFinal;
    }

    @Override
    public void efetuarPagamento() {
    	
        double valorFinal = calcularValor();

        System.out.println("Pagamento em dinheiro efetuado. Valor total: R$" + getValorTotal());
        System.out.println("Desconto de 5% aplicado. Valor com desconto: R$" + valorFinal);
        System.out.println("Valor pago: R$" + valorPago);
        System.out.println("Troco: R$" + (valorPago - valorFinal));
        System.out.println("Volte sempre");
    }
}
