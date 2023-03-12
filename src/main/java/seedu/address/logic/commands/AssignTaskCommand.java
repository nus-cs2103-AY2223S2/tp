package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

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

    private final Index toAssignTask;
    private final Index toAssignMember;

    /**
     * Creates an AssignTaskCommand to add the specified {@code DeadlineTask}
     * @param taskIndex
     * @param memberIndex
     */
    public AssignTaskCommand(Index taskIndex, Index memberIndex) {
        requireNonNull(taskIndex);
        requireNonNull(memberIndex);
        this.toAssignTask = taskIndex;
        this.toAssignMember = memberIndex;
    }

    /**
     * Executes the assign task command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (!model.hasTaskIndex(toAssignTask)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        if (!model.hasPersonIndex(toAssignMember)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToAssign = lastShownList.get(toAssignTask.getZeroBased());
        Person personToAssign = lastShownPersonList.get(toAssignMember.getZeroBased());

        model.assignTask(toAssignTask, toAssignMember);
        String taskString = taskToAssign.toString();
        String personString = personToAssign.getName().toString();
        return new CommandResult(String.format(MESSAGE_SUCCESS, personString, taskString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignTaskCommand // instanceof handles nulls
                && toAssignTask.equals(((AssignTaskCommand) other).toAssignTask)
                && toAssignMember.equals(((AssignTaskCommand) other).toAssignMember));
    }

}
