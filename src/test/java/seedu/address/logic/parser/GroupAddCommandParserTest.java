package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GroupAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model)
 * and unit tests for GroupAddCommandParser.
 */
public class GroupAddCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GroupAddCommand.MESSAGE_USAGE);
    private static final String TAG_FIELD_NOT_PRESENT =
            String.format(GroupAddCommandParser.MESSAGE_TAG_DOES_NOT_EXIST_PARSE_FAILURE,
                    GroupAddCommand.MESSAGE_USAGE);
    private GroupAddCommandParser parser = new GroupAddCommandParser();

    @Test
    public void parse_missingParts_failure() {
        String test1 = "t/Hall";
        String test2 = "1 ";
        String test3 = " ";
        // no index specified
        assertParseFailure(parser, test1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, test2, TAG_FIELD_NOT_PRESENT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_groupAddSingleGroup_success() throws ParseException {
        Index targetIndex = INDEX_FIRST_PERSON;
        String input = targetIndex.getOneBased() + " t/Hall";
        // One group
        Set<Tag> expectedTagList = new HashSet<>();
        expectedTagList.add(new Tag("Hall"));
        GroupAddCommand expectedCommand = new GroupAddCommand(targetIndex, expectedTagList);
        assertParseSuccess(parser, input, expectedCommand);
    }
}
