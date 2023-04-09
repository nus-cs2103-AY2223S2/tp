package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteImageCommand;

public class DeleteImageCommandParserTest {
    private DeleteImageCommandParser parser = new DeleteImageCommandParser();

    @Test
    public void parse_letterIndex_exceptionThrown() {
        assertParseFailure(parser, "delete-image abc", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_numericalIndex_success() {
        assertParseSuccess(parser, "1",
                new DeleteImageCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_negativeIndex_exceptionThrown() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_INDEX);
    }
}
