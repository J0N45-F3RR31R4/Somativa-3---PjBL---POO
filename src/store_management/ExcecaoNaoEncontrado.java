package store_management;

public class ExcecaoNaoEncontrado extends ExcecaoEstoque {
	
private static final long serialVersionUID = 1L;
	
	public ExcecaoNaoEncontrado() {
		super("Produto não encontrado.");
	}

}
