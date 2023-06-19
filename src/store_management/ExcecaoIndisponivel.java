package store_management;

public class ExcecaoIndisponivel extends ExcecaoEstoque {
	
	private static final long serialVersionUID = 1L;
	
	public ExcecaoIndisponivel() {
		super("Produto indisponível.");
	}

}
