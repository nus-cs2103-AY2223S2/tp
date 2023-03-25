package seedu.internship.logic.commands;

import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternBuddy;

import org.junit.jupiter.api.Test;

import seedu.internship.model.InternBuddy;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;

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
