package arb.logic.parser.client;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.client.FindClientCommand;
import arb.model.client.Client;
import arb.model.client.predicates.ClientContainsTagsPredicate;
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
        ClientContainsTagsPredicate expectedTagsPredicate = PredicateUtil.getClientContainsTagPredicate("friend");
        NameContainsKeywordsPredicate expectedTitlesPredicate =
                PredicateUtil.getNameContainsKeywordsPredicate("Alice", "Bob");
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
