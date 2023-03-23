package mycelium.mycelium.logic.uievent.key;
import mycelium.mycelium.ui.MainWindow;

/**
 * Represents a key combination that can be executed.
 */
public abstract class Key {
    public abstract void execute(MainWindow mainWindow);
}
