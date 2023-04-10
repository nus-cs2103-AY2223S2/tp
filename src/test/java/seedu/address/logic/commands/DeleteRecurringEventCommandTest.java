package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.SampleEventUtil.BIKING_RECURRING_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DeleteRecurringEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);
        model.addRecurringEvent(editedPerson, BIKING_RECURRING_EVENT);

        DeleteRecurringEventCommand command = new DeleteRecurringEventCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteRecurringEventCommand.MESSAGE_SUCCESS, BIKING_RECURRING_EVENT)
                + " to " + editedPerson.getName();

        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());
    }
}
