package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalReadings.getTypicalFullReadingLevels;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
