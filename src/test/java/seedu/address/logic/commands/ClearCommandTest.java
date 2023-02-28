package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalFriendlyLink;

import org.junit.jupiter.api.Test;

import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFriendlyLink_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFriendlyLink_success() {
        Model model = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());
        expectedModel.setFriendlyLink(new FriendlyLink());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
