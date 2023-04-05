package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.TagCommand;
import seedu.modtrek.model.tag.Tag;

class TagCommandParserTest {

    private final TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no module specified
        assertParseFailure(parser, PREFIX_TAG + "Computer Science Foundation",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, VALID_CODE_CS1101S + "Computer Science Foundation",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, VALID_CODE_CS1101S + "/i " + "Computer Science Foundation",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_CODE_CS1101S + " include" + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_CODE_CS1101S + " remove" + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, VALID_CODE_CS1101S + " include",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_MISSING_PREFIX));
        assertParseFailure(parser, VALID_CODE_CS1101S + " remove",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_MISSING_PREFIX));
    }

    @Test
    public void parse_missingTagDetails_failure() {
        assertParseFailure(parser, VALID_CODE_CS1101S + " include " + PREFIX_TAG,
                Tag.MESSAGE_MISSING_DETAIL);
        assertParseFailure(parser, VALID_CODE_CS1101S + " remove " + PREFIX_TAG,
                Tag.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_missingIncludeRemove_failure() {
        assertParseFailure(parser, VALID_CODE_CS1101S,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, VALID_CODE_CS1101S,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_success() {
        HashSet<Tag> tags = new HashSet<>();
        Tag newTag = new Tag("Computer Science Foundation");
        tags.add(newTag);
        assertParseSuccess(parser, VALID_CODE_CS1101S + " include " + PREFIX_TAG + " " + VALID_TAG_CS1101S,
                new TagCommand(CS1101S.getCode(), true, tags));
        assertParseSuccess(parser, VALID_CODE_CS1101S + " remove " + PREFIX_TAG + " " + VALID_TAG_CS1101S,
                new TagCommand(CS1101S.getCode(), false, tags));
    }

}
