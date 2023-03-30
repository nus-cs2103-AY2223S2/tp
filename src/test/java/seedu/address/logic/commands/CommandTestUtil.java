package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_KEYDATE;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.testutil.EditOpeningDescriptorBuilder;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.Command;
import seedu.ultron.logic.commands.CommandResult;
import seedu.ultron.logic.commands.EditCommand;
import seedu.ultron.logic.commands.exceptions.CommandException;
import seedu.ultron.model.Ultron;
import seedu.ultron.model.Model;
import seedu.ultron.model.opening.CompanyOrPositionContainsKeywordsPredicate;
import seedu.ultron.model.opening.Opening;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_POSITION_GOOGLE = "Software Engineer";
    public static final String VALID_POSITION_SHOPEE = "Backend developer";
    public static final String VALID_COMPANY_GOOGLE = "Google";
    public static final String VALID_COMPANY_SHOPEE = "Shopee";
    public static final String VALID_EMAIL_GOOGLE = "google@gmail.com";
    public static final String VALID_EMAIL_SHOPEE = "shopee@shopee.com";
    public static final String VALID_STATUS_GOOGLE = "APPLIED";
    public static final String VALID_STATUS_SHOPEE = "INTERVIEWING";
    public static final String VALID_REMARK_GOOGLE = "Reply expected in early January";
    public static final String VALID_REMARK_SHOPEE = "Interview is going to include design pattern questions";
    public static final String VALID_DATENAME_INTERVIEW = "Interview";
    public static final String VALID_DATENAME_OA = "OA";
    public static final String VALID_DATE_EARLYMAR = "2023-03-01";
    public static final String VALID_DATE_LATEFEB = "2023-02-28";
    public static final ArrayList<String> VALID_KEYDATE_MARCH_INTERVIEW = new ArrayList<>(
            Arrays.asList(VALID_DATENAME_INTERVIEW, VALID_DATE_EARLYMAR));
    public static final ArrayList<String> VALID_KEYDATE_FEB_OA = new ArrayList<>(
            Arrays.asList(VALID_DATENAME_OA, VALID_DATE_LATEFEB));

    public static final String POSITION_DESC_GOOGLE = " " + PREFIX_POSITION + VALID_POSITION_GOOGLE;
    public static final String POSITION_DESC_SHOPEE = " " + PREFIX_POSITION + VALID_POSITION_SHOPEE;
    public static final String COMPANY_DESC_GOOGLE = " " + PREFIX_COMPANY + VALID_COMPANY_GOOGLE;
    public static final String COMPANY_DESC_SHOPEE = " " + PREFIX_COMPANY + VALID_COMPANY_SHOPEE;
    public static final String EMAIL_DESC_GOOGLE = " " + PREFIX_EMAIL + VALID_EMAIL_GOOGLE;
    public static final String EMAIL_DESC_SHOPEE = " " + PREFIX_EMAIL + VALID_EMAIL_SHOPEE;
    public static final String STATUS_DESC_GOOGLE = " " + PREFIX_STATUS + VALID_STATUS_GOOGLE;
    public static final String STATUS_DESC_SHOPEE = " " + PREFIX_STATUS + VALID_STATUS_SHOPEE;
    public static final String REMARK_DESC_GOOGLE = " " + PREFIX_REMARK + VALID_REMARK_GOOGLE;
    public static final String REMARK_DESC_SHOPEE = " " + PREFIX_REMARK + VALID_REMARK_SHOPEE;
    public static final String KEYDATE_DESC_OA = " " + PREFIX_KEYDATE + VALID_DATENAME_OA + "@" + VALID_DATE_LATEFEB;
    public static final String KEYDATE_DESC_INTERVIEW = " " + PREFIX_KEYDATE + VALID_DATENAME_INTERVIEW + "@" + VALID_DATE_EARLYMAR;

    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION + "Software Engineer&"; // '&' not allowed in positions
    public static final String INVALID_COMPANY_DESC_1 = " " + PREFIX_COMPANY + " "; // whitespaces only not allowed in company
    public static final String INVALID_COMPANY_DESC_2 = " " + PREFIX_COMPANY + ""; // blanks not allowed in company
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "shopee!yahoo"; // missing '@' symbol
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS; // empty string not allowed for statuses
    public static final String INVALID_DATE_DESC_1 = " " + PREFIX_KEYDATE + "OA 2023-03-03"; // missing '@' symbol
    public static final String INVALID_DATE_DESC_2 = " " + PREFIX_KEYDATE + "OA@2023/03/03"; // dates must use '-'
    public static final String INVALID_DATE_DESC_3 = " " + PREFIX_KEYDATE + "@2023/03/03"; // keydate must have non-blank name
    public static final String INVALID_DATE_DESC_4 = " " + PREFIX_KEYDATE + "OA@2023/99/03"; // month must be valid
    public static final String INVALID_DATE_DESC_5 = " " + PREFIX_KEYDATE + "OA@2023/03/99"; // day must be valid

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditOpeningDescriptor DESC_GOOGLE;
    public static final EditCommand.EditOpeningDescriptor DESC_SHOPEE;

    static {
        DESC_GOOGLE = new EditOpeningDescriptorBuilder().withPosition(VALID_POSITION_GOOGLE)
                .withCompany(VALID_COMPANY_GOOGLE).withEmail(VALID_EMAIL_GOOGLE).withStatus(VALID_STATUS_GOOGLE)
                .withDates(VALID_KEYDATE_MARCH_INTERVIEW).build();
        DESC_SHOPEE = new EditOpeningDescriptorBuilder().withPosition(VALID_POSITION_SHOPEE)
                .withCompany(VALID_COMPANY_SHOPEE).withEmail(VALID_EMAIL_SHOPEE).withStatus(VALID_STATUS_SHOPEE)
                .withDates(VALID_KEYDATE_MARCH_INTERVIEW, VALID_KEYDATE_FEB_OA).build();
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
            System.out.println("Expected result: " + expectedCommandResult.getFeedbackToUser());
            System.out.println("Ultron result: " + result.getFeedbackToUser());
            System.out.println("Expected list: " + expectedModel.getFilteredOpeningList().toString());
            System.out.println("Ultron list: " + actualModel.getFilteredOpeningList().toString());
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
     * - the status book, filtered opening list and selected opening in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Ultron expectedUltron = new Ultron(actualModel.getUltron());
        List<Opening> expectedFilteredList = new ArrayList<>(actualModel.getFilteredOpeningList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedUltron, actualModel.getUltron());
        assertEquals(expectedFilteredList, actualModel.getFilteredOpeningList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the opening at the given {@code targetIndex} in the
     * {@code model}'s status book.
     */
    public static void showOpeningAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredOpeningList().size());

        Opening opening = model.getFilteredOpeningList().get(targetIndex.getZeroBased());
        final String[] splitPosition = opening.getPosition().fullPosition.split("\\s+");
        model.updateFilteredOpeningList(new CompanyOrPositionContainsKeywordsPredicate(Arrays.asList(splitPosition[0])));

        assertEquals(1, model.getFilteredOpeningList().size());
    }

}
