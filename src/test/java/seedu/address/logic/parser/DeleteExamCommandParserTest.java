package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exam.DeleteExamCommand;
import seedu.address.logic.parser.exam.DeleteExamCommandParser;

class DeleteExamCommandParserTest {
    private DeleteExamCommandParser parser = new DeleteExamCommandParser();
    @Test
    public void parse_missingArs_failure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        DeleteExamCommand.MESSAGE_USAGE);
        //Missing index
        String input1 = " name/Amy";
        assertParseFailure(parser, input1, expected);

        //Missing name
        String input2 = " index/1";
        assertParseFailure(parser, input2, expected);
    }

    @Test
    public void parse_indexNonPositive_failure() {
        String expected = MESSAGE_INVALID_INDEX;
        //Negative index
        String input1 = " name/Amy"
                + " index/-2";
        assertParseFailure(parser, input1, expected);

        //Zero index
        String input2 = " name/Amy"
                + " index/0";
        assertParseFailure(parser, input2, expected);
    }
}
