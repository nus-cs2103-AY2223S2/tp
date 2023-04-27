package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.QUANTITY_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_MEIJI;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.wife.testutil.TypicalIndex.INDEX_SECOND_FOOD;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.foodcommands.IncreaseCommand;
import seedu.wife.logic.commands.foodcommands.IncreaseCommand.IncreaseFoodDescriptor;
import seedu.wife.model.food.Quantity;
import seedu.wife.testutil.IncreaseFoodDescriptorBuilder;

public class IncreaseCommandParserTest {
    private static final String MESSAGE_INVALID_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncreaseCommand.MESSAGE_USAGE);


    private IncreaseCommandParser parser = new IncreaseCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_MEIJI, MESSAGE_INVALID_COMMAND);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_COMMAND);
    }
    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_INVALID_INDEX));

        // zero index
        assertParseFailure(parser, "0", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_INVALID_INDEX));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_COMMAND);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/100", MESSAGE_INVALID_COMMAND);
    }

    @Test
    public void parse_negativeQuantitySpecifiedUnfilteredList_failure() {
        String failureMessage = Quantity.MESSAGE_CONSTRAINTS;
        //quantity specified is 0
        assertParseFailure(parser, "1 q/0", failureMessage);
        //quantity specified is a negative number
        assertParseFailure(parser, "1 q/-2", failureMessage);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FOOD;
        String userInput = targetIndex.getOneBased() + QUANTITY_DESC_MEIJI;

        IncreaseFoodDescriptor descriptor = new IncreaseFoodDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_MEIJI)
                .build();
        IncreaseCommand expectedCommand = new IncreaseCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
