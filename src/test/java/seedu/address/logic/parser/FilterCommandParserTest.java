package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterCommand.
 */
public class FilterCommandParserTest {
    public static final String ERROR_MESSAGE = "Invalid command format! \n"
            + "filter: Filters all address book students.\n "
            + "Parameters: the metric to be sorted (performance or urgency), "
            + "and the desired threshold value (0 to 100)\n"
            + "For example, 'filter performance 40' is a command you can type";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void check_attributes() throws CommandException {
        FilterCommandParser filterParser = new FilterCommandParser();
        assertEquals(filterParser.METRIC_INDEX, 1);
        assertEquals(filterParser.THRESHOLD_INDEX, 2);
        assertTrue(filterParser.VALIDMETRICS.contains("performance"));
        assertTrue(filterParser.VALIDMETRICS.contains("urgency"));
        assertFalse(filterParser.VALIDMETRICS.contains("name"));
    }

    @Test
    public void execute() {
        FilterCommandParser filterParser = new FilterCommandParser();

        assertParseFailure(filterParser, "sda", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter all performance 40", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter performance 101", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter performance 40.0", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter performance -1", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter performance 40.5", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter performanc 40", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter name 40", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter 40", ERROR_MESSAGE);
        assertParseFailure(filterParser, "filter 40 performance", ERROR_MESSAGE);

        FilterCommand filterCommand = new FilterCommand("performance", 40);
        assertParseSuccess(filterParser, "filter performance 40", filterCommand);

        filterCommand = new FilterCommand("urgency", 100);
        assertParseSuccess(filterParser, "filter urgency 100", filterCommand);

        filterCommand = new FilterCommand("performance", 0);
        assertParseSuccess(filterParser, "filter performance 0", filterCommand);
    }
}
