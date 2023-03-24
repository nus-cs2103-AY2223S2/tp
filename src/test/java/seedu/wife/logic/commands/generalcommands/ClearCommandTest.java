package seedu.wife.logic.commands.generalcommands;

import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.testutil.TypicalWife.getTypicalWife;

import org.junit.jupiter.api.Test;

import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.Wife;

public class ClearCommandTest {

    @Test
    public void execute_emptyWife_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyWife_success() {
        Model model = new ModelManager(getTypicalWife(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalWife(), new UserPrefs());
        expectedModel.setWife(new Wife());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
