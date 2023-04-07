package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_QUANTITY_STRING;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_UNIT_DESC;
import static seedu.wife.logic.commands.CommandTestUtil.NAME_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.NAME_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.wife.logic.commands.CommandTestUtil.QUANTITY_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.QUANTITY_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.TAG_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.UNIT_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.UNIT_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_CHOCOLATE;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.wife.testutil.TypicalFood.MEIJI;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.foodcommands.AddCommand;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Name;
import seedu.wife.model.food.Quantity;
import seedu.wife.model.food.Unit;
import seedu.wife.model.food.foodvalidator.ExpiryDateValidator;
import seedu.wife.testutil.FoodBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Food expectedFood = new FoodBuilder(MEIJI).build();

        // whitespace only preamble
        assertParseSuccess(parser, NAME_DESC_MEIJI + UNIT_DESC_MEIJI
                + QUANTITY_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI, new AddCommand(expectedFood));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_CHOCOLATE + NAME_DESC_MEIJI + UNIT_DESC_MEIJI
                + QUANTITY_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI,
                new AddCommand(expectedFood));

        // multiple units - last unit accepted
        assertParseSuccess(parser, NAME_DESC_MEIJI + UNIT_DESC_CHOCOLATE + UNIT_DESC_MEIJI
                + QUANTITY_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI,
                new AddCommand(expectedFood));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser, NAME_DESC_MEIJI + UNIT_DESC_MEIJI + QUANTITY_DESC_CHOCOLATE
                + QUANTITY_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI, new AddCommand(expectedFood));

        // multiple expiry dates - last expiry date accepted
        assertParseSuccess(parser, NAME_DESC_MEIJI + UNIT_DESC_MEIJI + QUANTITY_DESC_MEIJI
                + EXPIRY_DATE_DESC_CHOCOLATE + EXPIRY_DATE_DESC_MEIJI, new AddCommand(expectedFood));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                        + EXPIRY_DATE_DESC_CHOCOLATE, expectedMessage);

        // missing unit prefix
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + VALID_UNIT_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                        + EXPIRY_DATE_DESC_CHOCOLATE, expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + VALID_QUANTITY_CHOCOLATE
                        + EXPIRY_DATE_DESC_CHOCOLATE, expectedMessage);

        // missing expiry date prefix
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                        + VALID_EXPIRY_DATE_CHOCOLATE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_CHOCOLATE + VALID_UNIT_CHOCOLATE + VALID_QUANTITY_CHOCOLATE
                        + VALID_EXPIRY_DATE_CHOCOLATE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE, Name.MESSAGE_CONSTRAINTS);

        // invalid unit
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + INVALID_UNIT_DESC + QUANTITY_DESC_CHOCOLATE
                + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE, Unit.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + INVALID_QUANTITY_DESC
                + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE, Quantity.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + INVALID_QUANTITY_STRING
                + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE,
                Quantity.MESSAGE_CHAR_CONSTRAINTS);

        // invalid expiry date
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                + INVALID_EXPIRY_DATE_DESC + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE,
                ExpiryDateValidator.MESSAGE_FORMAT_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                        + INVALID_EXPIRY_DATE_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE
                        + QUANTITY_DESC_CHOCOLATE + EXPIRY_DATE_DESC_CHOCOLATE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
