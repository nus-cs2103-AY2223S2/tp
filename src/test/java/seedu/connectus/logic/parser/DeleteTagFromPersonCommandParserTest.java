package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.connectus.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.DeleteTagFromPersonCommand;

public class DeleteTagFromPersonCommandParserTest {


    private final DeleteTagFromPersonCommandParser parser = new DeleteTagFromPersonCommandParser();

    @Test
    public void parse_validArgs_returnsCommand() {
        assertParseSuccess(parser, "1 r/1 mod/1", new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON,
            Index.fromZeroBased(0), null, null, Index.fromZeroBased(0)
        ));
    }

    @Test
    public void parse_unrelatedArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noTagSpecified_throwsParseException() {
        assertParseFailure(parser, "1 t/",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 t/ ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 t/\t",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noModuleSpecified_throwsParseException() {
        assertParseFailure(parser, "1 mod/",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 mod/ ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 mod/\t",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nothingSpecified_throwsParseException() {
        assertParseFailure(parser, "1 mod/ t/",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 mod/  t/ ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 mod/\t t/\t",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
    }
}
