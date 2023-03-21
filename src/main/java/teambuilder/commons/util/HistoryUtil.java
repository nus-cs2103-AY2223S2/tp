package teambuilder.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import teambuilder.commons.core.Memento;

/**
 * Represents the history of states in the app.
 */
public class HistoryUtil {
    private static HistoryUtil singleton = new HistoryUtil();
    private static final int MAX_STATE = 10;

    private DescribedMemento[] undoHistory;
    private DescribedMemento[] redoFuture;
    private int currentNum = -1;

    private HistoryUtil() {
        undoHistory = new DescribedMemento[MAX_STATE];
        redoFuture = new DescribedMemento[MAX_STATE];
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
        if (currentNum - 1 < 0 && undoHistory[MAX_STATE - 1] != null) {
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
     * Stores a past memento of the app and a description of the memento.
     * Maximumn of 10 stored momentos at any time.
     *
     * @param memo The memento to be stored.
     * @param desc The description of the memo.
     */
    public void storePast(Memento memo, String desc) {
        requireNonNull(memo);
        requireNonNull(desc);

        DescribedMemento memoWithDesc = new DescribedMemento(memo, desc);
        incrementCurrentNum();
        undoHistory[currentNum] = memoWithDesc;
        redoFuture = new DescribedMemento[MAX_STATE];
    }

    /**
     * Restores the app to its previous state.
     *
     * @return An Optional containing a string of the description of the state restored if successful,
     *      contains null otherwise.
     */
    public Optional<String> undo() {
        if (currentNum <= -1) {
            return Optional.ofNullable(null);
        }
        if (undoHistory[currentNum] == null) {
            currentNum = -1;
            return Optional.ofNullable(null);
        }

        DescribedMemento mementoForUndo = undoHistory[currentNum];
        DescribedMemento mementoForRedo = mementoForUndo.getUpdatedMemento();
        boolean isSuccessful = mementoForUndo.restore();
        String descriptionUndo = mementoForUndo.desc;
        undoHistory[currentNum] = null;
        redoFuture[currentNum] = mementoForRedo;

        decreaseCurrentNum();
        return isSuccessful ? Optional.of(descriptionUndo) : Optional.ofNullable(null);
    }

    /**
     * Restores the app to its state before an undo.
     *
     * @return An Optional containing a string of the description of the state restored if successful,
     *      contains null otherwise.
     */
    public Optional<String> redo() {
        incrementCurrentNum();

        if (redoFuture[currentNum] == null) {
            decreaseCurrentNum();
            return Optional.ofNullable(null);
        }

        DescribedMemento mementoForRedo = redoFuture[currentNum];
        DescribedMemento mementoForUndo = mementoForRedo.getUpdatedMemento();
        boolean isSuccessful = mementoForRedo.restore();
        String descriptionRedo = mementoForRedo.desc;
        undoHistory[currentNum] = mementoForUndo;
        redoFuture[currentNum] = null;

        return isSuccessful ? Optional.of(descriptionRedo) : Optional.ofNullable(null);
    }

    /**
     * Wrapper class for a Memento and its description.
     */
    private class DescribedMemento {
        private Memento memento;
        private String desc;

        private DescribedMemento(Memento memento, String desc) {
            this.memento = memento;
            this.desc = desc;
        }

        private boolean restore() {
            return memento.restore();
        }

        private DescribedMemento getUpdatedMemento() {
            return new DescribedMemento(memento.getUpdatedMemento(), desc);
        }

    }
}
