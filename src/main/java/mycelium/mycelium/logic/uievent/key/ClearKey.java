package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.ui.MainWindow;

/**
 * ClearKey is a key combination that clears the command box and the command log.
 */
public class ClearKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+D");

    @Override
    public void execute(MainWindow mainWindow) {
        mainWindow.getCommandBox().clearInput();
        mainWindow.getCommandLog().clearFeedback();
    }
}
