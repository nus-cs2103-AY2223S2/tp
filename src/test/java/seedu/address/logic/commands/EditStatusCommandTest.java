package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_SOFTWARE_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.InterviewDate;
import seedu.address.testutil.InternshipBuilder;

public class EditStatusCommandTest {
    private Model model;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        EditStatusCommand editStatusCommand = new EditStatusCommand(Index.fromOneBased(2),
                InternshipStatus.ACCEPTED);

        InternshipApplication updatedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BANK_OF_AMERICA)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_ENGINEER)
                .withStatus(InternshipStatus.ACCEPTED)
                .withInterviewDate(new InterviewDate("2023-04-09 12:00 PM")).build();

        String expectedMessage = String.format(EditStatusCommand.MESSAGE_UPDATE_STATUS_SUCCESS, updatedApplication);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(1), updatedApplication);

        try {
            CommandResult archivedMessage = editStatusCommand.execute(model);
            assertCommandSuccess(archivedMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        EditStatusCommand editStatusCommand = new EditStatusCommand(outOfBoundIndex, InternshipStatus.ACCEPTED);

        assertCommandFailure(editStatusCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_SECOND_APPLICATION);
        EditStatusCommand editStatusCommand = new EditStatusCommand(Index.fromOneBased(1),
                InternshipStatus.ACCEPTED);

        InternshipApplication updatedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BANK_OF_AMERICA)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_ENGINEER)
                .withStatus(InternshipStatus.ACCEPTED)
                .withInterviewDate(new InterviewDate("2023-04-09 12:00 PM")).build();

        String expectedMessage = String.format(EditStatusCommand.MESSAGE_UPDATE_STATUS_SUCCESS, updatedApplication);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), updatedApplication);

        try {
            CommandResult archivedMessage = editStatusCommand.execute(model);
            assertCommandSuccess(archivedMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_SECOND_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        EditStatusCommand editStatusCommand = new EditStatusCommand(outOfBoundIndex, InternshipStatus.ACCEPTED);

        assertCommandFailure(editStatusCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStatusCommand standardCommand = new EditStatusCommand(INDEX_FIRST_APPLICATION,
                InternshipStatus.ACCEPTED);

        // same values -> returns true
        EditStatusCommand commandWithSameValues = new EditStatusCommand(INDEX_FIRST_APPLICATION,
                InternshipStatus.ACCEPTED);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStatusCommand(INDEX_SECOND_APPLICATION, InternshipStatus.ACCEPTED)));

        // different status -> returns false
        assertFalse(standardCommand.equals(new EditStatusCommand(INDEX_FIRST_APPLICATION, InternshipStatus.REJECTED)));
    }
}
