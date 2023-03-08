package mycelium.mycelium.model.project.exceptions;

/**
 * Signals that the operation would result in duplicate projects.
 */
public class DuplicateProjectException extends RuntimeException {
    public DuplicateProjectException() {
        super("Operation would result in duplicate projects");
    }
}
