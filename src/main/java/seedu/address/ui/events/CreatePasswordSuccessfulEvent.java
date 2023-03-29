package seedu.address.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event that happens when user creates a new password successfully
 */
public class CreatePasswordSuccessfulEvent extends Event {
    public static final EventType<CreatePasswordSuccessfulEvent> CREATE_PASSWORD_SUCCESSFUL_EVENT =
            new EventType<>("CREATE_PASSWORD_SUCCESSFUL");

    public CreatePasswordSuccessfulEvent() {
        super(CREATE_PASSWORD_SUCCESSFUL_EVENT);
    }
}
