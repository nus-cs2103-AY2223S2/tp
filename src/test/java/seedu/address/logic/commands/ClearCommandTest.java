package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntities.getTypicalReroll;

import org.junit.jupiter.api.Test;

import seedu.address.experimental.model.Reroll;
import seedu.address.experimental.model.Model;
import seedu.address.experimental.model.ModelManager;
import seedu.address.experimental.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalReroll(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalReroll(), new UserPrefs());
        expectedModel.setReroll(new Reroll());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
