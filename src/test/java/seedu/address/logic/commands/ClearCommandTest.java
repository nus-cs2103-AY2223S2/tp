package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalInternships.getTypicalNoteList;
import static seedu.address.testutil.TypicalInternships.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ClearCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_emptyAddressBook_success() {
        model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_NULL, expectedModel);
    }


    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setInternEase(new AddressBook());

        ClearCommand clearCommand = new ClearCommand();
        CommandResult result = clearCommand.execute(model);

        assertCommandSuccess(result, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
