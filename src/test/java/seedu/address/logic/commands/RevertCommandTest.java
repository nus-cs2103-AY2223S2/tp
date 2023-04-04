package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RevertCommand.MESSAGE_DUPLICATE_APPLICATION;
import static seedu.address.logic.commands.RevertCommand.MESSAGE_NO_APPLICATIONS;
import static seedu.address.logic.commands.RevertCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.InternshipApplication;

/**
 * Contains integration tests (interaction with the Model) for {@code RevertCommand}.
 */
public class RevertCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_validCacheList_success() {
        InternshipApplication ia1 = model.getSortedFilteredInternshipList().get(Index.fromOneBased(3).getZeroBased());
        InternshipApplication ia2 = model.getSortedFilteredInternshipList().get(Index.fromOneBased(1).getZeroBased());
        model.deleteInternship(ia1);
        model.addInternshipToCache(ia1);
        model.deleteInternship(ia2);
        model.addInternshipToCache(ia2);
        RevertCommand revertCommand = new RevertCommand();

        String expectedMessage = String.format(MESSAGE_SUCCESS, ia2);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.deleteInternship(ia1);
        expectedModel.addInternshipToCache(ia1);
        expectedModel.deleteInternship(ia2);
        expectedModel.addInternshipToCache(ia2);
        expectedModel.addApplication(ia2);

        CommandResult revertMessage = revertCommand.execute(model);
        assertCommandSuccess(revertMessage, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_duplicateInternships_error() {
        InternshipApplication ia1 = model.getSortedFilteredInternshipList().get(Index.fromOneBased(3).getZeroBased());
        InternshipApplication ia2 = model.getSortedFilteredInternshipList().get(Index.fromOneBased(1).getZeroBased());
        model.deleteInternship(ia1);
        model.addInternshipToCache(ia1);
        model.deleteInternship(ia2);
        model.addInternshipToCache(ia2);
        model.addApplication(ia2);
        RevertCommand revertCommand = new RevertCommand();

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.deleteInternship(ia1);
        expectedModel.addInternshipToCache(ia1);
        expectedModel.deleteInternship(ia2);
        expectedModel.addInternshipToCache(ia2);
        expectedModel.addApplication(ia2);

        CommandResult revertMessage = revertCommand.execute(model);
        assertCommandSuccess(revertMessage, model, MESSAGE_DUPLICATE_APPLICATION, expectedModel);

    }

    @Test
    public void execute_emptyCacheList_success() {
        model = new ModelManager();

        RevertCommand revertCommand = new RevertCommand();

        ModelManager expectedModel = new ModelManager();

        CommandResult revertMessage = revertCommand.execute(model);
        assertCommandSuccess(revertMessage, model, MESSAGE_NO_APPLICATIONS, expectedModel);
    }
}
