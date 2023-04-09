package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.ALICE;
import static seedu.address.testutil.TypicalInternships.AMAZON;
import static seedu.address.testutil.TypicalInternships.BANK_OF_AMERICA;
import static seedu.address.testutil.TypicalInternships.BENSON;
import static seedu.address.testutil.TypicalInternships.CARL;
import static seedu.address.testutil.TypicalInternships.DANIEL;
import static seedu.address.testutil.TypicalInternships.ELLE;
import static seedu.address.testutil.TypicalInternships.FIONA;
import static seedu.address.testutil.TypicalInternships.GEORGE;
import static seedu.address.testutil.TypicalInternships.GOOGLE;
import static seedu.address.testutil.TypicalInternships.HARRY;
import static seedu.address.testutil.TypicalInternships.IAN;
import static seedu.address.testutil.TypicalInternships.JAMES;
import static seedu.address.testutil.TypicalInternships.META;
import static seedu.address.testutil.TypicalInternships.MICRON;
import static seedu.address.testutil.TypicalInternships.NETFLIX;
import static seedu.address.testutil.TypicalInternships.ORACLE;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.comparator.CompanyNameComparator;
import seedu.address.model.application.comparator.InterviewDateComparator;
import seedu.address.model.application.comparator.JobTitleComparator;
import seedu.address.model.application.comparator.StatusComparator;

public class SortCommandTest {
    private Model model = new ModelManager(
            getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    private Model expectedModel = new ModelManager(
            getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void equals() {
        CompanyNameComparator firstCmp = new CompanyNameComparator();
        JobTitleComparator secondCmp = new JobTitleComparator();

        SortCommand sortFirstCommand = new SortCommand(firstCmp);
        SortCommand sortSecondCommand = new SortCommand(secondCmp);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(firstCmp);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different cmp -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void execute_sortByCompanyName_sorted() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 17);
        CompanyNameComparator cmp = new CompanyNameComparator();
        SortCommand command = new SortCommand(cmp);
        expectedModel.updateSortedFilteredInternshipList(cmp);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(
                ALICE, AMAZON, BANK_OF_AMERICA, BENSON, CARL, DANIEL, ELLE, JAMES, FIONA, GEORGE, GOOGLE, HARRY,
                IAN, META, MICRON, NETFLIX, ORACLE),
                model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_sortByJobTitle_sorted() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 17);
        JobTitleComparator cmp = new JobTitleComparator();
        SortCommand command = new SortCommand(cmp);
        expectedModel.updateSortedFilteredInternshipList(cmp);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedFilteredInternshipList(), model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_sortByInterviewDate_sorted() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 17);
        InterviewDateComparator cmp = new InterviewDateComparator();
        SortCommand command = new SortCommand(cmp);
        expectedModel.updateSortedFilteredInternshipList(cmp);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedFilteredInternshipList(), model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_sortByStatus_sorted() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 17);
        StatusComparator cmp = new StatusComparator();
        SortCommand command = new SortCommand(cmp);
        expectedModel.updateSortedFilteredInternshipList(cmp);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedFilteredInternshipList(), model.getSortedFilteredInternshipList());
    }
}
