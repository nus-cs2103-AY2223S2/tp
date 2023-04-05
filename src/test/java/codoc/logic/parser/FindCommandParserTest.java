package codoc.logic.parser;

import static codoc.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import codoc.commons.core.Messages;
import codoc.logic.commands.FindCommand;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    //    @Test // Yet to fix
    //    public void parse_validArgs_returnsFindCommand() {
    //        // no leading and trailing whitespaces
    //        Predicate<Person> combinedPredicate = person -> true;
    //        Predicate<Person> namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
    //        combinedPredicate = combinedPredicate.and(namePredicate);
    //        FindCommand expectedFindCommand =
    //                new FindCommand(combinedPredicate);
    //        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);
    //
    //        // multiple whitespaces between keywords
    //        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    //    }

}
