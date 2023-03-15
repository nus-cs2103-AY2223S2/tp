package vimification.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import vimification.commons.core.Messages;
import vimification.commons.core.index.Index;
import vimification.logic.commands.exceptions.CommandException;
import vimification.model.Model;
import vimification.model.TaskList;
import vimification.model.person.Person;
import vimification.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the task list.
 */
public class DeleteCommand extends LogicCommand {

    public static final String MESSAGE_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;


    public DeleteCommand(TaskList taskList, Index targetIndex) {
        super(taskList);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= super.numOfTask()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = super.getTask(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToDelete));
    }

    @Override
    public CommandResult undo() {
        return new CommandResult(String.format("%s", ""));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                        && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

    // For possible testing. Can be removed prior to production
    public boolean targetIndexEquals(Object other) {
        return this.targetIndex.equals(((Index) other));
    }
}

//    public static final String COMMAND_WORD = "delete";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD
//            + ": Deletes the person identified by the index number used in the displayed person list.\n"
//            + "Parameters: INDEX (must be a positive integer)\n"
//            + "Example: " + COMMAND_WORD + " 1";
//
//    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
//
//    private final Index targetIndex;
//
//    public DeleteCommand(Index targetIndex) {
//        this.targetIndex = targetIndex;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        List<Person> lastShownList = model.getFilteredPersonList();
//
//        if (targetIndex.getZeroBased() >= lastShownList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//        }
//
//        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
//        model.deletePerson(personToDelete);
//        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof DeleteCommand // instanceof handles nulls
//                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
//    }
//}
