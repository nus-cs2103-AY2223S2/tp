package teambuilder.commons.core;

/**
 * Represents a previous state of an originator
 */
public interface Momento {
    /**
     * Restores the state of the Originator to this momento's state.
     *
     * @return true if successful, false otherwise
     */
    public boolean restore();
}
