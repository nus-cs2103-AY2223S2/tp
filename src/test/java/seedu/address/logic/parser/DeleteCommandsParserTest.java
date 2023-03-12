package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommands;
import seedu.address.logic.parser.exceptions.ParseException;




/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandsParserTest {
    private DeleteCommandsParser parser = new DeleteCommandsParser();

    @Test
    public void parse_validArgs_returnsDeleteCommands() throws ParseException {
        ArrayList<Index> temp = ParserUtil.parseindexs("1 2 3", " ");
        assertParseSuccess(parser, "1 2 3", new DeleteCommands(temp));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        ArrayList<String> temp = new ArrayList<>();
        temp.add("a");
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommands.MESSAGE_USAGE));
    }

}
