package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
// import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTxnByPersonCommand;
// import seedu.address.model.person.FindContainsAnythingPredicate;

public class FindTxnByPersonCommandParserTest {

    private FindTxnByPersonCommandParser parser = new FindTxnByPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindTxnByPersonCommand.MESSAGE_USAGE));
    }

    // @Test
    // public void parse_validArgs_returnsFindCommand() {
    // // no leading and trailing whitespaces
    // FindAllCommand expectedFindCommand =
    // new FindAllCommand(new FindContainsAnythingPredicate(Arrays.asList("Alice",
    // "Bob")));
    // assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

    // // multiple whitespaces between keywords
    // assertParseSuccess(parser, " \n Alice \n \t Bob \t", expectedFindCommand);
    // }

}
