package teambuilder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static teambuilder.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import teambuilder.commons.core.Messages;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.ModelManager;
import teambuilder.model.UserPrefs;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unknownOrderSpecified_failure() {
        CommandException thrown = assertThrows(CommandException.class, () ->
                new SortCommand("unknown", "tcount").execute(model));
        assertTrue(thrown.getMessage().contentEquals(Messages.MESSAGE_INVALID_SORTING_ORDER));
    }

    @Test
    public void execute_emptyOrderSpecified_failure() {
        CommandException thrown = assertThrows(CommandException.class, () ->
                new SortCommand("", "tcount").execute(model));
        assertTrue(thrown.getMessage().contentEquals(Messages.MESSAGE_INVALID_SORTING_ORDER));
    }

    @Test
    public void execute_unknownSortBySpecified_failure() {
        CommandException thrown = assertThrows(CommandException.class, () ->
                new SortCommand("desc", "unknown").execute(model));
        assertTrue(thrown.getMessage().contentEquals(Messages.MESSAGE_INVALID_SORT_BY));
    }

    @Test
    public void execute_emptySortBySpecified_failure() {
        CommandException thrown = assertThrows(CommandException.class, () ->
                new SortCommand("desc", "").execute(model));
        assertTrue(thrown.getMessage().contentEquals(Messages.MESSAGE_INVALID_SORT_BY));
    }

    @Test
    public void execute_allArgumentsSpecifiedSuccessfully_success() throws CommandException {
        CommandResult commandResult = new SortCommand("desc", "tcount").execute(model);
        assertEquals(SortCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        final SortCommand standardCommand = new SortCommand("desc", "tcount");

        // same values -> returns true
        SortCommand sameCommand = new SortCommand("desc", "tcount");
        assertTrue(standardCommand.equals(sameCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different order -> returns false
        assertFalse(standardCommand.equals(new SortCommand("asc", "tcount")));

    }

}
