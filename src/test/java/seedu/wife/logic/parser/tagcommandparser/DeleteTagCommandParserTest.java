package seedu.wife.logic.parser.tagcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.tagcommands.DeleteTagCommand;
import seedu.wife.model.tag.Tag;

/**
 * A class to test the DeleteTagCommand.
 */
public class DeleteTagCommandParserTest {

    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTagCommand() {
        assertParseSuccess(parser, " " + PREFIX_NAME + VALID_TAG_DAIRY, new DeleteTagCommand(new Tag(VALID_TAG_DAIRY)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
            parser,
            "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE)
        );
    }
}
