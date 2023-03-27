package arb.logic.parser.client;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.client.FindClientCommand;
import arb.model.client.Client;
import arb.model.client.predicates.ClientContainsTagPredicate;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.testutil.PredicateUtil;

public class FindClientCommandParserTest {

    private FindClientCommandParser parser = new FindClientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void parse_validArgs_returnsFindClientCommand() {
        List<String> expectedTags = Arrays.asList("friend");
        List<String> expectedNames = Arrays.asList("Alice", "Bob");
        ClientContainsTagPredicate expectedTagsPredicate = new ClientContainsTagPredicate(expectedTags);
        NameContainsKeywordsPredicate expectedTitlesPredicate = new NameContainsKeywordsPredicate(expectedNames);
        CombinedPredicate<Client> expectedCombinedPredicate =
                PredicateUtil.getCombinedPredicate(expectedTagsPredicate, expectedTitlesPredicate);

        // no leading and trailing whitespaces
        FindClientCommand expectedFindClientCommand =
                new FindClientCommand(expectedCombinedPredicate);
        assertParseSuccess(parser, " n/Alice n/Bob t/friend", expectedFindClientCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t t/friend \n", expectedFindClientCommand);
    }

}
