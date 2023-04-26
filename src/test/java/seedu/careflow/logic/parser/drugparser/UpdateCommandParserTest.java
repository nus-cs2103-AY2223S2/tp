package seedu.careflow.logic.parser.drugparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.drugcommands.UpdateCommand;
import seedu.careflow.model.drug.TradeName;

class UpdateCommandParserTest {

    private final UpdateCommandParser updateCommandParser = new UpdateCommandParser();

    @Test
    public void parse_validfields_success() {
        TradeName tradeName = new TradeName("Panadol");
        int val = 10;
        boolean add = true;

        assertParseSuccess(updateCommandParser, " Panadol -by +10",
                new UpdateCommand(tradeName, val, add));
    }

    @Test
    public void parse_nonNumericalValue_failure() {
        assertParseFailure(updateCommandParser, " Panadol -by +a",
                UpdateCommandParser.INVALID_VALUE_MESSAGE);
    }

    @Test
    public void parse_invalidTradeName_failure() {
        assertParseFailure(updateCommandParser, " $$$ -by +10",
                "Trade names should only contain alphanumeric characters and spaces, "
                        + "it should not be blank and less than 50 characters");
    }

    @Test
    public void parse_missingSymbol_failure() {
        assertParseFailure(updateCommandParser, " Panadol -by 50",
                UpdateCommandParser.INVALID_UNKNOWN_SYMBOL_MESSAGE);
    }

    @Test
    public void parse_missingValue_failure() {
        assertParseFailure(updateCommandParser, " Panadol",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingTradeName_failure() {
        assertParseFailure(updateCommandParser, "-by +10",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
    }
}
