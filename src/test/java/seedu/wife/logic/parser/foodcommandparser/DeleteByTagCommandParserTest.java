package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_CHOCOLATE;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.foodcommands.DeleteByTagCommand;
import seedu.wife.model.tag.Tag;


public class DeleteByTagCommandParserTest {
    private DeleteByTagCommandParser parser = new DeleteByTagCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteByTagCommand() {
        assertParseSuccess(
            parser,
            " " + PREFIX_NAME + VALID_TAG_CHOCOLATE,
            new DeleteByTagCommand(new Tag(VALID_TAG_CHOCOLATE))
        );
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
            parser,
            "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteByTagCommand.MESSAGE_USAGE)
        );
    }
}
