package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.wife.logic.commands.CommandTestUtil.INVALID_UNIT_DESC;
import static seedu.wife.logic.commands.CommandTestUtil.NAME_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.QUANTITY_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.QUANTITY_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.UNIT_DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.UNIT_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_MEIJI;
import static seedu.wife.logic.commands.foodcommands.EditCommand.MESSAGE_NOT_EDITED;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.wife.testutil.TypicalIndex.INDEX_FIRST_FOOD;
import static seedu.wife.testutil.TypicalIndex.INDEX_SECOND_FOOD;
import static seedu.wife.testutil.TypicalIndex.INDEX_THIRD_FOOD;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.foodcommands.EditCommand;
import seedu.wife.logic.commands.foodcommands.EditCommand.EditFoodDescriptor;
import seedu.wife.model.food.Name;
import seedu.wife.model.food.Quantity;
import seedu.wife.model.food.Unit;
import seedu.wife.model.food.foodvalidator.ExpiryDateValidator;
import seedu.wife.testutil.EditFoodDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_MEIJI, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", String.format(MESSAGE_NOT_EDITED, EditCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_MEIJI, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_INVALID_INDEX));

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_MEIJI, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_INVALID_INDEX));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid unit
        assertParseFailure(parser, "1" + INVALID_UNIT_DESC, Unit.MESSAGE_CONSTRAINTS);
        // invalid quantity
        assertParseFailure(parser, "1" + INVALID_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS);
        // invalid expiry date
        assertParseFailure(parser, "1" + INVALID_EXPIRY_DATE_DESC, ExpiryDateValidator.MESSAGE_FORMAT_CONSTRAINTS);

        // invalid unit followed by valid quantity
        assertParseFailure(parser, "1" + INVALID_UNIT_DESC + QUANTITY_DESC_MEIJI, Unit.MESSAGE_CONSTRAINTS);

        // valid unit followed by invalid unit. The test case for invalid unit followed by valid unit
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + UNIT_DESC_CHOCOLATE + INVALID_UNIT_DESC, Unit.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_QUANTITY_DESC
                        + VALID_EXPIRY_DATE_MEIJI + VALID_UNIT_MEIJI, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FOOD;
        String userInput = targetIndex.getOneBased() + UNIT_DESC_CHOCOLATE
                + QUANTITY_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI + NAME_DESC_MEIJI;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_MEIJI)
                .withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_MEIJI)
                .withExpiryDate(VALID_EXPIRY_DATE_MEIJI)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + UNIT_DESC_CHOCOLATE + QUANTITY_DESC_MEIJI;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_MEIJI).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_FOOD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_MEIJI;
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_MEIJI).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // unit
        userInput = targetIndex.getOneBased() + UNIT_DESC_MEIJI;
        descriptor = new EditFoodDescriptorBuilder().withUnit(VALID_UNIT_MEIJI).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + QUANTITY_DESC_MEIJI;
        descriptor = new EditFoodDescriptorBuilder().withQuantity(VALID_QUANTITY_MEIJI).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expiry date
        userInput = targetIndex.getOneBased() + EXPIRY_DATE_DESC_MEIJI;
        descriptor = new EditFoodDescriptorBuilder().withExpiryDate(VALID_EXPIRY_DATE_MEIJI).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + UNIT_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI + QUANTITY_DESC_MEIJI
                + UNIT_DESC_MEIJI + EXPIRY_DATE_DESC_MEIJI + QUANTITY_DESC_MEIJI
                + UNIT_DESC_CHOCOLATE + EXPIRY_DATE_DESC_CHOCOLATE + QUANTITY_DESC_CHOCOLATE;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_CHOCOLATE)
                .withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE)
                .build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + INVALID_UNIT_DESC + UNIT_DESC_CHOCOLATE;
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withUnit(VALID_UNIT_CHOCOLATE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + QUANTITY_DESC_CHOCOLATE + INVALID_UNIT_DESC
                + EXPIRY_DATE_DESC_CHOCOLATE + UNIT_DESC_CHOCOLATE;
        descriptor = new EditFoodDescriptorBuilder()
                .withUnit(VALID_UNIT_CHOCOLATE)
                .withQuantity(VALID_QUANTITY_CHOCOLATE)
                .withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
