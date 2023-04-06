package seedu.address.logic.commands.task.note;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NoteList;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ClearNoteCommand}.
 */
public class ClearNoteCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_emptyNoteList_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), new NoteList());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                new NoteList());

        assertCommandSuccess(new ClearNoteCommand(), model, ClearNoteCommand.MESSAGE_NULL, expectedModel);
    }


    @Test
    public void execute_nonEmptyNoteList_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                new NoteList());

        ClearNoteCommand clearNoteCommand = new ClearNoteCommand();
        CommandResult result = clearNoteCommand.execute(model);

        assertCommandSuccess(result, model, ClearNoteCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
