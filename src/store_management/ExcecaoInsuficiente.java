package store_management;

public class ExcecaoInsuficiente extends ExcecaoEstoque {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoInsuficiente() {
		super("Quantidade maior que a disponívle no estoque.");
	}
	
}
