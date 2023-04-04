package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RemarkCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the RemarkCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RemarkCommandParserTest {

    private RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_validIndex_returnsRemarkCommand() {
        assertParseSuccess(parser, "1", new RemarkCommand(INDEX_FIRST_PERSON, null));
        assertParseSuccess(parser, "2", new RemarkCommand(INDEX_SECOND_PERSON, null));
    }

    @Test
    public void parse_validIndexAndRemark_returnsRemarkCommand() {
        assertParseSuccess(parser, "1 " + VALID_REMARK,
                new RemarkCommand(INDEX_FIRST_PERSON, new Remark("Hello")));
        assertParseSuccess(parser, "1 \0", new RemarkCommand(INDEX_FIRST_PERSON, new Remark("\0")));
    }

    @Test
    public void parse_validRemarkLeadingWhitespace_stripsWhitespace() {
        assertParseSuccess(parser, "1 \n\n\n\n\n\t\t\t\t\t     \t\t\t\n" + VALID_REMARK,
                new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK)));
    }

    @Test
    public void parse_validRemarkTrailingWhitespace_stripsWhitespace() {
        //assertParseSuccess(parser, "1 " + VALID_REMARK + "\n\n\n\n\n\t\t\t   \t\t  \n",
        //        new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK)));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "0", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "a", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "-1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
