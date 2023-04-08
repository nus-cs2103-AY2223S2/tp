package fasttrack.logic.commands;

import static fasttrack.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static fasttrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static fasttrack.logic.parser.CliSyntax.PREFIX_NAME;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PHONE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_TAG;
import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.ExpenseTracker;
import fasttrack.model.Model;
import fasttrack.model.expense.Expense;
import fasttrack.ui.ScreenType;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualDataModel, CommandResult expectedCommandResult,
                                            Model expectedDataModel) {
        try {
            CommandResult result = command.execute(actualDataModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedDataModel, actualDataModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualDataModel, String expectedMessage,
                                            Model expectedDataModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, ScreenType.EXPENSE_SCREEN);
        assertCommandSuccess(command, actualDataModel, expectedCommandResult, expectedDataModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExpenseTracker expectedExpenseTracker = new ExpenseTracker(actualModel.getExpenseTracker());
        List<Expense> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExpenseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedExpenseTracker, actualModel.getExpenseTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredExpenseList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the expense at the given
     * {@code targetIndex} in the
     * {@code model}'s ExpenseTracker.
     */
    public static void showExpenseAtIndex(Model dataModel, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < dataModel.getFilteredExpenseList().size());

        Expense expense = dataModel.getFilteredExpenseList().get(targetIndex.getZeroBased());
        final String name = expense.getName();
        //TODO update predicates here when created
        //model.updateFilteredExpensesList(new NameContainsKeywordsPredicate(Arrays.asList(name)));

        assertEquals(1, dataModel.getFilteredExpenseList().size());
    }

}
