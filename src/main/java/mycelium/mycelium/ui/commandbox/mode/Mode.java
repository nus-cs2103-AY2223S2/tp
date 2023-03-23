package mycelium.mycelium.ui.commandbox.mode;

import java.util.Optional;

/**
 * Represents a mode that the command box can be in.
 */
public abstract class Mode {
    /**
     * Gets the type of mode.
     *
     * @return the type of mode
     */
    public abstract ModeType getModeType();

    /**
     * Sets up the mode.
     *
     * @param prevInput the previous input from the command box before mode change
     */
    public abstract void setupMode(String prevInput);

    /**
     * Called when the input changes.
     *
     * @param input The input from the command box
     */
    public abstract void onInputChange(String input);

    /**
     * Called when the input is submitted.
     * Returns the mode to switch to, if any.
     * Note that the teardownMode() method will be called before switching modes.
     *
     * @param input The input from the command box
     * @return the mode to switch to, if any
     */
    public abstract Optional<Mode> onInputSubmit(String input);

    /**
     * Tears down the mode.
     */
    public abstract void teardownMode();

    /**
     * Represents the type of mode.
     */
    public static enum ModeType {
        COMMAND_MODE,
        SEARCH_MODE
    }
}
