package mycelium.mycelium.logic.uievent;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.logic.uievent.key.FindKey;
import mycelium.mycelium.logic.uievent.key.HelpKey;
import mycelium.mycelium.logic.uievent.key.SwitchKey;
import mycelium.mycelium.ui.MainWindow;

/**
 * UiEventManager is the manager for all the key events.
 */
public class UiEventManager implements UiEvent {
    public static final EventType<KeyEvent> TYPE = KeyEvent.KEY_PRESSED;
    private MainWindow mainWindow;
    private Logic logic;

    /**
     * Constructor for UiEventManager.
     *
     * @param logic the logic component
     * @param mainWindow the main window
     */
    public UiEventManager(Logic logic, MainWindow mainWindow) {
        this.logic = logic;
        this.mainWindow = mainWindow;
    }

    /**
     * Catch and execute the key event if it matches any of the key combinations.
     *
     * @param event the key event
     */
    public void catchAndExecute(KeyEvent event) {
        if (HelpKey.KEY_COMBINATION.match(event)) {
            new HelpKey().execute(logic, mainWindow);
        } else if (FindKey.KEY_COMBINATION.match(event)) {
            new FindKey().execute(logic, mainWindow);
        } else if (SwitchKey.KEY_COMBINATION.match(event)) {
            new SwitchKey().execute(logic, mainWindow);
        } else {
            return;
        }
        event.consume();
    }

    public EventHandler<KeyEvent> getEventHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                catchAndExecute(event);
            }
        };
    }
}
