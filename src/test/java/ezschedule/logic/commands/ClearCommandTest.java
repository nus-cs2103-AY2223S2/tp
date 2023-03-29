package ezschedule.logic.commands;

import static ezschedule.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;

import org.junit.jupiter.api.Test;

import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.Scheduler;
import ezschedule.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyScheduler_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyScheduler_success() {
        Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalScheduler(), new UserPrefs());
        expectedModel.setScheduler(new Scheduler());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
