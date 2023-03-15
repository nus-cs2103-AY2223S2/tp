package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.NAME_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_MEIJI;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.wife.testutil.TypicalIndexes.INDEX_THIRD_FOOD;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.foodcommands.EditCommand;
import seedu.wife.logic.commands.foodcommands.EditCommand.EditFoodDescriptor;
import seedu.wife.testutil.EditFoodDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_MEIJI, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_MEIJI, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_MEIJI, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    /*
    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_UNIT_DESC, Unit.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_EXPIRY_DATE_DESC, ExpiryDate.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_UNIT_DESC + QUANTITY_DESC_MEIJI, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + UNIT_DESC_CHOCOLATE + INVALID_UNIT_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_MEIJI + TAG_DESC_CHOCOLATE + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_MEIJI + TAG_EMPTY + TAG_DESC_CHOCOLATE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_MEIJI + TAG_DESC_CHOCOLATE, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_QUANTITY_DESC
                        + VALID_EXPIRY_DATE_MEIJI + VALID_UNIT_MEIJI, Name.MESSAGE_CONSTRAINTS);
    }

    /*
    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FOOD;
        String userInput = targetIndex.getOneBased() + UNIT_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE
                + QUANTITY_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI + NAME_DESC_MEIJI + TAG_DESC_MEIJI;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_MEIJI)
                .withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_MEIJI)
                .withExpiryDate(VALID_EXPIRY_DATE_MEIJI)
                .withTags(VALID_TAG_DAIRY, VALID_TAG_CHOCOLATE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    */

    /*
    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_MEIJI;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_MEIJI).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    */

    /*
    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_FOOD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_MEIJI;
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_MEIJI).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + UNIT_DESC_MEIJI;
        descriptor = new EditFoodDescriptorBuilder().withUnit(VALID_UNIT_MEIJI).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + QUANTITY_DESC_MEIJI;
        descriptor = new EditFoodDescriptorBuilder().withQuantity(VALID_QUANTITY_MEIJI).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + EXPIRY_DATE_DESC_MEIJI;
        descriptor = new EditFoodDescriptorBuilder().withExpiryDate(VALID_EXPIRY_DATE_MEIJI).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_MEIJI;
        descriptor = new EditFoodDescriptorBuilder().withTags(VALID_TAG_CHOCOLATE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    */

    /*
    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + UNIT_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI + QUANTITY_DESC_MEIJI
                + TAG_DESC_MEIJI + UNIT_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI + QUANTITY_DESC_MEIJI + TAG_DESC_MEIJI
                + UNIT_DESC_CHOCOLATE + EXPIRY_DATE_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE + TAG_DESC_CHOCOLATE;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_CHOCOLATE).withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE)
                .withTags(VALID_TAG_CHOCOLATE, VALID_TAG_DAIRY)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    */

    /*
    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + INVALID_UNIT_DESC + UNIT_DESC_CHOCOLATE;
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withUnit(VALID_UNIT_CHOCOLATE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + QUANTITY_DESC_CHOCOLATE + INVALID_UNIT_DESC + EXPIRY_DATE_DESC_CHOCOLATE
                + UNIT_DESC_CHOCOLATE;
        descriptor = new EditFoodDescriptorBuilder().withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_CHOCOLATE)
                .withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    */

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_FOOD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
