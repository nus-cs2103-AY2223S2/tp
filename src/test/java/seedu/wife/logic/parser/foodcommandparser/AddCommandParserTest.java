package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.NAME_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.QUANTITY_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.UNIT_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_CHOCOLATE;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.foodcommands.AddCommand;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    /*
    @Test
    public void parse_allFieldsPresent_success() {
        Food expectedFood = new FoodBuilder(MEIJI).withTags(VALID_TAG_DAIRY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MEIJI + UNIT_DESC_MEIJI
                + QUANTITY_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI + TAG_DESC_MEIJI, new AddCommand(expectedFood));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_MEIJI + NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE
                + QUANTITY_DESC_CHOCOLATE + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE,
                new AddCommand(expectedFood));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_CHOCOLATE + NAME_DESC_MEIJI + UNIT_DESC_CHOCOLATE
                + QUANTITY_DESC_CHOCOLATE + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_MEIJI,
                new AddCommand(expectedFood));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_MEIJI
                + QUANTITY_DESC_CHOCOLATE + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_MEIJI, new AddCommand(expectedFood));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                + EXPIRY_DATE_DESC_MEIJI + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_MEIJI, new AddCommand(expectedFood));

        // multiple tags - all accepted
        Food expectedFoodMultipleTags = new FoodBuilder(CHOCOLATE).withTags(VALID_TAG_DAIRY, VALID_TAG_CHOCOLATE)
                .build();
        assertParseSuccess(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE + TAG_DESC_MEIJI,
                new AddCommand(expectedFoodMultipleTags));
    }
    */

    /*
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Food expectedFood = new FoodBuilder(MEIJI).withTags().build();
        assertParseSuccess(parser, NAME_DESC_MEIJI + UNIT_DESC_MEIJI + QUANTITY_DESC_MEIJI
                        + EXPIRY_DATE_DESC_CHOCOLATE, new AddCommand(expectedFood));
    }
    */

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                        + EXPIRY_DATE_DESC_CHOCOLATE, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + VALID_UNIT_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                        + EXPIRY_DATE_DESC_CHOCOLATE, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + VALID_QUANTITY_CHOCOLATE
                        + EXPIRY_DATE_DESC_CHOCOLATE, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                        + VALID_EXPIRY_DATE_CHOCOLATE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_CHOCOLATE + VALID_UNIT_CHOCOLATE + VALID_QUANTITY_CHOCOLATE
                        + VALID_EXPIRY_DATE_CHOCOLATE, expectedMessage);
    }

    /*
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + INVALID_UNIT_DESC + QUANTITY_DESC_CHOCOLATE
                + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + INVALID_QUANTITY_DESC
                + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                + INVALID_EXPIRY_DATE_DESC + TAG_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                + EXPIRY_DATE_DESC_CHOCOLATE + INVALID_TAG_DESC + VALID_TAG_CHOCOLATE, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE
                        + INVALID_EXPIRY_DATE_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE
                        + QUANTITY_DESC_CHOCOLATE + EXPIRY_DATE_DESC_CHOCOLATE + TAG_DESC_MEIJI + TAG_DESC_CHOCOLATE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
    */
}
