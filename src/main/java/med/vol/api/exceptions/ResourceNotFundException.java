package med.vol.api.exceptions;

public class ResourceNotFundException extends RuntimeException {
    public ResourceNotFundException() {
        super("Resource not found");
    }
}
