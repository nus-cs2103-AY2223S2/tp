package teambuilder.logic.commands;

import org.junit.jupiter.api.Test;
import teambuilder.commons.core.Messages;
import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.ModelManager;
import teambuilder.model.TeamBuilder;
import teambuilder.model.UserPrefs;
import teambuilder.model.person.Person;

import static org.junit.jupiter.api.Assertions.*;
import static teambuilder.logic.commands.CommandTestUtil.assertCommandSuccess;
import static teambuilder.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static teambuilder.testutil.TypicalPersons.getTypicalAddressBook;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unknownOrderSpecified_failure() {
        CommandException thrown = assertThrows(CommandException.class,
                () -> new SortCommand("unknown", "tcount").execute(model));
        assertTrue(thrown.getMessage().contentEquals(Messages.MESSAGE_INVALID_SORTING_ORDER));
    }

    @Test
    public void execute_emptyOrderSpecified_failure() {
        CommandException thrown = assertThrows(CommandException.class,
                () -> new SortCommand("", "tcount").execute(model));
        assertTrue(thrown.getMessage().contentEquals(Messages.MESSAGE_INVALID_SORTING_ORDER));
    }

    @Test
    public void execute_unknownSortBySpecified_failure() {
        CommandException thrown = assertThrows(CommandException.class,
                () -> new SortCommand("desc", "unknown").execute(model));
        assertTrue(thrown.getMessage().contentEquals(Messages.MESSAGE_INVALID_SORT_BY));
    }

    @Test
    public void execute_emptySortBySpecified_failure() {
        CommandException thrown = assertThrows(CommandException.class,
                () -> new SortCommand("desc", "").execute(model));
        assertTrue(thrown.getMessage().contentEquals(Messages.MESSAGE_INVALID_SORT_BY));
    }

    @Test
    public void execute_allArgumentsSpecifiedSuccessfully_success() throws CommandException {
        CommandResult commandResult = new SortCommand("desc", "tcount").execute(model);
        assertEquals(SortCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

}
