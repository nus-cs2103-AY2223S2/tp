package arb.logic.commands.client;

import static arb.logic.commands.CommandTestUtil.assertCommandFailure;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import arb.commons.core.Messages;
import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.testutil.TypicalProjects;

public class ClearClientCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearClientCommand(), ListType.CLIENT, ListType.CLIENT, model,
                ClearClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalProjects.getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(new ClearClientCommand(), ListType.CLIENT, ListType.CLIENT, model,
                ClearClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_withCurrentListTypeProject_failure() {
        assertCommandFailure(new ClearClientCommand(), ListType.PROJECT, new ModelManager(),
                Messages.MESSAGE_INVALID_LIST_CLIENT);
    }

}
