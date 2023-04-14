package tfifteenfour.clipboard.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.predicates.ShowAllListedPredicate;
import tfifteenfour.clipboard.logic.predicates.TaskNameContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;

/**
 * Finds a task in the selected group whose name contains any of the specified keywords.
 */
public class FindTaskCommand extends FindCommand {
    public static final String COMMAND_TYPE_WORD = "task";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + ": Finds a task. "
            + "Parameters: "
            + "TASK_SEARCH_TERM\n"
            + "Example: " + COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + " " + "Assignment1 ";

    public static final String MESSAGE_SUCCESS = "Found %1$s results";
    private final TaskNameContainsPredicate predicate;
    private final CurrentSelection currentSelection;

    /**
     * Creates a FindTaskCommand to find the specified sessions with the given predicate.
     * @param predicate The predicate to filter tasks with.
     * @param currentSelection The current selection of the LogicManager.
     */
    public FindTaskCommand(TaskNameContainsPredicate predicate, CurrentSelection currentSelection) {
        this.predicate = predicate;
        this.currentSelection = currentSelection;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Group selectedGroup = currentSelection.getSelectedGroup();
        selectedGroup.updateFilteredTasks(predicate);
        int filteredSize = selectedGroup.getUnmodifiableFilteredTaskList().size();

        if (filteredSize == 0) {
            ShowAllListedPredicate.resetTasksFilter(currentSelection);
        }

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, filteredSize), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate));
    }
}
