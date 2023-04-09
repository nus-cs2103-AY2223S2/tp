package fasttrack.logic.parser.add;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static fasttrack.logic.commands.CommandTestUtil.AMT_APPLE;
import static fasttrack.logic.commands.CommandTestUtil.CAT_APPLE;
import static fasttrack.logic.commands.CommandTestUtil.DESC_APPLE;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_DATE;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_END_DATE;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_INTERVAL;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_START_DATE;
import static fasttrack.logic.commands.CommandTestUtil.VALID_END_DATE;
import static fasttrack.logic.commands.CommandTestUtil.VALID_INTERVAL_DAY;
import static fasttrack.logic.commands.CommandTestUtil.VALID_INTERVAL_MONTH;
import static fasttrack.logic.commands.CommandTestUtil.VALID_INTERVAL_WEEK;
import static fasttrack.logic.commands.CommandTestUtil.VALID_INTERVAL_YEAR;
import static fasttrack.logic.commands.CommandTestUtil.VALID_START_DATE;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fasttrack.testutil.TypicalRecurringExpenseManagers.RECUR_APPLE_DAY;
import static fasttrack.testutil.TypicalRecurringExpenseManagers.RECUR_APPLE_DAY_WITH_END;
import static fasttrack.testutil.TypicalRecurringExpenseManagers.RECUR_APPLE_MONTH;
import static fasttrack.testutil.TypicalRecurringExpenseManagers.RECUR_APPLE_MONTH_WITH_END;
import static fasttrack.testutil.TypicalRecurringExpenseManagers.RECUR_APPLE_WEEK;
import static fasttrack.testutil.TypicalRecurringExpenseManagers.RECUR_APPLE_WEEK_WITH_END;
import static fasttrack.testutil.TypicalRecurringExpenseManagers.RECUR_APPLE_YEAR;
import static fasttrack.testutil.TypicalRecurringExpenseManagers.RECUR_APPLE_YEAR_WITH_END;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.add.AddRecurringExpenseCommand;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Price;

class AddRecurringExpenseCommandParserTest {
    private final AddRecurringExpenseCommandParser parser = new AddRecurringExpenseCommandParser();

    @Test
    void parse_validArgs_returnsAddRecurringExpenseCommand() {
        String input1 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_DAY + VALID_START_DATE;
        AddRecurringExpenseCommand expected1 = new AddRecurringExpenseCommand(RECUR_APPLE_DAY);
        assertParseSuccess(parser, input1, expected1);

        String input2 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_WEEK + VALID_START_DATE;
        AddRecurringExpenseCommand expected2 = new AddRecurringExpenseCommand(RECUR_APPLE_WEEK);
        assertParseSuccess(parser, input2, expected2);

        String input3 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_MONTH + VALID_START_DATE;
        AddRecurringExpenseCommand expected3 = new AddRecurringExpenseCommand(RECUR_APPLE_MONTH);
        assertParseSuccess(parser, input3, expected3);

        String input4 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_YEAR + VALID_START_DATE;
        AddRecurringExpenseCommand expected4 = new AddRecurringExpenseCommand(RECUR_APPLE_YEAR);
        assertParseSuccess(parser, input4, expected4);

        String input5 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_DAY + VALID_START_DATE + VALID_END_DATE;
        AddRecurringExpenseCommand expected5 = new AddRecurringExpenseCommand(RECUR_APPLE_DAY_WITH_END);
        assertParseSuccess(parser, input5, expected5);

        String input6 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_WEEK + VALID_START_DATE + VALID_END_DATE;
        AddRecurringExpenseCommand expected6 = new AddRecurringExpenseCommand(RECUR_APPLE_WEEK_WITH_END);
        assertParseSuccess(parser, input6, expected6);

        String input7 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_MONTH + VALID_START_DATE + VALID_END_DATE;
        AddRecurringExpenseCommand expected7 = new AddRecurringExpenseCommand(RECUR_APPLE_MONTH_WITH_END);
        assertParseSuccess(parser, input7, expected7);

        String input8 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_YEAR + VALID_START_DATE + VALID_END_DATE;
        AddRecurringExpenseCommand expected8 = new AddRecurringExpenseCommand(RECUR_APPLE_YEAR_WITH_END);
        assertParseSuccess(parser, input8, expected8);
    }

    @Test
    void parse_invalidValue_throwsParseException() {
        // Invalid category name
        String input1 = DESC_APPLE + AMT_APPLE + INVALID_CATEGORY_DESC + VALID_INTERVAL_DAY + VALID_START_DATE;
        assertParseFailure(parser, input1, Category.MESSAGE_CONSTRAINTS);

        // invalid price
        String input2 = DESC_APPLE + INVALID_AMOUNT_DESC + CAT_APPLE + VALID_INTERVAL_DAY + VALID_START_DATE;
        assertParseFailure(parser, input2, Price.MESSAGE_CONSTRAINTS);

        // invalid start date
        String input3 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_DAY + INVALID_DATE;
        assertParseFailure(parser, input3, MESSAGE_INVALID_DATE_FORMAT);

        // End date after start date
        String input4 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_DAY + INVALID_START_DATE + INVALID_END_DATE;
        assertParseFailure(parser, input4, "End date provided is earlier than start date.");

        // Invalid interval
        String input5 = DESC_APPLE + AMT_APPLE + CAT_APPLE + INVALID_INTERVAL + VALID_START_DATE;
        assertParseFailure(parser, input5, "Not a valid date format (day, week, month, year)");

        // Missing name
        String input6 = AMT_APPLE + CAT_APPLE + VALID_INTERVAL_DAY + VALID_START_DATE;
        assertParseFailure(parser, input6, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRecurringExpenseCommand.MESSAGE_USAGE));

        // Missing category
        String input7 = DESC_APPLE + AMT_APPLE + VALID_INTERVAL_DAY + VALID_START_DATE;
        assertParseFailure(parser, input6, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRecurringExpenseCommand.MESSAGE_USAGE));

        // Missing price
        String input8 = DESC_APPLE + CAT_APPLE + VALID_INTERVAL_DAY + VALID_START_DATE;
        assertParseFailure(parser, input8, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRecurringExpenseCommand.MESSAGE_USAGE));

        // Missing interval
        String input9 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_START_DATE;
        assertParseFailure(parser, input9, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRecurringExpenseCommand.MESSAGE_USAGE));

        // Missing start date
        String input10 = DESC_APPLE + AMT_APPLE + CAT_APPLE + VALID_INTERVAL_DAY;
        assertParseFailure(parser, input10, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRecurringExpenseCommand.MESSAGE_USAGE));
    }
}
