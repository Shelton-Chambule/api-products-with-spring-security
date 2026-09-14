package project.product.spring.security.services.exeption;
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(Object id) {
        super("Resource not found " + id);
    }
}
