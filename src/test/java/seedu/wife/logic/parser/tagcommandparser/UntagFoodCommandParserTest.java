package seedu.wife.logic.parser.tagcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.wife.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.NAME_DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.TAG_DESCRIPTION_DAIRY;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_FIRST_INDEX_ID;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.wife.testutil.TypicalIndex.INDEX_FIRST_FOOD;
import static seedu.wife.testutil.TypicalTag.DAIRY_TAG;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.tagcommands.UntagFoodCommand;
import seedu.wife.model.tag.Tag;
import seedu.wife.testutil.TagBuilder;

public class UntagFoodCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UntagFoodCommand.MESSAGE_USAGE);
    private final UntagFoodCommandParser parser = new UntagFoodCommandParser();

    @Test
    public void parse_indexThenTag_success() {
        Tag dairyTag = new TagBuilder(DAIRY_TAG).build();
        String dairyTagName = dairyTag.getTagName();
        UntagFoodCommand expectedCommand = new UntagFoodCommand(dairyTagName, INDEX_FIRST_FOOD);

        String userInput = String.format("%s %s", VALID_FIRST_INDEX_ID, TAG_DESCRIPTION_DAIRY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidExtraFlagAfterIndex_failure() {
        String userInput = String.format("%s %s %s", VALID_FIRST_INDEX_ID, EXPIRY_DATE_DESC_MEIJI,
                TAG_DESCRIPTION_DAIRY);

        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingArguments_failure() {
        // no tag specified
        assertParseFailure(parser, VALID_FIRST_INDEX_ID, MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, NAME_DESC_MEIJI, MESSAGE_INVALID_FORMAT);

        // no tag and no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // Number preamble
        String invalidIndexMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1 " + TAG_DESCRIPTION_DAIRY, invalidIndexMessage);

        // invalid arguments (String) being parsed as preamble
        assertParseFailure(parser, "some random string  " + VALID_TAG_DAIRY
                + VALID_FIRST_INDEX_ID, MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "qty/1 " + TAG_DESCRIPTION_DAIRY
                + VALID_FIRST_INDEX_ID, MESSAGE_INVALID_FORMAT);
    }
}
