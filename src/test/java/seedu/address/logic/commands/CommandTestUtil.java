package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ListingBook;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.TitleContainsKeywordsPredicate;
import seedu.address.testutil.EditListingDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // valid applicant info
    public static final String VALID_APPLICANT_NAME_BENEDICT = "Benedict Green";
    public static final String VALID_APPLICANT_NAME_BENEDICT_DESC = " " + PREFIX_APPLICANT
            + VALID_APPLICANT_NAME_BENEDICT;
    public static final String VALID_APPLICANT_NAME_CHRIS = "Chris Toper";
    public static final String VALID_APPLICANT_NAME_CHRIS_DESC = " " + PREFIX_APPLICANT
            + VALID_APPLICANT_NAME_CHRIS;

    public static final String VALID_TITLE =
            "Entry Level Software Developer – Mentorship program and option to work remotely!";
    public static final String VALID_TITLE_ALT =
            "Entry Level Software Engineer – Mentorship program and option to work remotely!";
    public static final String VALID_DESCRIPTION =
            "We’re looking for a full-time entry-level software developer. "
                    + "The ideal candidate is someone who’s just out of school "
                    + "and looking for some quality career experience. "
                    + "Salary is $35,000 a year with opportunity for advancement, bonuses and paid sick leave. "
                    + "Remote work is possible.";
    public static final String VALID_DESCRIPTION_ALT =
            "We’re looking for a full-time entry-level software engineer. "
                    + "The ideal candidate is someone who’s just out of school "
                    + "and looking for some quality career experience. "
                    + "Salary is $35,000 a year with opportunity for advancement, bonuses and paid sick leave. "
                    + "Remote work is possible.";
    public static final ArrayList<Applicant> VALID_NO_APPLICANTS = new ArrayList<>();

    public static final String VALID_TITLE_DESC = " " + PREFIX_TITLE + VALID_TITLE;
    public static final String VALID_TITLE_DESC_ALT = " " + PREFIX_TITLE + VALID_TITLE_ALT;
    public static final String VALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION;
    public static final String VALID_DESCRIPTION_DESC_ALT = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_ALT;
    public static final String VALID_APPLICANTS_DESC =
            " " + PREFIX_APPLICANT + VALID_APPLICANT_NAME_BENEDICT
            + " " + PREFIX_APPLICANT + VALID_APPLICANT_NAME_CHRIS;
    public static final String VALID_APPLICANTS_DESC_ALT =
            " " + PREFIX_APPLICANT + VALID_APPLICANT_NAME_BENEDICT;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "TITLE\n"; // '\n' not allowed in titles
    public static final String INVALID_DESCRIPTION_DESC =
            " " + PREFIX_DESCRIPTION + "^"; // '^' only not allowed in descriptions
    public static final String INVALID_APPLICANT_DESC =
            " " + PREFIX_APPLICANT + "peter*"; // '*' not allowed in applicant names

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditListingDescriptor DESC_LISTING;
    public static final EditCommand.EditListingDescriptor DESC_LISTING_ALT;

    static {
        DESC_LISTING = new EditListingDescriptorBuilder().withJobTitle(VALID_TITLE)
                .withJobDescription(VALID_DESCRIPTION)
                .withApplicants(VALID_APPLICANT_NAME_BENEDICT).build();
        DESC_LISTING_ALT = new EditListingDescriptorBuilder().withJobTitle(VALID_TITLE_ALT)
                .withJobDescription(VALID_DESCRIPTION_ALT)
                .withApplicants(VALID_APPLICANT_NAME_BENEDICT, VALID_APPLICANT_NAME_CHRIS).build();
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
     * - the listing book, filtered listing book and selected listing in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ListingBook expectedListingBook = new ListingBook(actualModel.getListingBook());
        List<Listing> expectedFilteredList = new ArrayList<>(actualModel.getFilteredListingList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedListingBook, actualModel.getListingBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredListingList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the listing at the given {@code targetIndex} in the
     * {@code model}'s listing book.
     */
    public static void showListingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredListingList().size());

        Listing listing = model.getFilteredListingList().get(targetIndex.getZeroBased());
        final String[] splitName = listing.getTitle().fullTitle.split("\\s+");
        model.updateFilteredListingList(new TitleContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredListingList().size());
    }

}
