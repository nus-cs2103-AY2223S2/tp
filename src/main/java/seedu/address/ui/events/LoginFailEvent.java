package seedu.address.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * An event that will be triggered when user fails to login
 *
 * @author Haiqel Bin Hanaffi
 */
public class LoginFailEvent extends Event {
    public static final EventType<LoginFailEvent> LOGIN_FAIL_EVENT =
            new EventType<>("LOGIN_FAIL");

    public LoginFailEvent() {
        super(LOGIN_FAIL_EVENT);
    }

}
