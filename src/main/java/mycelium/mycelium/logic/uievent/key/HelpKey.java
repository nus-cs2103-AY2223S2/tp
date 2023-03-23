package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.ui.MainWindow;

/**
 * HelpKey is the key combination to open the help window.
 */
public class HelpKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("F1");

    @Override
    public void execute(MainWindow mainWindow) {
        mainWindow.handleHelp();
    }
}
