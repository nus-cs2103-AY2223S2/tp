package mycelium.mycelium.ui.action;

import mycelium.mycelium.ui.MainWindow;

/**
 * An action to exit the application.
 */
public class ExitAction extends UiAction {
    @Override
    public void execute(MainWindow mainWindow) {
        logger.fine("Exiting");
        mainWindow.handleExit();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitAction;
    }

    @Override
    public int hashCode() {
        return ExitAction.class.hashCode();
    }
}
