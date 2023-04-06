package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.ClearByCommand.MESSAGE_CLEAR_SUCCESS;
import static seedu.address.logic.commands.ClearByCommand.MESSAGE_EMPTY_FILTERED_LIST;
import static seedu.address.logic.commands.ClearByCommand.MESSAGE_NULL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalInternships.getTypicalInternships;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.JobTitle;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ClearByCommand}.
 */
public class ClearByCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_clearByCompanyName_success() {
        CompanyName companyName = new CompanyName("Alice Wonder");

        ClearByCommand clearByCommand = new ClearByCommand(companyName);

        CommandResult expectedMessage = new CommandResult(
                String.format(MESSAGE_CLEAR_SUCCESS, "Company name", companyName.fullName));

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());

        List<InternshipApplication> filteredList = getTypicalInternships().stream()
                .filter(a -> (a.getCompanyName()).equals(companyName))
                .collect(Collectors.toList());

        for (InternshipApplication ia : filteredList) {
            expectedModel.deleteInternship(ia);
        }

        try {
            CommandResult clearByMessage = clearByCommand.execute(model);
            assertCommandSuccess(clearByMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_clearByJobTitle_success() {
        JobTitle jobTitle = new JobTitle("Software Engineer");

        ClearByCommand clearByCommand = new ClearByCommand(jobTitle);

        CommandResult expectedMessage = new CommandResult(
                String.format(MESSAGE_CLEAR_SUCCESS, "Job title", jobTitle.fullName));

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());

        List<InternshipApplication> filteredList = getTypicalInternships().stream()
                .filter(a -> (a.getJobTitle()).equals(jobTitle))
                .collect(Collectors.toList());

        for (InternshipApplication ia : filteredList) {
            expectedModel.deleteInternship(ia);
        }

        try {
            CommandResult clearByMessage = clearByCommand.execute(model);
            assertCommandSuccess(clearByMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_clearByStatus_success() {
        InternshipStatus status = InternshipStatus.PENDING;

        ClearByCommand clearByCommand = new ClearByCommand(status);

        CommandResult expectedMessage = new CommandResult(
                String.format(MESSAGE_CLEAR_SUCCESS, "Status", status));

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());

        List<InternshipApplication> filteredList = getTypicalInternships().stream()
                .filter(a -> (a.getStatus().equals(status) && !a.isArchived()))
                .collect(Collectors.toList());

        for (InternshipApplication ia : filteredList) {
            expectedModel.deleteInternship(ia);
        }

        try {
            CommandResult clearByMessage = clearByCommand.execute(model);
            assertCommandSuccess(clearByMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_clearByEmptyList_error() {
        CompanyName companyName = new CompanyName("Never Found");

        ClearByCommand clearByCommand = new ClearByCommand(companyName);

        CommandResult expectedMessage = new CommandResult(
                String.format(MESSAGE_EMPTY_FILTERED_LIST, "Company name", companyName.fullName));

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());

        List<InternshipApplication> filteredList = getTypicalInternships().stream()
                .filter(a -> (a.getCompanyName()).equals(companyName))
                .collect(Collectors.toList());

        for (InternshipApplication ia : filteredList) {
            expectedModel.deleteInternship(ia);
        }

        try {
            CommandResult clearByMessage = clearByCommand.execute(model);
            assertCommandSuccess(clearByMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_clearByEmptyFilteredList_error() {
        model = new ModelManager();
        InternshipStatus status = InternshipStatus.PENDING;

        ClearByCommand clearByCommand = new ClearByCommand(status);

        CommandResult expectedMessage = new CommandResult(MESSAGE_NULL);

        ModelManager expectedModel = new ModelManager();
        try {
            CommandResult clearByMessage = clearByCommand.execute(model);
            assertCommandSuccess(clearByMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void equals() {
        ClearByCommand clearByFirstCommand = new ClearByCommand(new CompanyName("Company 1"));
        ClearByCommand clearBySecondCommand = new ClearByCommand(new JobTitle("Developer"));
        ClearByCommand clearByThirdCommand = new ClearByCommand(InternshipStatus.PENDING);

        // same object -> returns true
        assertTrue(clearByFirstCommand.equals(clearByFirstCommand));
        assertTrue(clearByThirdCommand.equals(clearByThirdCommand));

        // same values -> returns true
        ClearByCommand clearByFirstCommandCopy = new ClearByCommand(new CompanyName("Company 1"));
        assertTrue(clearByFirstCommand.equals(clearByFirstCommandCopy));

        // different types -> returns false
        assertFalse(clearByFirstCommand.equals(1));

        // null -> returns false
        assertFalse(clearByFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(clearByFirstCommand.equals(clearBySecondCommand));
        assertFalse(clearBySecondCommand.equals(clearByThirdCommand));
        assertFalse(clearByThirdCommand.equals(clearByFirstCommand));
    }
}
