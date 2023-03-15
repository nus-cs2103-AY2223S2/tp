package teambuilder.commons.util;

import teambuilder.commons.core.Momento;

/**
 * Represents the history of states in the app.
 */
public class HistoryUtil {
    private static HistoryUtil singleton = new HistoryUtil();

    private static final int MAX_STATE = 10;
    private Momento[] history;
    private int currentNum = -1;
    private String descriptionUndo;

    private HistoryUtil() {
        history = new Momento[MAX_STATE];
    }

    private void incrementCurrentNum() {
        currentNum = currentNum + 1 >= MAX_STATE ? 0 : currentNum + 1;
    }

    private void decreaseCurrentNum() {
        if (currentNum <= -1) {
            currentNum = -1;
            return;
        }
        // check wrap around
        if (currentNum - 1 < 0 && history[MAX_STATE - 1] != null) {
            currentNum = MAX_STATE - 1;
            return;
        }

        currentNum = currentNum - 1;
    }

    /**
     * Gives the single instance of HistoryUtil.
     *
     * @return The only instance of HistoryUtil.
     */
    public static HistoryUtil getInstance() {
        return singleton;
    }

    /**
     * Restores the app to its previous state.
     *
     * @return true if successful, false otherwise
     */
    public boolean undo() {
        if (currentNum <= -1) {
            assert currentNum == -1 : "currentNum too negative!";
            return false;
        }

        boolean isSuccessful = history[currentNum].restore();
        descriptionUndo = history[currentNum].toString();
        history[currentNum] = null;
        decreaseCurrentNum();
        return isSuccessful;
    }

    /**
     * Retrieves the description of the last successful undo.
     *
     * @return The string containing the description of the last successful undo
     */
    public String getLastUndoDescription() {
        return descriptionUndo == null ? "" : descriptionUndo;
    }

    /**
     * Stores a momento of the app. Maximumn of 10 stored momentos at any time.
     *
     * @param memo The momento to be stored.
     */
    public void store(Momento memo) {
        incrementCurrentNum();
        history[currentNum] = memo;
    }
}
