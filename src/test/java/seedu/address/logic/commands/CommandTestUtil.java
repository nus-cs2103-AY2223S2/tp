package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternBuddy;
import seedu.address.model.Model;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_COMPANY_NAME_APPLE = "Apple";
    public static final String VALID_COMPANY_NAME_GOOGLE = "Google";
    public static final String VALID_ROLE_APPLE = "Mobile Developer";
    public static final String VALID_ROLE_GOOGLE = "Software Developer";
    public static final String VALID_STATUS_APPLE = "interview";
    public static final String VALID_STATUS_GOOGLE = "applied";
    public static final String VALID_DATE_APPLE = "2023-02-01";
    public static final String VALID_DATE_GOOGLE = "2023-02-03";
    public static final String VALID_TAG_FRONT = "frontend";
    public static final String VALID_TAG_BACK = "backend";

    public static final String COMPANY_NAME_DESC_APPLE = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_APPLE;
    public static final String COMPANY_NAME_DESC_GOOGLE = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_GOOGLE;
    public static final String ROLE_DESC_APPLE = " " + PREFIX_ROLE + VALID_ROLE_APPLE;
    public static final String ROLE_DESC_GOOGLE = " " + PREFIX_ROLE + VALID_ROLE_GOOGLE;
    public static final String STATUS_DESC_APPLE = " " + PREFIX_STATUS + VALID_STATUS_APPLE;
    public static final String STATUS_DESC_GOOGLE = " " + PREFIX_STATUS + VALID_STATUS_GOOGLE;
    public static final String DATE_DESC_APPLE = " " + PREFIX_DATE + VALID_DATE_APPLE;
    public static final String DATE_DESC_GOOGLE = " " + PREFIX_DATE + VALID_DATE_GOOGLE;
    public static final String TAG_DESC_FRONT = " " + PREFIX_TAG + VALID_TAG_FRONT;
    public static final String TAG_DESC_BACK = " " + PREFIX_TAG + VALID_TAG_BACK;

    // '&' not allowed in company names
    public static final String INVALID_COMPANY_NAME_DESC = " " + PREFIX_COMPANY_NAME + "Grab&";
    // '@' not allowed in roles
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + "Front@Developer";
    // pending is not a possible value for status
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "pending";
    // invalid date format where dates have to be yyyy-MM-dd
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "23-02-02";
    // tag more than 30 characters
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternshipDescriptor DESC_APPLE;
    public static final EditCommand.EditInternshipDescriptor DESC_GOOGLE;

    static {
        DESC_APPLE = new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_APPLE)
                .withRole(VALID_ROLE_APPLE).withStatus(VALID_STATUS_APPLE).withDate(VALID_DATE_APPLE)
                .withTags(VALID_TAG_FRONT).build();
        DESC_GOOGLE = new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_GOOGLE)
                .withRole(VALID_ROLE_GOOGLE).withStatus(VALID_STATUS_GOOGLE).withDate(VALID_DATE_GOOGLE)
                .withTags(VALID_TAG_BACK, VALID_TAG_FRONT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternBuddy expectedInternBuddy = new InternBuddy(actualModel.getInternBuddy());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternBuddy, actualModel.getInternBuddy());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showInternshipAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternshipList().size());

        Internship internship = model.getFilteredInternshipList().get(targetIndex.getZeroBased());
        final String[] splitCompanyName = internship.getCompanyName().fullCompanyName.split("\\s+");
        final String[] splitRole = internship.getRole().fullRole.split("\\s+");
        final String[] splitStatus = internship.getStatus().fullStatus.split("\\s+");
        final String[] splitTag = internship.getTags().isEmpty()
                ? new String[0]
                : internship.getTags().stream()
                .map(tag -> tag.tagName)
                .map(str -> str.split("\\s+"))
                .findFirst().get();
        model.updateFilteredInternshipList(new InternshipContainsKeywordsPredicate(
                Arrays.asList(splitCompanyName[0]), Arrays.asList(splitRole[0]), Arrays.asList(splitStatus[0]),
                Arrays.asList((splitTag[0]))));

        assertEquals(1, model.getFilteredInternshipList().size());
    }

}
