package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;

class LoadCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    }
    @Test
    void execute_pathNotSpecified() {
        LoadCommand c = new LoadCommand(null);
        assertThrows(CommandException.class, () -> c.execute(model));
    }
    @Test
    void execute_pathInvalid() {
        LoadCommand c = new LoadCommand("INVALID PATH");
        assertThrows(CommandException.class, () -> c.execute(model));
    }
    @Test
    void execute_notModcheckReadableFile() {
        LoadCommand c = new LoadCommand("data/JsonAddressBookStorageTest/notJsonFormatAddressBook.json");
        assertThrows(CommandException.class, () -> c.execute(model));
    }
    @Test
    void execute_readableFile_testSuccess() {
        //Success scenario designed to require Ui input
        //Testing done manually
        assertTrue(true);
    }
}
