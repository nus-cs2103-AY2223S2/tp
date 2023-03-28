package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;

import org.junit.jupiter.api.Test;

import seedu.address.model.Mathutoring;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ClearCommandTest {

    @Test
    public void execute_emptyMathutoring_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyMathutoring_success() {
        Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMathutoring(), new UserPrefs());
        expectedModel.setMathutoring(new Mathutoring());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
