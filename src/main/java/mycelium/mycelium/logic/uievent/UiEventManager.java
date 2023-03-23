package mycelium.mycelium.logic.uievent;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;
import mycelium.mycelium.logic.uievent.key.ClearKey;
import mycelium.mycelium.logic.uievent.key.FindKey;
import mycelium.mycelium.logic.uievent.key.HelpKey;
import mycelium.mycelium.logic.uievent.key.NextItemKey;
import mycelium.mycelium.logic.uievent.key.PrevItemKey;
import mycelium.mycelium.logic.uievent.key.SwitchKey;
import mycelium.mycelium.logic.uievent.key.TabKey;
import mycelium.mycelium.ui.MainWindow;

/**
 * UiEventManager is the manager for all the key events.
 */
public class UiEventManager implements UiEvent {
    public static final EventType<KeyEvent> TYPE = KeyEvent.KEY_PRESSED;
    private MainWindow mainWindow;

    /**
     * Constructor for UiEventManager.
     *
     * @param mainWindow the main window
     */
    public UiEventManager(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Catch and execute the key event if it matches any of the key combinations.
     *
     * @param event the key event
     */
    public void catchAndExecute(KeyEvent event) {
        if (HelpKey.KEY_COMBINATION.match(event)) {
            new HelpKey().execute(mainWindow);
        } else if (FindKey.KEY_COMBINATION.match(event)) {
            new FindKey().execute(mainWindow);
        } else if (SwitchKey.KEY_COMBINATION.match(event)) {
            new SwitchKey().execute(mainWindow);
        } else if (NextItemKey.KEY_COMBINATION.match(event)) {
            new NextItemKey().execute(mainWindow);
        } else if (PrevItemKey.KEY_COMBINATION.match(event)) {
            new PrevItemKey().execute(mainWindow);
        } else if (ClearKey.KEY_COMBINATION.match(event)) {
            new ClearKey().execute(mainWindow);
        } else if (TabKey.KEY_COMBINATION.match(event)) {
            new TabKey().execute(mainWindow);
        } else {
            mainWindow.getCommandBox().requestFocus();
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
