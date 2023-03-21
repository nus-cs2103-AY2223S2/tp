package mycelium.mycelium.logic.uievent;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * UiEvent is the interface for all the key events.
 */
public interface UiEvent {
    EventHandler<KeyEvent> getEventHandler();
}
