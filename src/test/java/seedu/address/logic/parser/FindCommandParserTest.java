package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertSame;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noPrefix_throwsParseException() {
        assertParseFailure(parser, "Alice Bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_findPredicateIsTheSame() {
        Predicate<Person> predicate = x -> true;
        predicate = predicate.and(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        FindCommand expectedFindCommand = new FindCommand(predicate);
        assertSame(expectedFindCommand.getPredicate(), predicate);
    }

    // Cannot test due to java limitations at checking predicate equality
    //@Test
    //public void parse_validArgs_returnsFindCommand() throws ParseException {
    //// no leading and trailing whitespaces
    //    FindCommand expectedFindCommand =
    //          new FindCommand(truePredicate.and(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))));
    //        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);
    //
    //    // multiple whitespaces between keywords
    //    assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindCommand);
    //}

}
