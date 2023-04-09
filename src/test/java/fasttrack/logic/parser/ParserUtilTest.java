package fasttrack.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.expense.Price;
import fasttrack.model.expense.RecurringExpenseType;


public class ParserUtilTest {

    @Test
    public void parseIndex_validInput_success() throws ParseException {
        assertEquals(Index.fromOneBased(1), ParserUtil.parseIndex(" 1 "));
        assertEquals(Index.fromOneBased(2), ParserUtil.parseIndex("2"));
    }

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        // empty string
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex(""));
        // string based input
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("one"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("0"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("-1"));
    }

    @Test
    public void parseExpenseName_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExpenseName(null));
    }

    @Test
    public void parseExpenseName_validInput_success() throws ParseException {
        assertEquals("milk", ParserUtil.parseExpenseName("  milk "));
    }

    @Test
    public void parseExpenseName_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExpenseName(""));
    }

    @Test
    public void parsePrice_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePrice(null));
    }

    @Test
    public void parsePrice_validInput_success() throws ParseException {
        assertEquals(new Price("1.23"), ParserUtil.parsePrice("  1.23  "));
    }

    @Test
    public void parsePrice_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(""));
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice("-1.23"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice("one"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice("0"));
    }

    //@Test
    //public void parseCategoryWithSummary_validInput_success() throws ParseException {
    //    // leading and trailing whitespace
    //    assertEquals(new UserDefinedCategory("category", "abc"), ParserUtil.parseCategory("  category ", "abc"));
    //    // miscellaneous category
    //    assertEquals(new MiscellaneousCategory(), ParserUtil.parseCategory("miscellaneous"));
    //    UserDefinedCategory category = ParserUtil.parseCategory("food", "for dining");
    //    assertEquals("food", category.getCategoryName());
    //    assertEquals("for dining", category.getSummary());
    //}

    @Test
    public void parseCategory_invalidInput_throwsParseException() {
        // empty category name
        assertThrows(ParseException.class, () -> ParserUtil.parseCategory(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseCategory("@food"));
    }


    @Test
    void parseDate_validInput_success() throws ParseException {
        LocalDate date = ParserUtil.parseDate("22/3/2023");
        assertEquals(LocalDate.parse("2023-03-22"), date);
    }

    @Test
    void parseDate_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> {
            ParserUtil.parseDate("2023/03/14");
        });
    }

    @Test
    void parseTimespan_validInput_success() throws ParseException {
        assertEquals(ParserUtil.Timespan.WEEK, ParserUtil.parseTimespan("week"));
        assertEquals(ParserUtil.Timespan.MONTH, ParserUtil.parseTimespan("month"));
        assertEquals(ParserUtil.Timespan.YEAR, ParserUtil.parseTimespan("year"));
        assertEquals(ParserUtil.Timespan.WEEK, ParserUtil.parseTimespan("w"));
        assertEquals(ParserUtil.Timespan.MONTH, ParserUtil.parseTimespan("m"));
        assertEquals(ParserUtil.Timespan.YEAR, ParserUtil.parseTimespan("y"));
    }

    @Test
    void parseTimespan_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> {
            ParserUtil.parseTimespan("wk");
        });
    }

    @Test
    void parseTimeSpanRecurringExpense_validInput_success() throws ParseException {
        assertEquals(RecurringExpenseType.DAILY, ParserUtil.parseTimeSpanRecurringExpense("day"));
        assertEquals(RecurringExpenseType.WEEKLY, ParserUtil.parseTimeSpanRecurringExpense("week"));
        assertEquals(RecurringExpenseType.MONTHLY, ParserUtil.parseTimeSpanRecurringExpense("month"));
        assertEquals(RecurringExpenseType.YEARLY, ParserUtil.parseTimeSpanRecurringExpense("year"));
        assertEquals(RecurringExpenseType.DAILY, ParserUtil.parseTimeSpanRecurringExpense("d"));
        assertEquals(RecurringExpenseType.WEEKLY, ParserUtil.parseTimeSpanRecurringExpense("w"));
        assertEquals(RecurringExpenseType.MONTHLY, ParserUtil.parseTimeSpanRecurringExpense("m"));
        assertEquals(RecurringExpenseType.YEARLY, ParserUtil.parseTimeSpanRecurringExpense("y"));
    }

    @Test
    void getDateByTimespan_validInput_success() {
        LocalDate expectedWeekDate = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate expectedMonthDate = LocalDate.now().withDayOfMonth(1);
        LocalDate expectedYearDate = LocalDate.now().withDayOfYear(1);
        assertEquals(expectedWeekDate, ParserUtil.getDateByTimespan(ParserUtil.Timespan.WEEK));
        assertEquals(expectedMonthDate, ParserUtil.getDateByTimespan(ParserUtil.Timespan.MONTH));
        assertEquals(expectedYearDate, ParserUtil.getDateByTimespan(ParserUtil.Timespan.YEAR));
    }
}
