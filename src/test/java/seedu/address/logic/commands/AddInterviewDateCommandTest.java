package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_BANK_OF_AMERICA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_CARL_KURZ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_SOFTWARE_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_WEB_DEVELOPER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.model.application.InternshipStatus.DECLINED;
import static seedu.address.model.application.InternshipStatus.PENDING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_APPLICATION;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InterviewDate;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.InterviewDateBuilder;

public class AddInterviewDateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        InterviewDate interviewDate = new InterviewDate("2023-04-09 12:00 PM");
        InternshipApplication initialApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_CARL_KURZ)
                .withJobTitle(VALID_JOB_TITLE_WEB_DEVELOPER)
                .withStatus(PENDING).build();
        InternshipApplication interviewDateAddedApplication = new InternshipBuilder()
                .withCompanyName(VALID_COMPANY_NAME_CARL_KURZ)
                .withJobTitle(VALID_JOB_TITLE_WEB_DEVELOPER)
                .withStatus(PENDING)
                .withInterviewDate(interviewDate).build();
        AddInterviewDateCommand addInterviewDateCommand = new AddInterviewDateCommand(INDEX_SEVENTH_APPLICATION,
                interviewDate);

        String expectedMessage = String.format(AddInterviewDateCommand.MESSAGE_ADD_INTERVIEW_DATE_SUCCESS,
                initialApplication + "\n" + interviewDate);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(6), interviewDateAddedApplication);

        assertCommandSuccess(addInterviewDateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        InterviewDate interviewDate = new InterviewDateBuilder().build();
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        AddInterviewDateCommand addInterviewDateCommand = new AddInterviewDateCommand(outOfBoundIndex, interviewDate);

        assertCommandFailure(addInterviewDateCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_SECOND_APPLICATION);
        InternshipApplication applicationInFilteredList =
                model.getSortedFilteredInternshipList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        InternshipApplication initialApplication =
                new InternshipBuilder(applicationInFilteredList).build();
        InterviewDate interviewDate = new InterviewDateBuilder().build();
        InternshipApplication interviewDateAddedApplication =
                new InternshipBuilder(applicationInFilteredList).withInterviewDate(interviewDate).build();
        AddInterviewDateCommand addInterviewDateCommand = new AddInterviewDateCommand(INDEX_FIRST_APPLICATION,
                interviewDate);
        String expectedMessage = String.format(AddInterviewDateCommand.MESSAGE_ADD_INTERVIEW_DATE_SUCCESS,
                initialApplication + "\n" + interviewDate);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTodoList(), getTypicalNoteList());
        expectedModel.setApplication(model.getSortedFilteredInternshipList().get(0), interviewDateAddedApplication);

        assertCommandSuccess(addInterviewDateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_SECOND_APPLICATION);
        InterviewDate interviewDate = new InterviewDateBuilder().build();
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());
        AddInterviewDateCommand addInterviewDateCommand = new AddInterviewDateCommand(outOfBoundIndex, interviewDate);

        assertCommandFailure(addInterviewDateCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }
}
