package teambuilder.logic.commands;

import static teambuilder.logic.commands.CommandTestUtil.assertCommandSuccess;
import static teambuilder.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import teambuilder.model.Model;
import teambuilder.model.ModelManager;
import teambuilder.model.TeamBuilder;
import teambuilder.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new TeamBuilder());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
