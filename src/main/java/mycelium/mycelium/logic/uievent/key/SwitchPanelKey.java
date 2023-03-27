package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;

/**
 * SwitchPanelKey is the key combination to switch between the entire panel tabs.
 */
public class SwitchPanelKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+S");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        mainWindow.nextTabPanel();
    }
}
