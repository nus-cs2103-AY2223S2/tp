package mycelium.mycelium.ui.action;

import mycelium.mycelium.ui.MainWindow;

/**
 * An action that shows the help window.
 */
public class ShowHelpAction extends UiAction {
    @Override
    public void execute(MainWindow mainWindow) {
        logger.fine("Showing help");
        mainWindow.handleHelp();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ShowHelpAction;
    }

    @Override
    public int hashCode() {
        return ShowHelpAction.class.hashCode();
    }
}
