package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;

/**
 * FindKey is the key combination to turn the command box into listening mode to enable interactive task finding.
 */
public class FindKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+F");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        mainWindow.getCommandBox().toggleListening();
    }
}
