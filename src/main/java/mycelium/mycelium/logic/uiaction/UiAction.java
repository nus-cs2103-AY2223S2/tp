package mycelium.mycelium.logic.uiaction;


import java.util.logging.Logger;

import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.ui.MainWindow;

/**
 * Represents a UI action.
 */
public abstract class UiAction {
    protected Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Executes the action.
     * @param mainWindow The main window of the application.
     */
    public abstract void execute(MainWindow mainWindow);
}
