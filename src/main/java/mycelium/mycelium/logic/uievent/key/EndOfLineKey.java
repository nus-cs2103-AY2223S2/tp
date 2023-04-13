package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;

/**
 * EndOfLineKey is a key combination that moves the cursor to the end of the command box.
 */
public class EndOfLineKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+E");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        mainWindow.moveCommandBoxToEndOfLine();
    }
}
