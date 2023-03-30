package ezschedule.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.model.Model;
import ezschedule.model.event.Event;

/**
 * Deletes an event identified using it's displayed index from the scheduler.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undo the most recently deleted event and adds to the scheduler \n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Retrieved most recent deleted event: %1$s";
    @Override
    public String commandWord() {
        return COMMAND_WORD;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Command prevCommand = model.recentCommand().get(0);
        switch (prevCommand.commandWord()) {
        case "add":
            Event preEvent = model.recentEvent().get(0);
            model.deleteEvent(preEvent);
            break;
        case "edit":
            Event toEditEvent = model.recentEvent().get(0);
            Event editedEvent = model.recentEvent().get(1);
            model.setEvent(editedEvent, toEditEvent);
            break;
        case "delete":
            Event deletedEvent = model.recentEvent().get(0);
            model.addEvent(deletedEvent);
            break;
        case "recur":
            ArrayList<Event> recurEventList = model.recentEvent();
            for (Event event : recurEventList) {
                model.deleteEvent(event);
            }
            break;
        default:
            break;
        }
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, "Empty string for Undo action now"));
    }
}
