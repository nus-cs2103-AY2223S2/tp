package seedu.event.logic.commands;

import static seedu.event.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.event.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.event.testutil.TypicalEvents.getTypicalEventBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.event.model.Model;
import seedu.event.model.ModelManager;
import seedu.event.model.UserPrefs;
import seedu.event.model.event.Event;
import seedu.event.testutil.EventBuilder;
import seedu.event.testutil.TypicalContacts;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEventBook(),
                TypicalContacts.getTypicalContactList(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() {
        Event validEvent = new EventBuilder().build();

        Model expectedModel = new ModelManager(model.getEventBook(),
                model.getContactList(), new UserPrefs());
        expectedModel.addEvent(validEvent);

        assertCommandSuccess(new AddCommand(validEvent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEvent), expectedModel);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event eventInList = model.getEventBook().getEventList().get(0);
        assertCommandFailure(new AddCommand(eventInList), model, AddCommand.MESSAGE_DUPLICATE_EVENT);
    }

}
