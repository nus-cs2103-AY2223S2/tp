package seedu.careflow.logic.parser.drugparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.careflow.logic.commands.drugcommands.DeleteCommand.MESSAGE_USAGE;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.careflow.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.careflow.commons.core.index.Index;
import seedu.careflow.logic.commands.drugcommands.DeleteCommand;
import seedu.careflow.model.drug.TradeName;

class DeleteCommandParserTest {

    private final DeleteCommandParser deleteCommandParser = new DeleteCommandParser();

    @Test
    public void parse_validIndex_returnsDeleteCommand() {
        Index index = Index.fromOneBased(1);

        assertParseSuccess(deleteCommandParser, " -i 1",
                new DeleteCommand(index));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertParseFailure(deleteCommandParser, " -i -1", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidNonNumericalIndex_throwsParseException() {
        assertParseFailure(deleteCommandParser, " -i a", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(deleteCommandParser, "-i ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_validTradeName_returnsDeleteCommand() {
        TradeName tradeName = new TradeName("Panadol");

        assertParseSuccess(deleteCommandParser, " -tn Panadol",
                new DeleteCommand(tradeName));
    }

    @Test
    public void parse_missingTradeName_failure() {
        assertParseFailure(deleteCommandParser, "-tn ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
