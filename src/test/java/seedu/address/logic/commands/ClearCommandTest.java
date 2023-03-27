package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskBook;
import seedu.address.model.TaskBookModel;
import seedu.address.model.TaskBookModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        TaskBookModel taskBookModel = new TaskBookModelManager();
        Model expectedModel = new ModelManager();
        TaskBookModel expectedTaskBookModel = new TaskBookModelManager();

        assertCommandSuccess(new ClearCommand(), model, taskBookModel, ClearCommand.MESSAGE_SUCCESS,
                expectedModel, expectedTaskBookModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        TaskBookModel taskBookModel = new TaskBookModelManager();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        TaskBookModel expectedTaskBookModel = new TaskBookModelManager();
        expectedModel.setAddressBook(new AddressBook());
        expectedTaskBookModel.setTaskBook(new TaskBook());

        assertCommandSuccess(new ClearCommand(), model, taskBookModel, ClearCommand.MESSAGE_SUCCESS,
                expectedModel, expectedTaskBookModel);
    }

}
