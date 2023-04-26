package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.model.ClassList;
import taa.model.Model;
import taa.model.assignment.AssignmentList;
import taa.storage.TaaData;

/**
 * Clears the entire class list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All students have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaaData(new TaaData(new ClassList("no name"), AssignmentList.INSTANCE.getAssignments()));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
