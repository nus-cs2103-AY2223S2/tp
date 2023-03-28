package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.DATE_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.EMPTY_INDEXLIST;
import static seedu.internship.logic.commands.CommandTestUtil.EMPTY_PREDICATE;
import static seedu.internship.logic.commands.CommandTestUtil.NON_EMPTY_INDEXLIST;
import static seedu.internship.logic.commands.CommandTestUtil.NON_EMPTY_PREDICATE;
import static seedu.internship.logic.commands.CommandTestUtil.ROLE_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.internship.logic.commands.CommandTestUtil.TAG_DESC_FRONT;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside from the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_indexAndPredicateNonEmpty_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 " + COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + TAG_DESC_FRONT, new DeleteCommand(NON_EMPTY_INDEXLIST, NON_EMPTY_PREDICATE));
    }

    @Test
    public void parse_indexEmptyAndPredicateNonEmpty_returnsDeleteCommand() {
        assertParseSuccess(parser, COMPANY_NAME_DESC_GOOGLE + ROLE_DESC_GOOGLE + STATUS_DESC_GOOGLE
                + DATE_DESC_GOOGLE + TAG_DESC_FRONT, new DeleteCommand(EMPTY_INDEXLIST, NON_EMPTY_PREDICATE));
    }

    @Test
    public void parse_indexNonEmptyAndPredicateEmpty_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(NON_EMPTY_INDEXLIST, EMPTY_PREDICATE));
    }

    @Test
    public void parse_indexEmptyAndPredicateEmpty_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_MISSING_ARGUMENTS, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }
}
