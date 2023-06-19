package store_management;

public abstract class ExcecaoEstoque extends Exception {
	
	private static final long serialVersionUID = 1L;

    public ExcecaoEstoque(String msg) {
        super(msg);
    }
}

