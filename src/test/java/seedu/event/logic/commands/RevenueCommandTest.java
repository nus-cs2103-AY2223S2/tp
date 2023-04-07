package seedu.event.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.event.testutil.TypicalEvents.getTypicalEventBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.model.Model;
import seedu.event.model.ModelManager;
import seedu.event.model.UserPrefs;
import seedu.event.testutil.TypicalContacts;

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
