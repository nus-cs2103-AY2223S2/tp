package seedu.careflow.logic.parser.drugparser;

import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.drugcommands.FindCommand;
import seedu.careflow.model.drug.TradeNameContainsKeywordsPredicate;

class FindCommandParserTest {

    private final FindCommandParser findCommandParser = new FindCommandParser();
    @Test
    public void parse_validTradeName_returnsFindCommand() {

        List<String> keywords = List.of("Panadol");

        TradeNameContainsKeywordsPredicate tradeNameContainsKeywordsPredicate =
                new TradeNameContainsKeywordsPredicate(keywords);

        assertParseSuccess(findCommandParser, "Panadol",
                new FindCommand(tradeNameContainsKeywordsPredicate));
    }

}
