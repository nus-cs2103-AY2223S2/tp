package seedu.address.model;

import java.util.ArrayList;

/**
 * View of a command history store.
 */
public interface ReadOnlyEduMateHistory {
    String getCurrentCommand();

    boolean isUpPressedBefore();

    /**
     * Returns the saved history of the commands keyed by the user previously.
     * @return An arraylist of past commands.
     */
    ArrayList<String> getHistory();

    /**
     * Returns the previous command keyed by the user according to the button the user pressed.
     * @param isUp Boolean to see whether the user wants to return the older command or the newer command.
     * @return The command string.
     */
    String getPreviousCommand(boolean isUp);

}
