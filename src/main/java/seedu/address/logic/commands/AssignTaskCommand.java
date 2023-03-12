package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.List;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.person.Person;

/**
 * Assigns a task to a member of the team
 */
public class AssignTaskCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a task to a particular user "
            + "Parameters: "
            + PREFIX_TASK_INDEX + "TASK_ID "
            + PREFIX_PERSON_INDEX + "MEMBER_ID"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_INDEX + "3"
            + PREFIX_PERSON_INDEX + "2";

    public static final String MESSAGE_SUCCESS = "Assigned task to %s \n %s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task is already assigned.";
    public static final String MESSAGE_INVALID_INDEX = "This task is not in the list.";

    private final Index toAssignTask;
    private final Index toAssignMember;

    /**
     * Creates an AssignTaskCommand to add the specified {@code DeadlineTask}
     */
    public AssignTaskCommand(Index taskIndex, Index memberIndex) {
        requireNonNull(taskIndex);
        requireNonNull(memberIndex);
        this.toAssignTask = taskIndex;
        this.toAssignMember = memberIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (!model.hasTaskIndex(toAssignTask)) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        Task taskToAssign = lastShownList.get(toAssignTask.getZeroBased());
        Person personToAssign = lastShownPersonList.get(toAssignMember.getZeroBased());

        System.out.println("Assigning task " + taskToAssign.toString() + " to " + personToAssign.getName().toString() + "...");

        model.assignTask(toAssignTask, toAssignMember);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToAssign.getName().toString(), taskToAssign.toString()));
    }

    // @Override
    // public boolean equals(Object other) {
    //     return other == this // short circuit if same object
    //             || (other instanceof DeadlineCommand // instanceof handles nulls
    //             && toAdd.equals(((DeadlineCommand) other).toAdd));
    // }
}
