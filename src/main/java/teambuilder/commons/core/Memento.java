package teambuilder.commons.core;

/**
 * Represents a previous state of an originator
 */
public interface Memento {
    /**
     * Restores the state of the Originator to this memento's state.
     *
     * @return true if successful, false otherwise
     */
    public boolean restore();

    /**
     * Retrieves the latest memento of this memento's originator
     *
     * @return The latest memento of this memento's originator.
     */
    public Memento getUpdatedMemento();
}
