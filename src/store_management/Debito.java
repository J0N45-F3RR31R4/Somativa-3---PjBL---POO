package store_management;

public class Debito extends Pagamento {
    private String banco;
    private String agencia;
    private String conta;

    public Debito(double valorTotal, String banco, String agencia, String conta) {
        super(valorTotal);
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    @Override
    public double calcularValor() {
        double valorDesconto = getValorTotal() * 0.03;
        return getValorTotal() - valorDesconto;
    }

    @Override
    public void efetuarPagamento() {
        double valorDesconto = getValorTotal() * 0.03;
        double valorFinal = getValorTotal() - valorDesconto;

        System.out.println("Pagamento com cartão de débito efetuado. Valor total: R$" + getValorTotal());
        System.out.println("Desconto de 3% aplicado. Valor com desconto: R$" + valorFinal);
        System.out.println("Volte sempre");
    }
}
