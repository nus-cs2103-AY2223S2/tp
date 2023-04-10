package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_SOFTWARE_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_SOFTWARE_TESTER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_META;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.model.application.InternshipStatus.ACCEPTED;
import static seedu.address.model.application.InternshipStatus.DECLINED;
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
import seedu.address.model.application.InterviewDate;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class ArchiveCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ArchiveCommand archiveCommand = new ArchiveCommand(Index.fromOneBased(2));

        InternshipApplication archivedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BANK_OF_AMERICA)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_ENGINEER)
                .withInterviewDate(new InterviewDate("2023-04-09 12:00 PM"))
                .withStatus(DECLINED)
                .withIsArchived(true).build();

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_APPLICATION_SUCCESS, archivedApplication);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(1), archivedApplication);

        try {
            CommandResult archivedMessage = archiveCommand.execute(model);
            assertCommandSuccess(archivedMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, Index.fromOneBased(1));
        ArchiveCommand archiveCommand = new ArchiveCommand(Index.fromOneBased(1));

        InternshipApplication archivedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_META)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_TESTER)
                .withContact(new Contact(new Phone(VALID_PHONE_META), new Email(VALID_EMAIL_META)))
                .withInterviewDate(new InterviewDate("2023-04-01 08:00 PM"))
                .withStatus(ACCEPTED)
                .withIsArchived(true).build();

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_APPLICATION_SUCCESS, archivedApplication);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), archivedApplication);

        try {
            CommandResult archivedMessage = archiveCommand.execute(model);
            assertCommandSuccess(archivedMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, Index.fromOneBased(3));

        Index outOfBoundIndex = Index.fromOneBased(3);
        // ensures that outOfBoundIndex is still in bounds of internships list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArchiveCommand archiveFirstCommand = new ArchiveCommand(Index.fromOneBased(3));
        ArchiveCommand archiveSecondCommand = new ArchiveCommand(Index.fromOneBased(5));

        // same object -> returns true
        assertTrue(archiveFirstCommand.equals(archiveFirstCommand));

        // same values -> returns true
        ArchiveCommand archiveFirstCommandCopy = new ArchiveCommand(Index.fromOneBased(3));
        assertTrue(archiveFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(archiveFirstCommandCopy.equals(1));

        // null -> returns false
        assertFalse(archiveFirstCommandCopy.equals(null));

        // different person -> returns false
        assertFalse(archiveFirstCommandCopy.equals(archiveSecondCommand));
    }
}
