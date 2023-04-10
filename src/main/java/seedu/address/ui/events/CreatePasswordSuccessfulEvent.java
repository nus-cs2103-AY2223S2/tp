package seedu.address.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event that happens when user creates a new password successfully
 */
public class CreatePasswordSuccessfulEvent extends Event {
    public static final EventType<CreatePasswordSuccessfulEvent> CREATE_PASSWORD_SUCCESSFUL_EVENT =
            new EventType<>("CREATE_PASSWORD_SUCCESSFUL");

    private String hashedPassword;

    /**
     * Contructor for CreatePasswordSuccessfulEvent that requires the user's password
     * @param hashedPassword
     */
    public CreatePasswordSuccessfulEvent(String hashedPassword) {
        super(CREATE_PASSWORD_SUCCESSFUL_EVENT);
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }
}
