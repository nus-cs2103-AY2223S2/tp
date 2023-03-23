package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalInternBuddy;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternBuddy;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyInternBuddy_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternBuddy_success() {
        Model model = new ModelManager(getTypicalInternBuddy(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInternBuddy(), new UserPrefs());
        expectedModel.setInternBuddy(new InternBuddy());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
