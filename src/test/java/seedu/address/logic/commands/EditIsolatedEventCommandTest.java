package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.EditIsolatedEventCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWO_O_CLOCK_VALID;
import static seedu.address.testutil.SampleEventUtil.SKIING_ISOLATED_EVENT;
import static seedu.address.testutil.SampleEventUtil.SLEEPING_ISOLATED_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditIsolatedEventCommand.EditEventDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditIsolatedEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);
        model.addIsolatedEvent(editedPerson, SKIING_ISOLATED_EVENT);

        EditEventDescriptor editEventDescriptor = new EditEventDescriptorBuilder("Sleep", TWO_O_CLOCK_VALID,
                THREE_O_CLOCK_VALID).build();
        EditIsolatedEventCommand command = new EditIsolatedEventCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_EVENT, editEventDescriptor);

        String expectedMessage = String.format(MESSAGE_SUCCESS, SLEEPING_ISOLATED_EVENT)
                + " from " + SKIING_ISOLATED_EVENT + " for " + editedPerson.getName();

        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());
    }
}
