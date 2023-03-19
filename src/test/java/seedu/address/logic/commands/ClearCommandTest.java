package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.testutil.ModelManagerBuilder;

public class ClearCommandTest {

    @Test
    public void execute_emptyFriendlyLink_success() {
        Model model = new ModelManagerBuilder().build();
        Model expectedModel = new ModelManagerBuilder().build();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFriendlyLink_success() {
        Model model = getTypicalModelManager();
        Model expectedModel = new ModelManagerBuilder().build();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
