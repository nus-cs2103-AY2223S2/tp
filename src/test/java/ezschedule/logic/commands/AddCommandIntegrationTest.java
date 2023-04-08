package ezschedule.logic.commands;

import static ezschedule.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ezschedule.testutil.TypicalEvents.OVERLAP_ART_EVENT;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.Event;
import ezschedule.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the {@code Model}) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalScheduler(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() {
        Event validEvent = new EventBuilder().build();

        Model expectedModel = new ModelManager(model.getScheduler(), new UserPrefs());
        expectedModel.addEvent(validEvent);

        assertCommandSuccess(new AddCommand(validEvent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEvent), expectedModel);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event eventInList = model.getScheduler().getEventList().get(0);
        CommandTestUtil.assertCommandFailure(new AddCommand(eventInList), model, AddCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_eventExistAtTime_throwsCommandException() {
        CommandTestUtil.assertCommandFailure(new AddCommand(OVERLAP_ART_EVENT), model,
                AddCommand.MESSAGE_EVENT_EXIST_AT_TIME);
    }
}
