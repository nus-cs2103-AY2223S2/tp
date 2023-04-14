package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBackups.getTypicalBackData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ViewBackupsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(),
            getTypicalBackData());
        expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
            model.getBackupData());
    }

    @Test
    public void execute_success() {
        ViewBackupsCommand toTest = new ViewBackupsCommand();
        assertCommandSuccess(toTest, model, toTest.execute(model), expectedModel);
    }
}
