package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.comparator.ListingComparator;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_FIELD + "none",
                new SortCommand(ListingComparator.NONE));

        assertParseSuccess(parser, " " + PREFIX_FIELD + "Title",
                new SortCommand(ListingComparator.TITLE));

        assertParseSuccess(parser, " " + PREFIX_FIELD + " DesCriptION",
                new SortCommand(ListingComparator.DESCRIPTION));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_FIELD + "APPLICANTS ",
                new SortCommand(ListingComparator.APPLICANTS));

        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + " " + PREFIX_FIELD + "APPLICANTS " + PREFIX_FIELD + "nonE",
                new SortCommand(ListingComparator.NONE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " " + VALID_TITLE_DESC, expectedMessage);
        assertParseFailure(parser, " Title", expectedMessage);
    }

    @Test
    public void parse_invalidField_failure() {
        String expectedMessage = ListingComparator.MESSAGE_CONSTRAINTS;

        assertParseFailure(parser, " " + PREFIX_FIELD, expectedMessage);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " " + PREFIX_FIELD + "donkey", expectedMessage);
    }

}
