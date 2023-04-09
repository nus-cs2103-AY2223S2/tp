package fasttrack.logic.parser.edit;

import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.edit.EditExpenseCommand;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.exceptions.ParseException;

public class EditExpenseCommandParserTest {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private final EditExpenseCommandParser parser = new EditExpenseCommandParser();

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_noArgumentsExceptIndex_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditExpenseCommand expectedCommand = new EditExpenseCommand(toUseForExpected, null,
                null, null, null);
        assertParseSuccess(parser, "1", expectedCommand);
    }

    @Test
    public void parse_onlyValidPriceChange_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditExpenseCommand expectedCommand = new EditExpenseCommand(toUseForExpected, null,
                4.50, null, null);
        assertParseSuccess(parser, "1 p/4.50", expectedCommand);
    }

    @Test
    public void parse_onlyValidCategoryChange_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditExpenseCommand expectedCommand = new EditExpenseCommand(toUseForExpected, null,
                null, null, "NewCategory");
        assertParseSuccess(parser, "1 c/NewCategory", expectedCommand);
    }

    @Test
    public void parse_onlyValidDateChange_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditExpenseCommand expectedCommand = new EditExpenseCommand(toUseForExpected, null,
                null, ParserUtil.parseDate("24/3/23"), null);
        assertParseSuccess(parser, "1 d/24/3/23", expectedCommand);
    }

    @Test
    public void parse_invalidDateChange_success() throws ParseException {
        assertParseFailure(parser, "1 d/2023/03/21", "Invalid date format! Please use DD/MM/YY format!");
    }
}
