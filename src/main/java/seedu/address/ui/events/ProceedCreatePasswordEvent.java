package seedu.address.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * An event class that is created when user decides to create a password in the welcom page
 *
 * @author Haiqel Bin Hanaffi
 */
public class ProceedCreatePasswordEvent extends Event {
    public static final EventType<ProceedCreatePasswordEvent> PROCEED_CREATE_PASSWORD =
            new EventType<>("PROCEED_CREATE_PASSWORD");

    public ProceedCreatePasswordEvent() {
        super(PROCEED_CREATE_PASSWORD);
    }
}
