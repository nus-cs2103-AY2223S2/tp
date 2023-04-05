package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.SortStudentCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterCommand.
 */
public class SortStudentCommandParserTest {
    public static final String ERROR_MESSAGE = "Invalid command format! \n"
            + "sort-student: Sorts all address book students.\n "
            + "Parameters: The group of students you wish to sort (either all, lab, tutorial or consultation), the "
            + "metric to be sorted (either address, email, name, performance or remark), and the desired order "
            + "(either reverse or nonreverse)\n"
            + "For example: 'sort-student all name reverse' command will order all students in reverse-alphabetical "
            + "ordering of their names";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void check_attributes() throws CommandException {
        SortStudentCommandParser filterParser = new SortStudentCommandParser();
        assertEquals(filterParser.GROUP_INDEX, 1);
        assertEquals(filterParser.METRIC_INDEX, 2);
        assertEquals(filterParser.ORDER_INDEX, 3);
        assertTrue(filterParser.VALIDMETRICS.contains("performance"));
        assertFalse(filterParser.VALIDMETRICS.contains("urgency"));
        assertTrue(filterParser.VALIDMETRICS.contains("name"));
        assertTrue(filterParser.VALIDMETRICS.contains("remark"));
        assertTrue(filterParser.VALIDMETRICS.contains("email"));
        assertTrue(filterParser.VALIDMETRICS.contains("address"));

        assertTrue(filterParser.VALIDGROUPS.contains("all"));
        assertTrue(filterParser.VALIDGROUPS.contains("lab"));
        assertTrue(filterParser.VALIDGROUPS.contains("tutorial"));
        assertTrue(filterParser.VALIDGROUPS.contains("consultation"));
        assertFalse(filterParser.VALIDGROUPS.contains("labs"));
        assertFalse(filterParser.VALIDGROUPS.contains(""));
    }

    @Test
    public void execute() {
        SortStudentCommandParser sortStudentParser = new SortStudentCommandParser();

        assertParseFailure(sortStudentParser, "", ERROR_MESSAGE);
        assertParseFailure(sortStudentParser, "sda", ERROR_MESSAGE);
        assertParseFailure(sortStudentParser, "sort-student all name", ERROR_MESSAGE);
        assertParseFailure(sortStudentParser, "sort-student all group reverse", ERROR_MESSAGE);
        assertParseFailure(sortStudentParser, "sort-student name reverse", ERROR_MESSAGE);
        assertParseFailure(sortStudentParser, "sort-student all reverse", ERROR_MESSAGE);

        SortStudentCommand sortStudentCommand = new SortStudentCommand("all", "name", true);
        assertParseSuccess(sortStudentParser, "sort-student all name nonreverse", sortStudentCommand);

        sortStudentCommand = new SortStudentCommand("all", "name", false);
        assertParseSuccess(sortStudentParser, "sort-student all name reverse", sortStudentCommand);

        sortStudentCommand = new SortStudentCommand("lab", "name", false);
        assertParseSuccess(sortStudentParser, "sort-student lab name reverse", sortStudentCommand);

        sortStudentCommand = new SortStudentCommand("tutorial", "name", false);
        assertParseSuccess(sortStudentParser, "sort-student tutorial name reverse", sortStudentCommand);

        sortStudentCommand = new SortStudentCommand("consultation", "name", false);
        assertParseSuccess(sortStudentParser, "sort-student consultation name reverse", sortStudentCommand);

        sortStudentCommand = new SortStudentCommand("consultation", "address", false);
        assertParseSuccess(sortStudentParser, "sort-student consultation address reverse", sortStudentCommand);

        sortStudentCommand = new SortStudentCommand("consultation", "remark", false);
        assertParseSuccess(sortStudentParser, "sort-student consultation remark reverse", sortStudentCommand);

        sortStudentCommand = new SortStudentCommand("consultation", "email", false);
        assertParseSuccess(sortStudentParser, "sort-student consultation email reverse", sortStudentCommand);

        sortStudentCommand = new SortStudentCommand("consultation", "performance", false);
        assertParseSuccess(sortStudentParser, "sort-student consultation performance reverse",
                sortStudentCommand);
    }
}
