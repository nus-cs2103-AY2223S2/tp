package ezschedule.logic.commands;

import static ezschedule.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.RecurFactor;
import ezschedule.testutil.EventBuilder;

public class UndoCommandTest {
    private List<String> recurFactorList = new ArrayList<>(Arrays.asList("day", "week", "month"));
    private Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalScheduler(), new UserPrefs());
    @Test
    public void execute_undoAddCommand_successful() throws CommandException {
        Event validEvent = new EventBuilder().build();
        Command prevCommand = new AddCommand(validEvent);
        model.addEvent(validEvent);
        model.addRecentEvent(validEvent);
        model.addRecentCommand(prevCommand);
        CommandResult commandResult = new UndoCommand().execute(model);
        assertEquals(String.format(UndoCommand.MESSAGE_UNDONE_SUCCESS, prevCommand.commandWord()),
                commandResult.getFeedbackToUser());
    }
    @Test
    public void execute_undoEditCommand_successful() {
    }
    @Test
    public void execute_undoDeleteCommand_successful() throws CommandException {
        List<Event> lastShownList = model.getFilteredEventList();
        Index targetIndex = Index.initIndex(new Random().nextInt(lastShownList.size()));
        List<Index> targetIndexes = new ArrayList<>();
        targetIndexes.add(targetIndex);
        Event eventToDelete = lastShownList.get(targetIndex.getZeroBased());
        Command deleteCommand = new DeleteCommand(targetIndexes);
        model.deleteEvent(eventToDelete);
        model.addRecentEvent(eventToDelete);
        model.addRecentCommand(deleteCommand);
        CommandResult commandResult = new UndoCommand().execute(model);
        assertEquals(String.format(UndoCommand.MESSAGE_UNDONE_SUCCESS, deleteCommand.commandWord()),
                commandResult.getFeedbackToUser());
        assertCommandSuccess(new UndoCommand(), model, deleteCommand.commandWord(), expectedModel);
    }
    @Test
    public void execute_undoRecurCommand_successful() throws CommandException {
        List<Event> lastShownList = model.getFilteredEventList();
        Index targetIndex = Index.initIndex(new Random().nextInt(lastShownList.size()));
        Date endDate = new Date("2024-02-02");
        String stringRecurFactor = recurFactorList.get(new Random().nextInt(recurFactorList.size()));
        RecurFactor recurFactor = new RecurFactor(stringRecurFactor);
        Command recurCommand = new RecurCommand(targetIndex, endDate, recurFactor);
        Event eventToRecur = lastShownList.get(targetIndex.getZeroBased());
        model.addRecentCommand(recurCommand);
        switch(stringRecurFactor) {
        case "day": ((RecurCommand) recurCommand).addEventPerDay(model, eventToRecur);
            break;
        case "week": ((RecurCommand) recurCommand).addEventPerWeek(model, eventToRecur);
            break;
        case "month": ((RecurCommand) recurCommand).addEventPerMonth(model, eventToRecur);
            break;
        default:
            break;
        }
        assertCommandSuccess(new UndoCommand(), model, recurCommand.commandWord(), expectedModel);
    }
}
