package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_CS1101S;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.TagCommand;
import seedu.modtrek.model.tag.Tag;

class TagCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_missingparts_failure() {
        // no module specified
        assertParseFailure(parser, PREFIX_TAG + "Computer Science Foundation", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, VALID_CODE_CS1101S + "Computer Science Foundation", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, VALID_CODE_CS1101S + "/i " + "Computer Science Foundation",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_CODE_CS1101S + " include " + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag
    }

    @Test
    public void parse_success() {
        assertParseSuccess(parser, VALID_CODE_CS1101S + " include ", new TagCommand(CS1101S.getCode(),
                true, new HashSet<>()));
    }

}
