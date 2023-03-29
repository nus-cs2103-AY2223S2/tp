package ezschedule.logic.commands;

import static java.util.Objects.requireNonNull;

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
    public String commandWord() {return COMMAND_WORD;}
    
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Command prevCommand = model.recentCommand().get(0);
        Event preEvent = model.recentEvent().get(0);
        System.out.println(model.recentEvent());
        switch (prevCommand.commandWord()) {
        case "add":
            model.deleteEvent(preEvent);
        case "edit":
            Event editedEvent = model.recentEvent().get(1);
            model.setEvent(editedEvent,preEvent);
        case "delete":
            model.addEvent(preEvent);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS,preEvent));
    }
}
