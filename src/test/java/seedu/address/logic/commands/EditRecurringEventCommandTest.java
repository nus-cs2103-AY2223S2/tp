package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWO_O_CLOCK_VALID;
import static seedu.address.testutil.SampleEventUtil.BIKING_RECURRING_EVENT;
import static seedu.address.testutil.SampleEventUtil.GYM_ISOLATED_EVENT;
import static seedu.address.testutil.SampleEventUtil.SWIMMING_RECURRING_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.exceptions.EventConflictException;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditRecurringEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execution_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();

        model.addPerson(editedPerson);
        model.addRecurringEvent(editedPerson, BIKING_RECURRING_EVENT);

        EditRecurringEventCommand.EditEventDescriptor editEventDescriptor =
                new EditEventDescriptorBuilder("Jogging", DayOfWeek.WEDNESDAY,
                        TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()).recurringbuild();

        EditRecurringEventCommand editCommand = new EditRecurringEventCommand(INDEX_FIRST_PERSON, INDEX_FIRST_EVENT,
                editEventDescriptor);

        RecurringEvent expectedEvent = new RecurringEvent("Jogging", DayOfWeek.WEDNESDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime());

        String expectedMessage = String.format("Original Event: " + BIKING_RECURRING_EVENT + " for "
                + editedPerson.getName() + "\n" + editCommand.MESSAGE_SUCCESS, expectedEvent + " for "
                + editedPerson.getName());

        assertEquals(expectedMessage, editCommand.execute(model).getFeedbackToUser());

    }

    @Test
    public void execute_conflictIsolatedEvent() throws CommandException {

        Person editedPerson = new PersonBuilder().build();

        editedPerson.getIsolatedEventList().insert(GYM_ISOLATED_EVENT);
        model.addPerson(editedPerson);
        model.addRecurringEvent(editedPerson, BIKING_RECURRING_EVENT);

        EditRecurringEventCommand.EditEventDescriptor editEventDescriptor =
                new EditEventDescriptorBuilder("Jogging", DayOfWeek.SATURDAY,
                        TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()).recurringbuild();

        EditRecurringEventCommand editCommand = new EditRecurringEventCommand(INDEX_FIRST_PERSON, INDEX_FIRST_EVENT,
                editEventDescriptor);

        assertThrows(EventConflictException.class, () ->editCommand.execute(model));

    }

    @Test
    public void execute_recurringEvent() throws CommandException {
        Person editedPerson = new PersonBuilder().build();

        model.addPerson(editedPerson);
        model.addRecurringEvent(editedPerson, BIKING_RECURRING_EVENT);
        model.addRecurringEvent(editedPerson, SWIMMING_RECURRING_EVENT);

        EditRecurringEventCommand.EditEventDescriptor editEventDescriptor =
                new EditEventDescriptorBuilder("Jogging", DayOfWeek.MONDAY,
                        TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()).recurringbuild();

        EditRecurringEventCommand editCommand = new EditRecurringEventCommand(INDEX_FIRST_PERSON, INDEX_SECOND_EVENT,
                editEventDescriptor);

        assertThrows(EventConflictException.class, () ->editCommand.execute(model));

    }

}
