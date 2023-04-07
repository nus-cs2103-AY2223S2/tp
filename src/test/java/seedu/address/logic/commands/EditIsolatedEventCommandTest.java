package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.EditIsolatedEventCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.SampleDateTimeUtil.FOUR_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.SIX_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWO_O_CLOCK_VALID;
import static seedu.address.testutil.SampleEventUtil.SKIING_ISOLATED_EVENT;
import static seedu.address.testutil.SampleEventUtil.SLEEPING_ISOLATED_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditIsolatedEventCommand.EditEventDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditIsolatedEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        editedPerson.getIsolatedEventList().insert(SKIING_ISOLATED_EVENT);
        model.addPerson(editedPerson);

        EditEventDescriptor editEventDescriptor = new EditEventDescriptorBuilder("Sleep", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID).build();
        EditIsolatedEventCommand command = new EditIsolatedEventCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_EVENT, editEventDescriptor);

        String expectedMessage = String.format(MESSAGE_SUCCESS, SLEEPING_ISOLATED_EVENT,
                editedPerson.getName())
                + "\nOriginal Event: "
                + SKIING_ISOLATED_EVENT + " for " + editedPerson.getName();

        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_conflictRecurringEvent() {
        Person editedPerson = new PersonBuilder().build();
        editedPerson.getIsolatedEventList().insert(SKIING_ISOLATED_EVENT);
        model.addPerson(editedPerson);

        model.addRecurringEvent(editedPerson, new RecurringEvent("Cycling", FOUR_O_CLOCK_VALID.getDayOfWeek(),
                THREE_O_CLOCK_VALID.toLocalTime(), SIX_O_CLOCK_VALID.toLocalTime()));

        EditEventDescriptor editEventDescriptor = new EditEventDescriptorBuilder("Sleep", TWO_O_CLOCK_VALID,
                FOUR_O_CLOCK_VALID).build();

        EditIsolatedEventCommand command = new EditIsolatedEventCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_EVENT, editEventDescriptor);

        assertThrows(CommandException.class, () ->command.execute(model));
    }
    @Test
    public void execute_invalidEventIndex() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        editedPerson.getIsolatedEventList().insert(SKIING_ISOLATED_EVENT);
        model.addPerson(editedPerson);

        EditEventDescriptor editEventDescriptor = new EditEventDescriptorBuilder("Sleep", TWO_O_CLOCK_VALID,
                FOUR_O_CLOCK_VALID).build();

        EditIsolatedEventCommand command = new EditIsolatedEventCommand(INDEX_FIRST_PERSON,
                INDEX_THIRD_EVENT, editEventDescriptor);

        assertThrows(CommandException.class, () ->command.execute(model));
    }
}
