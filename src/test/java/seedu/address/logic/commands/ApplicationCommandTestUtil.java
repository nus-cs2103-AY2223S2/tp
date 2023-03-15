package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.InternshipBook;
import seedu.address.model.application.Application;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

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

    public static final String ROLE_DESC_BYTEDANCE = " " + PREFIX_ROLE + VALID_ROLE_BYTEDANCE;
    public static final String ROLE_DESC_GRAB = " " + PREFIX_ROLE + VALID_ROLE_GRAB;
    public static final String COMPANY_NAME_DESC_BYTEDANCE = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_BYTEDANCE;
    public static final String COMPANY_NAME_DESC_GRAB = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_GRAB;
    public static final String COMPANY_EMAIL_DESC_BYTEDANCE = " " + PREFIX_COMPANY_EMAIL
            + VALID_COMPANY_EMAIL_BYTEDANCE;
    public static final String COMPANY_EMAIL_DESC_GRAB = " " + PREFIX_COMPANY_EMAIL + VALID_COMPANY_EMAIL_GRAB;
    public static final String STATUS_DESC_BYTEDANCE = " " + PREFIX_STATUS + VALID_STATUS_BYTEDANCE;
    public static final String STATUS_DESC_GRAB = " " + PREFIX_STATUS + VALID_STATUS_GRAB;

    // empty string not allowed for roles
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE;
    // '&' not allowed in company names
    public static final String INVALID_COMPANY_NAME_DESC = " " + PREFIX_COMPANY_NAME + "Meta&";
    // company email missing '@' symbol
    public static final String INVALID_COMPANY_EMAIL_DESC = " " + PREFIX_COMPANY_EMAIL + "bob!yahoo";
    // status must be within given range of values
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "waitlisted";
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
    public static void assertCommandSuccess(ApplicationCommand command, ApplicationModel actualModel,
                                            CommandResult expectedCommandResult, ApplicationModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(ApplicationCommand, ApplicationModel,
     * CommandResult, ApplicationModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(ApplicationCommand command, ApplicationModel actualModel,
                                            String expectedMessage, ApplicationModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the internship book, filtered application list and selected application in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(ApplicationCommand command, ApplicationModel actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternshipBook expectedInternshipBook = new InternshipBook(actualModel.getInternshipBook());
        List<Application> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicationList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternshipBook, actualModel.getInternshipBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicationList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s internship book.
     */
    public static void showPersonAtIndex(ApplicationModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        Application application = model.getFilteredApplicationList().get(targetIndex.getZeroBased());
        String companyName = application.getCompanyName().name;
        model.updateFilteredApplicationList(new NameContainsKeywordsPredicate(Arrays.asList(companyName)));

        assertEquals(1, model.getFilteredApplicationList().size());
    }

}
