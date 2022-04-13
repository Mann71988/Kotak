package customExceptionHandlerPkg;

public class ProductException extends RuntimeException {

    public ProductException(Integer id) {
        super("Product id not found : " + id);
    }

}
