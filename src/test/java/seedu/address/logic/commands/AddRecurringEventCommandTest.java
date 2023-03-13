package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWELVE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWO_O_CLOCK_VALID;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.exceptions.EventConflictException;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddRecurringEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);

        RecurringEvent recurringEvent = new RecurringEvent("biking", DayOfWeek.MONDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), TWO_O_CLOCK_VALID.toLocalTime());

        AddRecurringEventCommand command = new AddRecurringEventCommand(Index.fromOneBased(1),
                recurringEvent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addRecurringEvent(editedPerson, recurringEvent);

        String expectedMessage = String.format(AddRecurringEventCommand.MESSAGE_SUCCESS, recurringEvent) + " to "
                + editedPerson.getName();

        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());

        String expectedList = editedPerson.getRecurringEventList().toString();

        assertEquals(expectedList, "1. biking\n");

    }

    @Test
    public void execute_conflictingIsolatedEvent() {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);

        IsolatedEvent isolatedEvent = new IsolatedEvent("Skiing", TWELVE_O_CLOCK_VALID, THREE_O_CLOCK_VALID);

        RecurringEvent recurringEvent = new RecurringEvent("biking", DayOfWeek.THURSDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), TWO_O_CLOCK_VALID.toLocalTime());

        model.addIsolatedEvent(editedPerson, isolatedEvent);

        AddRecurringEventCommand command = new AddRecurringEventCommand(Index.fromOneBased(1),
                recurringEvent);

        assertThrows(EventConflictException.class, () ->command.execute(model));

    }

}
