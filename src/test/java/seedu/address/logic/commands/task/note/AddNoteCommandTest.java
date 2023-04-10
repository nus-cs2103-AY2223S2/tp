package seedu.address.logic.commands.task.note;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NoteList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Note;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddNoteCommand}.
 */
public class AddNoteCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_noteAcceptedByModel_addSuccessful() {
        Note validNote = new NoteBuilder().build();
        NoteList noteList = getTypicalNoteList();
        noteList.addNote(validNote);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalTodoList(), noteList);

        assertCommandSuccess(new AddNoteCommand(validNote), model,
                String.format(AddNoteCommand.MESSAGE_SUCCESS, validNote), expectedModel);
    }

    @Test
    public void execute_duplicateNote_throwsCommandException() {
        Note validNote = model.getFilteredNoteList().get(0);

        assertCommandFailure(new AddNoteCommand(validNote), model, AddNoteCommand.MESSAGE_DUPLICATE_NOTE);
    }
}
