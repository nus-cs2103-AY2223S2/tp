package seedu.address.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * An event that will trigger when user attempts to login
 * with given password
 *
 * @author Haiqel Bin Hanaffi
 */
public class AttemptLoginEvent extends Event {
    public static final EventType<AttemptLoginEvent> ATTEMPT_LOGIN_EVENT =
            new EventType<>("ATTEMPT_LOGIN");

    private String hashedPassword;

    /**
     * Creates an attempt login event with the user's password
     * @param hashedPassword of user
     */
    public AttemptLoginEvent(String hashedPassword) {
        super(ATTEMPT_LOGIN_EVENT);
        this.hashedPassword = hashedPassword;
    }

    /**
     * Get the password of the user
     * @return password of the user
     */
    public String getHashedPassword() {
        return this.hashedPassword;
    }
}
