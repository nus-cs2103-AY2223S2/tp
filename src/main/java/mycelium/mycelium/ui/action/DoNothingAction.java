package mycelium.mycelium.ui.action;

import mycelium.mycelium.ui.MainWindow;

/**
 * Represents a UI action that does nothing.
 */
public class DoNothingAction extends UiAction {
    /**
     * Creates a new DoNothingAction.
     */
    public DoNothingAction() {
        // Do nothing
    }

    @Override
    public void execute(MainWindow mainWindow) {
        // Do nothing
        logger.fine("Doing nothing");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DoNothingAction;
    }

    @Override
    public int hashCode() {
        return DoNothingAction.class.hashCode();
    }
}
