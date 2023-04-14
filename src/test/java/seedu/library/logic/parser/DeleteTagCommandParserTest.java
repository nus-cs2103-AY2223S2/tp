package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_PLANT;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.library.logic.commands.DeleteTagCommand;
import seedu.library.model.tag.Tag;

public class DeleteTagCommandParserTest {
    private DeleteTagCommandParser parser = new DeleteTagCommandParser();
    private Tag tag = new Tag(VALID_TAG_PLANT);

    @Test
    public void parse_validArgs_returnDeleteCommand() {
        assertParseSuccess(parser, VALID_TAG_PLANT, new DeleteTagCommand(tag));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_TAG_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
    }
}
