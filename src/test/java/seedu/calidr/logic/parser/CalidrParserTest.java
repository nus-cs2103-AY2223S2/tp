package seedu.calidr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.calidr.logic.commands.AddEventCommand;
import seedu.calidr.logic.commands.AddTodoCommand;
import seedu.calidr.logic.commands.Command;
import seedu.calidr.logic.commands.DeleteTaskCommand;
import seedu.calidr.logic.commands.EditTaskCommand;
import seedu.calidr.logic.commands.ExitCommand;
import seedu.calidr.logic.commands.HelpCommand;
import seedu.calidr.logic.commands.MarkTaskCommand;
import seedu.calidr.logic.commands.PageCommand;
import seedu.calidr.logic.commands.SearchTaskCommand;
import seedu.calidr.logic.commands.ShowCommand;
import seedu.calidr.logic.commands.UnmarkTaskCommand;
import seedu.calidr.logic.commands.ViewDateCommand;
import seedu.calidr.logic.parser.exceptions.ParseException;

public class CalidrParserTest {

    private CalidrParser parser;

    @BeforeEach
    public void setUp() {
        parser = new CalidrParser();
    }

    @Test
    public void parseCommand_addTodoCommand_success() throws ParseException {
        Command command = parser.parseCommand("todo t/ assignment by/ 12-12-2023 2359");
        assertEquals(AddTodoCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_addEventCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "event t/ lecture from/ 27-08-2023 1500 to/ 27-08-2023 1700");
        assertEquals(AddEventCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_editTaskCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "edit 3 to/ 23-05-2023 1400");
        assertEquals(EditTaskCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_deleteTaskCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "delete 6");
        assertEquals(DeleteTaskCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_searchTaskCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "search book");
        assertEquals(SearchTaskCommand.class, command.getClass());
    }
    @Test
    public void parseCommand_markTaskCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "mark 2");
        assertEquals(MarkTaskCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_unmarkTaskCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "unmark 4");
        assertEquals(UnmarkTaskCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_showCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "show 5");
        assertEquals(ShowCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_pageCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "page week");
        assertEquals(PageCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_viewDateCommand_success() throws ParseException {
        Command command = parser.parseCommand(
                "view 14-07-2023");
        assertEquals(ViewDateCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_helpCommand_success() throws ParseException {
        Command command = parser.parseCommand("help");
        assertEquals(HelpCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_exitCommand_success() throws ParseException {
        Command command = parser.parseCommand("exit");
        assertEquals(ExitCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_unknownCommand_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parseCommand("foobar"));
    }

}
