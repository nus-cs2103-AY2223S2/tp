package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;

/**
 * EnterKey is a key combination that submits the command in the command box.
 *
 * This is to created to allow submission regardless of command box focus.
 */
public class EnterKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Enter");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        mainWindow.forceCommandBoxSubmit();
    }
}
