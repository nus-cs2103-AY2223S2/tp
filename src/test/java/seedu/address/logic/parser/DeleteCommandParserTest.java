package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteByNameCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteMultipleIndexCommand;
import seedu.address.logic.commands.DeleteSingleIndexCommand;
import seedu.address.model.person.NameContainsAllKeywordsPredicate;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteSingleIndexCommand() {
        assertParseSuccess(parser, "1", new DeleteSingleIndexCommand(INDEX_FIRST_PERSON));
    }
    @Test
    public void parse_validArgs_returnsDeleteMultipleIndexCommand() {
        assertParseSuccess(parser, "1,2,3", new DeleteMultipleIndexCommand(
                List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON)));
    }
    @Test
    public void parse_validArgs_returnsDeleteByNameCommand() {
        assertParseSuccess(parser, "John Doe",
                new DeleteByNameCommand(new NameContainsAllKeywordsPredicate(List.of("John", "Doe"))));
    }
    @Test
    public void parse_negativeIndex_returnsDeleteByNameCommand() {
        assertParseSuccess(parser, "-1",
                new DeleteByNameCommand(new NameContainsAllKeywordsPredicate(List.of("-1"))));
    }
    @Test
    public void parse_invalidArgs_returnsMessageUsage() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

}
