package teambuilder.commons.core;

/**
 * Represents a class with multiple possible states.
 */
public interface Originator {
    /**
     * Creates a momento with the current originator.
     *
     * @return The Momento containing the current originator and the desired state.
     */
    public Momento save();
}
