package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.testutil.InternshipBuilder;

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
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalInternships.getTypicalNoteList;
import static seedu.address.testutil.TypicalInternships.getTypicalTodoList;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class UnarchiveCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(Index.fromOneBased(2));

        InternshipApplication unarchivedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BANK_OF_AMERICA)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_ENGINEER)
                .withIsArchived(false).build();

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_APPLICATION_SUCCESS, unarchivedApplication);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(1), unarchivedApplication);

        try {
            CommandResult unarchivedMessage = unarchiveCommand.execute(model);
            assertCommandSuccess(unarchivedMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(unarchiveCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, Index.fromOneBased(1));
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(Index.fromOneBased(1));

        InternshipApplication unarchivedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_META)
                .withJobTitle(VALID_JOB_TITLE_SOFTWARE_TESTER)
                .withContact(new Contact(new Phone(VALID_PHONE_META), new Email(VALID_EMAIL_META)))
                .withIsArchived(false).build();

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_APPLICATION_SUCCESS, unarchivedApplication);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), unarchivedApplication);

        try {
            CommandResult unarchivedMessage = unarchiveCommand.execute(model);
            assertCommandSuccess(unarchivedMessage, model, expectedMessage, expectedModel);

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

        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(unarchiveCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnarchiveCommand unarchiveFirstCommand = new UnarchiveCommand(Index.fromOneBased(3));
        UnarchiveCommand unarchiveSecondCommand = new UnarchiveCommand(Index.fromOneBased(5));

        // same object -> returns true
        assertTrue(unarchiveFirstCommand.equals(unarchiveFirstCommand));

        // same values -> returns true
        UnarchiveCommand unarchiveFirstCommandCopy = new UnarchiveCommand(Index.fromOneBased(3));
        assertTrue(unarchiveFirstCommand.equals(unarchiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(unarchiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unarchiveFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unarchiveFirstCommand.equals(unarchiveSecondCommand));
    }
}
