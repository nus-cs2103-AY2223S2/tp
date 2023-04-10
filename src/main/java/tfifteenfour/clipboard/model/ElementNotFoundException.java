package tfifteenfour.clipboard.model;

/**
 * Signals that the operation is unable to find the specified element.
 */
public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException() {
        super("Can't find such element in list.");
    }
}
