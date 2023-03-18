package seedu.socket.logic.parser;

import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.FindCommand;
import seedu.socket.model.person.predicate.FindCommandPersonPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_returnsFindCommand() {
        List<String> emptyKeywords = Collections.emptyList();
        FindCommand command = new FindCommand(new FindCommandPersonPredicate(
                emptyKeywords,
                emptyKeywords,
                emptyKeywords,
                emptyKeywords,
                emptyKeywords,
                emptyKeywords,
                emptyKeywords));
        assertParseSuccess(parser, "", command);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> emptyKeywords = Collections.emptyList();
        List<String> nameKeywords = Arrays.asList("Alice", "Bob");

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new FindCommandPersonPredicate(
                        nameKeywords,
                        emptyKeywords,
                        emptyKeywords,
                        emptyKeywords,
                        emptyKeywords,
                        emptyKeywords,
                        emptyKeywords));

        // space in front of n/ is needed, see javadoc for ArgumentTokenizer.findPrefixPosition
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
