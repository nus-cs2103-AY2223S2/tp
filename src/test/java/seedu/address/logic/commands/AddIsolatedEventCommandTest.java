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

public class AddIsolatedEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);

        IsolatedEvent isolatedEvent = new IsolatedEvent("biking", TWO_O_CLOCK_VALID, THREE_O_CLOCK_VALID);
        AddIsolatedEventCommand command = new AddIsolatedEventCommand(Index.fromOneBased(1), isolatedEvent);

        String expectedMessage = String.format(AddIsolatedEventCommand.MESSAGE_SUCCESS, isolatedEvent) + " to "
                + editedPerson.getName();

        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_conflictingRecurringEventList() {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);

        IsolatedEvent isolatedEvent = new IsolatedEvent("Skiing", TWELVE_O_CLOCK_VALID, THREE_O_CLOCK_VALID);

        RecurringEvent recurringEvent = new RecurringEvent("biking", DayOfWeek.THURSDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), TWO_O_CLOCK_VALID.toLocalTime());

        model.addRecurringEvent(editedPerson, recurringEvent);

        AddIsolatedEventCommand command = new AddIsolatedEventCommand(Index.fromOneBased(1), isolatedEvent);

        assertThrows(EventConflictException.class, () ->command.execute(model));

    }
}
