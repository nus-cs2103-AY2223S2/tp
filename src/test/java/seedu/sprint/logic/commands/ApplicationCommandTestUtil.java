package seedu.sprint.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.sprint.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.Model;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.ApplicationContainsKeywordsPredicate;
import seedu.sprint.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class ApplicationCommandTestUtil {

    public static final String VALID_ROLE_BYTEDANCE = "ML Engineer Intern";
    public static final String VALID_ROLE_GRAB = "Software Engineer Intern";
    public static final String VALID_COMPANY_NAME_BYTEDANCE = "ByteDance";
    public static final String VALID_COMPANY_NAME_GRAB = "Grab";
    public static final String VALID_COMPANY_EMAIL_BYTEDANCE = "hr@bytedance.com";
    public static final String VALID_COMPANY_EMAIL_GRAB = "hr@grab.com";
    public static final String VALID_STATUS_BYTEDANCE = "interested";
    public static final String VALID_STATUS_GRAB = "applied";
    public static final String VALID_DEADLINE = "31-12-2023";
    public static final String VALID_DESCRIPTION = "Online Assessment";
    public static final String VALID_DESCRIPTION_INTERVIEW = "Technical Interview";
    public static final String VALID_TAG_HIGHSALARY = "highSalary";
    public static final String VALID_TAG_SCHOOL = "school";

    public static final String ROLE_DESC_BYTEDANCE = " " + PREFIX_ROLE + VALID_ROLE_BYTEDANCE;
    public static final String ROLE_DESC_GRAB = " " + PREFIX_ROLE + VALID_ROLE_GRAB;
    public static final String COMPANY_NAME_DESC_BYTEDANCE = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_BYTEDANCE;
    public static final String COMPANY_NAME_DESC_GRAB = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_GRAB;
    public static final String COMPANY_EMAIL_DESC_BYTEDANCE = " " + PREFIX_COMPANY_EMAIL
            + VALID_COMPANY_EMAIL_BYTEDANCE;
    public static final String COMPANY_EMAIL_DESC_GRAB = " " + PREFIX_COMPANY_EMAIL + VALID_COMPANY_EMAIL_GRAB;
    public static final String STATUS_DESC_BYTEDANCE = " " + PREFIX_STATUS + VALID_STATUS_BYTEDANCE;
    public static final String DEADLINE_DESC = " " + PREFIX_DEADLINE + VALID_DEADLINE;
    public static final String DESCRIPTION_DESC_ASSESSMENT = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION;
    public static final String DESCRIPTION_DESC_INTERVIEW = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_INTERVIEW;

    public static final String STATUS_DESC_GRAB = " " + PREFIX_STATUS + VALID_STATUS_GRAB;
    public static final String TAG_DESC_SCHOOL = " " + PREFIX_TAG + VALID_TAG_SCHOOL;
    public static final String TAG_DESC_HIGHSALARY = " " + PREFIX_TAG + VALID_TAG_HIGHSALARY;

    // empty string not allowed for roles
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE;
    // '&' not allowed in company names
    public static final String INVALID_COMPANY_NAME_DESC = " " + PREFIX_COMPANY_NAME + "Meta&";
    // company email missing '@' symbol
    public static final String INVALID_COMPANY_EMAIL_DESC = " " + PREFIX_COMPANY_EMAIL + "bob!yahoo";
    // status must be within given range of values
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "waitlisted";
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "OA&"; //'&' not allowed
    public static final String INVALID_DATE_DEADLINE_DESC = " " + PREFIX_DEADLINE + "33-02-2024"; //date does not exist
    public static final String INVALID_FORMAT_DEADLINE_DESC = " "
            + PREFIX_DEADLINE + "01/01/2023"; // invalid date format (should be dd-mm-yyyy)
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditApplicationCommand.EditApplicationDescriptor DESC_ONE;
    public static final EditApplicationCommand.EditApplicationDescriptor DESC_TWO;

    static {
        DESC_ONE = new EditApplicationDescriptorBuilder().withRole(VALID_ROLE_BYTEDANCE)
                .withCompanyName(VALID_COMPANY_NAME_BYTEDANCE).withCompanyEmail(VALID_COMPANY_EMAIL_BYTEDANCE)
                .withStatus(VALID_STATUS_BYTEDANCE).build();
        DESC_TWO = new EditApplicationDescriptorBuilder().withRole(VALID_ROLE_GRAB)
                .withCompanyName(VALID_COMPANY_NAME_GRAB).withCompanyEmail(VALID_COMPANY_EMAIL_GRAB)
                .withStatus(VALID_STATUS_GRAB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandHistory actualCommandHistory,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        actualCommandHistory.addCommand(command.toString());
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        if (command instanceof AddApplicationCommand || command instanceof ClearCommand
                || command instanceof DeleteApplicationCommand || command instanceof EditApplicationCommand
                || command instanceof DeleteTaskCommand
                || command instanceof AddTaskCommand || command instanceof EditTaskCommand) {
            expectedCommandHistory.setLastCommandAsModify();
        }

        if (command instanceof UndoCommand) {
            expectedCommandHistory.getPreviousModifyCommand();
        }

        if (command instanceof RedoCommand) {
            expectedCommandHistory.getNextModifyCommand();
        }
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory,
     * CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandHistory actualCommandHistory,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the internship book, filtered application list and selected application in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel,
                                            CommandHistory actualCommandHistory, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternshipBook expectedInternshipBook = new InternshipBook(actualModel.getInternshipBook());
        List<Application> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicationList());
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, actualCommandHistory));
        assertEquals(expectedInternshipBook, actualModel.getInternshipBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicationList());
        assertEquals(expectedCommandHistory, actualCommandHistory);
    }

    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s internship book.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        Application application = model.getFilteredApplicationList().get(targetIndex.getZeroBased());
        String companyName = application.getCompanyName().name;
        model.updateFilteredApplicationList(new ApplicationContainsKeywordsPredicate(Arrays.asList(companyName)));

        assertEquals(1, model.getFilteredApplicationList().size());
    }


    /**
     * Update {@code model}'s so that it only shows the first internship.
     */
    public static void findFirstInternship(Model model) {
        Application firstInternship = model.getFilteredApplicationList().get(0);
        model.updateFilteredApplicationList(x -> x.equals(firstInternship));
    }

    /**
     * Deletes the first internship in {@code model}'s filtered list from {@code model}'s sprint book.
     */
    public static void deleteFirstInternship(Model model) {
        Application firstInternship = model.getFilteredApplicationList().get(0);
        model.deleteApplication(firstInternship);
        model.commitInternshipBookChange();
    }

    /**
     * Deletes the first internship in {@code model}'s filtered list from {@code model}'s sprint book.
     */
    public static void deleteFirstInternship(Model model, CommandHistory commandHistory) {
        deleteFirstInternship(model);
        commandHistory.addCommand("delete 1");
        commandHistory.setLastCommandAsModify();
    }

    /**
     * Undo previous commands in {@code model}.
     */
    public static void undoPreviousCommand(Model model) {
        model.undoInternshipBook();
    }

    /**
     * Undo previous commands in {@code model}.
     */
    public static void undoPreviousCommand(Model model, CommandHistory commandHistory) {
        undoPreviousCommand(model);
        commandHistory.addCommand("undo");
        commandHistory.getPreviousModifyCommand();
    }

}
