package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;

/**
 * SwitchTabKey is the key combination to switch between the tabs within a panel.
 */
public class SwitchTabKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+L");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        mainWindow.nextTab();
    }
}
