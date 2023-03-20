package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalContacts;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class RevenueCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEventBook(),
                TypicalContacts.getTypicalContactList(), new UserPrefs());
        expectedModel = new ModelManager(model.getEventBook(),
            TypicalContacts.getTypicalContactList(), new UserPrefs());
    }

    @Test
    public void execute_calculateDoneEventOnly() throws CommandException {
        expectedModel.updateFilteredEventList(event -> event.getMark().isDone());
        Command command = new RevenueCommand();
        assertEquals(command.execute(model), command.execute(expectedModel));
    }
}
