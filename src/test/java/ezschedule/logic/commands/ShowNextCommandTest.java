package ezschedule.logic.commands;

import static ezschedule.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ezschedule.logic.commands.ShowNextCommand.SHOW_UPCOMING_COUNT_ONE;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.Messages;
import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.Scheduler;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.UpcomingEventPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ShowNextCommand.
 */
public class ShowNextCommandTest {

    private Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());

    @Test
    public void execute_typicalSchedulerShowOne_listOneEvent() {
        ShowNextCommand command = new ShowNextCommand(new UpcomingEventPredicate(SHOW_UPCOMING_COUNT_ONE));

        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, SHOW_UPCOMING_COUNT_ONE);
        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());
        expectedModel.updateUpcomingEventList(new UpcomingEventPredicate(SHOW_UPCOMING_COUNT_ONE));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_typicalSchedulerShowTwo_listTwoEvent() {
        final int count = 2;
        ShowNextCommand command = new ShowNextCommand(new UpcomingEventPredicate(count));

        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, count);
        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());
        expectedModel.updateUpcomingEventList(new UpcomingEventPredicate(count));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_typicalSchedulerShowMany_listFourEvent() {
        // TypicalScheduler only have 4 events
        assert (getTypicalScheduler().getEventList().size() == 4);

        final int count = 500; // A large number ("many events") that exceeds the number of events in scheduler.
        ShowNextCommand command = new ShowNextCommand(new UpcomingEventPredicate(count));

        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 4);
        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());
        expectedModel.updateUpcomingEventList(new UpcomingEventPredicate(count));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

}
