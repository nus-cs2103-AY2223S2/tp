package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.ui.MainWindow;

/**
 * PrevItemKey is a key combination that selects the previous item in the current tab.
 */
public class PrevItemKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+K");

    @Override
    public void execute(MainWindow mainWindow) {
        mainWindow.getEntityPanel().prevItem();
    }
}
