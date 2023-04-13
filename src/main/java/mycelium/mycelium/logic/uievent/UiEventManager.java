package mycelium.mycelium.logic.uievent;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.logic.uievent.key.ClearKey;
import mycelium.mycelium.logic.uievent.key.EndOfLineKey;
import mycelium.mycelium.logic.uievent.key.EnterKey;
import mycelium.mycelium.logic.uievent.key.FindKey;
import mycelium.mycelium.logic.uievent.key.HelpKey;
import mycelium.mycelium.logic.uievent.key.NextItemKey;
import mycelium.mycelium.logic.uievent.key.PrevItemKey;
import mycelium.mycelium.logic.uievent.key.QuitKey;
import mycelium.mycelium.logic.uievent.key.StartOfLineKey;
import mycelium.mycelium.logic.uievent.key.SwitchPanelKey;
import mycelium.mycelium.logic.uievent.key.SwitchTabKey;
import mycelium.mycelium.ui.MainWindow;

/**
 * UiEventManager is the manager for all the key events.
 */
public class UiEventManager implements UiEvent {
    public static final EventType<KeyEvent> TYPE = KeyEvent.KEY_PRESSED;
    private static final KeyCombination[] IGNORED_KEYS = {
        new KeyCodeCombination(
            KeyCode.TAB,
            KeyCombination.CONTROL_ANY,
            KeyCombination.SHIFT_ANY,
            KeyCombination.ALT_ANY,
            KeyCombination.META_ANY,
            KeyCombination.SHORTCUT_ANY)
    };

    private Logic logic;
    private MainWindow mainWindow;

    /**
     * Constructor for UiEventManager.
     *
     * @param mainWindow the main window
     */
    public UiEventManager(Logic logic, MainWindow mainWindow) {
        this.logic = logic;
        this.mainWindow = mainWindow;
    }

    /**
     * Check if the key event should be ignored.
     * This performs and early exit of the event handler,
     * to guard against problematic key combinations.
     *
     * @param event the key event
     * @return whether the key event should be ignored
     */
    public boolean isIgnored(KeyEvent event) {
        for (KeyCombination ignoredKey : IGNORED_KEYS) {
            if (ignoredKey.match(event)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Catch and execute the key event if it matches any of the key combinations.
     *
     * @param event the key event
     */
    public void catchAndExecute(KeyEvent event) {
        mainWindow.focusCommandBox();
        if (isIgnored(event)) {

        } else if (HelpKey.KEY_COMBINATION.match(event)) {
            new HelpKey().execute(logic, mainWindow);
        } else if (FindKey.KEY_COMBINATION.match(event)) {
            new FindKey().execute(logic, mainWindow);
        } else if (SwitchPanelKey.KEY_COMBINATION.match(event)) {
            new SwitchPanelKey().execute(logic, mainWindow);
        } else if (SwitchTabKey.KEY_COMBINATION.match(event)) {
            new SwitchTabKey().execute(logic, mainWindow);
        } else if (NextItemKey.KEY_COMBINATION.match(event)) {
            new NextItemKey().execute(logic, mainWindow);
        } else if (PrevItemKey.KEY_COMBINATION.match(event)) {
            new PrevItemKey().execute(logic, mainWindow);
        } else if (ClearKey.KEY_COMBINATION.match(event)) {
            new ClearKey().execute(logic, mainWindow);
        } else if (StartOfLineKey.KEY_COMBINATION.match(event)) {
            new StartOfLineKey().execute(logic, mainWindow);
        } else if (EndOfLineKey.KEY_COMBINATION.match(event)) {
            new EndOfLineKey().execute(logic, mainWindow);
        } else if (QuitKey.KEY_COMBINATION.match(event)) {
            new QuitKey().execute(logic, mainWindow);
        } else if (EnterKey.KEY_COMBINATION.match(event)) {
            new EnterKey().execute(logic, mainWindow);
        } else {
            return;
        }
        event.consume();
    }

    /**
     * Get the event handler for key events.
     *
     * @return the event handler for key events
     */
    public EventHandler<KeyEvent> getEventHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                catchAndExecute(event);
            }
        };
    }
}
