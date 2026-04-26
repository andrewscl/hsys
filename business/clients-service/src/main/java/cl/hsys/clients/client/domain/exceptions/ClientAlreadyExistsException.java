package cl.hsys.clients.client.domain.exceptions;

public class ClientAlreadyExistsException extends RuntimeException {
    
    public ClientAlreadyExistsException(String message) {
        super(message);
    }

}
