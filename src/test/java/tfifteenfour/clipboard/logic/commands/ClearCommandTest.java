package tfifteenfour.clipboard.logic.commands;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.TypicalStudents.getTypicalRoster;

import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.ModelManager;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyRoster_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyRoster_success() {
        Model model = new ModelManager(getTypicalRoster(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalRoster(), new UserPrefs());
        expectedModel.setRoster(new Roster());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
