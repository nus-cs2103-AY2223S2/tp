package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.library.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.library.logic.commands.CommandTestUtil.TAG_DESC_NOVEL;
import static seedu.library.logic.commands.CommandTestUtil.TAG_DESC_PLANT;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_PLANT;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.library.logic.commands.AddTagCommand;
import seedu.library.model.tag.Tag;
import seedu.library.testutil.TagsBuilder;

public class AddTagCommandParserTest {
    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_tagsPresent_success() {
        Set<Tag> expectedTags = new TagsBuilder().build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_DESC_PLANT + TAG_DESC_NOVEL,
                new AddTagCommand(expectedTags));
    }

    @Test
    public void parse_wrongFormat_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE);

        // no prefix for tags
        assertParseFailure(parser, VALID_TAG_PLANT, expectedMessage);

        // no tags
        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }
}
