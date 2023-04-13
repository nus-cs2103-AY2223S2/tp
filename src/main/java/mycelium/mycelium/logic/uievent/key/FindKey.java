package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;
import mycelium.mycelium.ui.commandbox.mode.CommandMode;
import mycelium.mycelium.ui.commandbox.mode.SearchMode;

/**
 * FindKey is the key combination to turn the command box into listening mode to enable interactive task finding.
 */
public class FindKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+F");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        switch (mainWindow.getCommandBoxModeType()) {
        case COMMAND_MODE:
            mainWindow.setCommandBoxMode(new SearchMode(mainWindow, logic));
            break;
        case SEARCH_MODE:
            mainWindow.setCommandBoxMode(new CommandMode(mainWindow));
            break;
        default:
            break;
        }
    }
}
