package sk.fullstack.lany.server.service.implementation;

/**
 * Exception for item that cannot be find or load
 */
public class ItemNotFoundException extends Exception {

    ItemNotFoundException(String message) {
        super(message);
    }
}
