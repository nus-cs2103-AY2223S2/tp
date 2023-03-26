package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.util.TypicalPersons.INVALID_TAG_DESC;
import static seedu.address.model.util.TypicalPersons.TAG_DESC_FRIEND;
import static seedu.address.model.util.TypicalPersons.TAG_DESC_LOGISTICS;
import static seedu.address.model.util.TypicalPersons.VALID_TAG_LOGISTICS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.tag.Tag;


public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_invalidTagValue_failure() {
        //empty tag
        assertParseFailure(parser, " " + PREFIX_TAG + " ", Tag.MESSAGE_CONSTRAINTS);
        //non alpha-numeric tag
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingTagPrefix_failure() {
        assertParseFailure(parser, " " + VALID_TAG_LOGISTICS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOneTagValue_failure() {
        assertParseFailure(parser, TAG_DESC_LOGISTICS + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validTagValue_success() {
        FilterCommand expectedCommand = new FilterCommand(new Tag(VALID_TAG_LOGISTICS));
        //same tag
        assertParseSuccess(parser, TAG_DESC_LOGISTICS, expectedCommand);
        //capitalised tag
        assertParseSuccess(parser, " " + PREFIX_TAG + "LOGISTICS", expectedCommand);
        //tag with trailing whitespaces
        assertParseSuccess(parser, " " + PREFIX_TAG + " logistics ", expectedCommand);
    }
}
