package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.ExpenseInCategoryPredicate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCategories.FOOD;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_categoryFieldPresent_success() {
        ExpenseInCategoryPredicate predicate = null;
        try {
            predicate =
                    new ExpenseInCategoryPredicate(ParserUtil.parseCategory("food"));
        } catch (ParseException e) {
            fail("Unexpected exception was thrown");
        }
        ListCommand expectedCommand = new ListCommand(Optional.of(predicate), Optional.empty());

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FOOD.getCategoryName(), expectedCommand);

        // no preamble
        assertParseSuccess(parser, FOOD.getCategoryName(), expectedCommand);
    }

    @Test
    public void parse_noFields_success() {
        ListCommand expectedCommand = new ListCommand(Optional.empty(), Optional.empty());

        // whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE, expectedCommand);

        // no preamble
        assertParseSuccess(parser, "", expectedCommand);
    }

}
