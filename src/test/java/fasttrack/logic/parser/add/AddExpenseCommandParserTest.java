package fasttrack.logic.parser.add;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static fasttrack.logic.commands.CommandTestUtil.AMT_APPLE;
import static fasttrack.logic.commands.CommandTestUtil.AMT_BANANA;
import static fasttrack.logic.commands.CommandTestUtil.CAT_APPLE;
import static fasttrack.logic.commands.CommandTestUtil.CAT_BANANA;
import static fasttrack.logic.commands.CommandTestUtil.DATE_APPLE;
import static fasttrack.logic.commands.CommandTestUtil.DATE_BANANA;
import static fasttrack.logic.commands.CommandTestUtil.DESC_APPLE;
import static fasttrack.logic.commands.CommandTestUtil.DESC_BANANA;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_DATE_FORMAT_DESC;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fasttrack.testutil.TypicalExpenses.APPLE;
import static fasttrack.testutil.TypicalExpenses.BANANA;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.add.AddExpenseCommand;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Price;


class AddExpenseCommandParserTest {

    private final AddExpenseCommandParser parser = new AddExpenseCommandParser();

    @Test
    void parse_validArgs_returnsAddExpenseCommand() {
        String input1 = DESC_APPLE + AMT_APPLE + DATE_APPLE + CAT_APPLE;
        AddExpenseCommand expectedCommand1 = new AddExpenseCommand(APPLE);
        assertParseSuccess(parser, input1, expectedCommand1);

        String input2 = DESC_BANANA + AMT_BANANA + CAT_BANANA + DATE_BANANA;
        AddExpenseCommand expectedCommand2 = new AddExpenseCommand(BANANA);
        assertParseSuccess(parser, input2, expectedCommand2);
    }

    @Test
    void parse_invalidValue_throwsParseException() {
        // invalid category name (with alphanumeric)
        String input1 = DESC_APPLE + AMT_APPLE + DATE_APPLE + INVALID_CATEGORY_DESC;
        assertParseFailure(parser, input1, Category.MESSAGE_CONSTRAINTS);

        // invalid date format
        String input2 = DESC_APPLE + AMT_APPLE + INVALID_DATE_FORMAT_DESC + CAT_APPLE;
        assertParseFailure(parser, input2, MESSAGE_INVALID_DATE_FORMAT);

        // invalid price
        String input3 = DESC_APPLE + INVALID_AMOUNT_DESC + DATE_APPLE + CAT_APPLE;
        assertParseFailure(parser, input3, Price.MESSAGE_CONSTRAINTS);
    }
}
