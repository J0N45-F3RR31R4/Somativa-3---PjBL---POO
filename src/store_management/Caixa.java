package store_management;

public class Caixa {
	private Double capital;
    private Double totalVendas = 0.0;
    private Double lucro = 0.0;
    private String Banco;
    private String Conta;

    public Caixa(Double capital, String Banco, String Conta) {
    	this.capital = capital;
    	this.Banco = Banco;
    	this.Conta = Conta;
    }
    
    public void adicionarVenda(double valor) {
        
    }

    public double getTotalVendas() {
        return totalVendas;
    }
    
    public double getLucro() {
    	return lucro;
    }

	public Double getCapital() {
		return capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}
	
	

	public void setTotalVendas(double totalVendas) {
		this.totalVendas = totalVendas;
	}

	public void setLucro(Double lucro) {
		this.lucro = lucro;
	}

	public void retirarValor(double valorCompra) {
		
		this.capital -= valorCompra;
		
	}
}

