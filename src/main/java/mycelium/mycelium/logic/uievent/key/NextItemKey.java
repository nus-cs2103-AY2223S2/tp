package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.ui.MainWindow;

/**
 * NextItemKey is a key combination that selects the next item in the current tab.
 */
public class NextItemKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+J");

    @Override
    public void execute(MainWindow mainWindow) {
        mainWindow.getEntityPanel().nextItem();
    }
}
