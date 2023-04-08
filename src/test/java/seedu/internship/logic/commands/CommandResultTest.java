package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;
import seedu.internship.testutil.TypicalEvents;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", ResultType.NO_CHANGE)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false

        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different resultType -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", ResultType.HOME)));

        // contains a Statistics -> return false


        // contains an Internship -> return false
        Internship internship = TypicalEvents.VALID_INTERNSHIP_EM11;
        assertFalse(commandResult.equals(new CommandResult("feedback", ResultType.NO_CHANGE, internship)));

        // contains (an ObservableList of) Events -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                ResultType.NO_CHANGE,
                FXCollections.observableArrayList(TypicalEvents.getTypicalEvents()))));

        // contains a Hashmap<LocalDate, List<Event>> -> return false
        HashMap<LocalDate, List<Event>> dummyHash = new HashMap<LocalDate, List<Event>>();
        assertFalse(commandResult.equals(new CommandResult("feedback",
                ResultType.NO_CHANGE,
                FXCollections.observableArrayList(TypicalEvents.getTypicalEvents()))));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different ResultType value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", ResultType.HOME).hashCode());

        // contains an Internship -> return different hashcode
        Internship internship = TypicalEvents.VALID_INTERNSHIP_EM11;
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", ResultType.NO_CHANGE, internship).hashCode());

        // contains (an ObservableList of) Events -> return different hashcode
        assertNotEquals(commandResult.hashCode(),
                FXCollections.observableArrayList(TypicalEvents.getTypicalEvents()).hashCode());

        // contains a Hashmap<LocalDate, List<Event>> -> return different hashcode
        HashMap<LocalDate, List<Event>> dummyHash = new HashMap<LocalDate, List<Event>>();
        assertNotEquals(commandResult.hashCode(), dummyHash.hashCode());

    }
}
