package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.ui.MainWindow;

/**
 * SwitchKey is the key combination to switch between the entity panel tabs.
 */
public class SwitchKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+L");

    @Override
    public void execute(MainWindow mainWindow) {
        mainWindow.getEntityPanel().nextTab();
    }
}
