package seedu.address.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event that occurs when user skips creating password for the application
 *
 * @author Haiqel Bin Hanaffi
 */
public class SkipCreatePasswordEvent extends Event {
    public static final EventType<SkipCreatePasswordEvent> SKIP_CREATE_PASSWORD_EVENT =
            new EventType<>("SKIP_CREATE_PASSWORD_EVENT");

    public SkipCreatePasswordEvent() {
        super(SKIP_CREATE_PASSWORD_EVENT);
    }
}
