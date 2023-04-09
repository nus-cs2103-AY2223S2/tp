package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.TaskBookModel.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskBookModel;
import seedu.address.model.person.Person;
import seedu.address.model.task.Comment;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;


/**
 * Assigns a task to a member of the team
 */
public class AssignTaskCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a task to a particular user.\n"
            + "Parameters: "
            + PREFIX_TASK_INDEX + "TASK_INDEX "
            + PREFIX_PERSON_INDEX + "MEMBER_INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_INDEX + "3 "
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
    public CommandResult execute(Model model, TaskBookModel taskBookModel) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = taskBookModel.getFilteredTaskList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (!taskBookModel.hasTaskIndex(toAssignTask)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        if (!model.hasPersonIndex(toAssignMember)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToAssign = lastShownList.get(toAssignTask.getZeroBased());
        Person personToAssign = lastShownPersonList.get(toAssignMember.getZeroBased());

        if (personToAssign.getRole() == null) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_ASSIGNED_ROLE);
        }

        if (taskToAssign.isDone()) {
            throw new CommandException(Messages.MESSAGE_TASK_ALREADY_DONE);
        }

        Task assignedTask = createAssignedTask(taskToAssign, toAssignMember, personToAssign);

        taskBookModel.assignTask(taskToAssign, assignedTask, toAssignTask);
        taskBookModel.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
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


    private static Task createAssignedTask(Task taskToAssign, Index personToAssign, Person personAssigned) {
        TaskDescription taskDesc = taskToAssign.getDescription();
        String taskDate = taskToAssign.getDate().toString();
        String taskType = taskToAssign.getTaskType();
        Task assignedTask = new Task(taskDesc, taskDate, taskType);
        String personAssignedName = personAssigned.getName().toString();
        String personAssignedRole = personAssigned.getRole().toString();
        assignedTask.assignPerson(personToAssign, personAssignedName, personAssignedRole);
        boolean status = taskToAssign.isDone();
        assignedTask.setStatus(status);
        Comment comment = taskToAssign.getTaskComment();
        assignedTask.setTaskComment(comment);
        return assignedTask;
    }

}
