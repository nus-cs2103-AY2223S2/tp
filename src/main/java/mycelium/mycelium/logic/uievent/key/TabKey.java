package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;

/**
 * TabKey is a key combination that does nothing. It is used to override the default tab key behavior.
 */
public class TabKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Tab");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        // Do nothing. Used to override the default tab key behavior.
    }
}
