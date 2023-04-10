package seedu.address.logic.parser.task.note;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.note.DeleteNoteCommand;

/**
 * Test class for {@code DeleteNoteCommandParser}.
 */
public class DeleteNoteCommandTest {

    private DeleteNoteCommandParser parser = new DeleteNoteCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE));
    }
}
