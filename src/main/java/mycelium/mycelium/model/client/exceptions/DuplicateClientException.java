package mycelium.mycelium.model.client.exceptions;

/**
 * Signals that the operation will result in duplicate Client (Clients are considered duplicates if they have the same
 * email).
 */
public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException() {
        super("Operation would result in duplicate clients");
    }
}
