package mycelium.mycelium.logic.uievent.key;

import javafx.scene.input.KeyCombination;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.ui.MainWindow;

/**
 * QuitKey is the key that quits the application.
 */
public class QuitKey extends Key {
    public static final KeyCombination KEY_COMBINATION = KeyCombination.valueOf("Ctrl+Q");

    @Override
    public void execute(Logic logic, MainWindow mainWindow) {
        mainWindow.handleExit();
    }
}
