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

    /**
     * Sets the description of the momento.
     *
     * @param desc The description of the momento in string.
     */
    public void setDescription(String desc);
}
