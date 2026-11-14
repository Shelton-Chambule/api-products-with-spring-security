package project.product.spring.security.exception;
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(Object id) {
        super("Resource not found " + id);
    }
}
