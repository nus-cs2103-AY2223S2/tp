package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;

/**
 * StartOfLineKey is a key combination that moves the cursor to the start of the command box.
 */
public class StartOfLineKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+W");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        mainWindow.moveCommandBoxToStartOfLine();
    }
}
