package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.SampleDateTimeUtil.VALID_END_DATETIME;
import static seedu.address.testutil.SampleDateTimeUtil.VALID_START_DATETIME;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddIsolatedEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);

        IsolatedEvent isolatedEvent = new IsolatedEvent("biking", VALID_START_DATETIME, VALID_END_DATETIME);
        AddIsolatedEventCommand command = new AddIsolatedEventCommand(Index.fromOneBased(1), isolatedEvent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addIsolatedEvent(editedPerson, isolatedEvent);

        String expectedMessage = String.format(AddIsolatedEventCommand.MESSAGE_SUCCESS, isolatedEvent) + " to "
                + editedPerson.getName();

        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());
    }
}
