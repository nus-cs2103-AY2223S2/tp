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
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 t/1 mod/1", new DeleteTagFromPersonCommand(INDEX_FIRST_PERSON,
            Index.fromZeroBased(0), Index.fromZeroBased(0)
        ));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagFromPersonCommand.MESSAGE_USAGE));
    }
}
