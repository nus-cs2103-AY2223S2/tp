package seedu.wife.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_UNIT;
import static seedu.wife.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.logic.commands.foodcommands.EditCommand;
import seedu.wife.model.Model;
import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.NameContainsKeywordsPredicate;
import seedu.wife.testutil.EditFoodDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_MEIJI = "MEIJI";
    public static final String VALID_UNIT_MEIJI = "Carton";
    public static final String VALID_QUANTITY_MEIJI = "2";
    public static final String VALID_EXPIRY_DATE_MEIJI = "13-11-2024";
    public static final String VALID_TAG_DAIRY = "DAIRY";
    public static final String VALID_NAME_CHOCOLATE = "Chocolate";
    public static final String VALID_UNIT_CHOCOLATE = "Bar";
    public static final String VALID_QUANTITY_CHOCOLATE = "2";
    public static final String VALID_EXPIRY_DATE_CHOCOLATE = "13-11-2024";
    public static final String VALID_TAG_CHOCOLATE = "CHOCOLATE";
    public static final String NAME_DESC_MEIJI = " " + PREFIX_NAME + VALID_NAME_MEIJI;
    public static final String UNIT_DESC_MEIJI = " " + PREFIX_UNIT + VALID_UNIT_MEIJI;
    public static final String QUANTITY_DESC_MEIJI = " " + PREFIX_QUANTITY + VALID_QUANTITY_MEIJI;
    public static final String EXPIRY_DATE_DESC_MEIJI = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_MEIJI;
    public static final String TAG_DESC_MEIJI = " " + PREFIX_TAG + VALID_TAG_DAIRY;
    public static final String NAME_DESC_CHOCOLATE = " " + PREFIX_NAME + VALID_NAME_CHOCOLATE;
    public static final String UNIT_DESC_CHOCOLATE = " " + PREFIX_UNIT + VALID_UNIT_CHOCOLATE;
    public static final String QUANTITY_DESC_CHOCOLATE = " " + PREFIX_QUANTITY + VALID_QUANTITY_CHOCOLATE;
    public static final String EXPIRY_DATE_DESC_CHOCOLATE = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_CHOCOLATE;
    public static final String TAG_DESC_CHOCOLATE = " " + PREFIX_TAG + VALID_TAG_CHOCOLATE;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Meiji&"; // '&' not allowed in names
    public static final String INVALID_UNIT_DESC = " " + PREFIX_UNIT + "C@rton"; // '@' not allowed in phones
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "-1"; // value < 0
    public static final String INVALID_EXPIRY_DATE_DESC = " " + PREFIX_EXPIRY_DATE + "13112024"; // date not formatted
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "dairy*"; // '*' not allowed in tags

    // test util for Tag\
    public static final String INVALID_TAG_NAME_EMPTY_NAME = "";
    public static final String INVALID_TAG_NAME_CONTAIN_SYMBOL = "Cre@m";
    public static final String INVALID_TAG_NAME_TOO_LENGTHY = "VegetablesHealthy";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFoodDescriptor DESC_MEIJI;
    public static final EditCommand.EditFoodDescriptor DESC_CHOCOLATE;

    static {
        DESC_MEIJI = new EditFoodDescriptorBuilder()
                .withName(VALID_NAME_MEIJI)
                .withUnit(VALID_UNIT_MEIJI)
                .withQuantity(VALID_QUANTITY_MEIJI)
                .withExpiryDate(VALID_EXPIRY_DATE_MEIJI)
                .withTags(VALID_TAG_DAIRY)
                .build();
        DESC_CHOCOLATE = new EditFoodDescriptorBuilder()
                .withName(VALID_NAME_CHOCOLATE)
                .withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_CHOCOLATE)
                .withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE)
                .withTags(VALID_TAG_CHOCOLATE)
                .build();
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
     * - the address book, filtered Food list and selected Food in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Wife expectedWife = new Wife(actualModel.getWife());
        List<Food> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFoodList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedWife, actualModel.getWife());
        assertEquals(expectedFilteredList, actualModel.getFilteredFoodList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the Food at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showFoodAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFoodList().size());

        Food food = model.getFilteredFoodList().get(targetIndex.getZeroBased());
        final String[] splitName = food.getName().toString().split("\\s+");
        model.updateFilteredFoodList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFoodList().size());
    }

}
