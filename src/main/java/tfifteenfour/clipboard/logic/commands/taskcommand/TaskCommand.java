package tfifteenfour.clipboard.logic.commands.taskcommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;


/**
 * Command that allows the user to view the tasks of a selected group
 */
public class TaskCommand extends Command {
    public static final String COMMAND_WORD = "task";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the tasks of the selected group at the index number in the current displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "[TASK PAGE]\nViewing: tasks of group %s";

    private final Index targetIndex;

    /**
     * Creates a TaskCommand to select a group at the specified index
     *
     * @param targetIndex the index of the group to select
     */
    public TaskCommand(Index targetIndex) {
        super(true);
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command to select a group and view its tasks
     *
     * @param model the model to execute the command on
     * @return a CommandResult indicating the result of the command
     * @throws CommandException if there is an error executing the command
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.GROUP_PAGE) {
            throw new CommandException("Wrong page. Navigate to group page to choose group you want to view tasks for");
        }

        Group selectedGroup = handleSelectGroup(model, currentSelection);
        currentSelection.setCurrentPage(PageType.TASK_PAGE);
        return new CommandResult(this,
                String.format("[TASK PAGE]\nViewing: tasks of group %s", selectedGroup), willModifyState);
    }

    /**
     * Handles the selection of a group and updates the current selection
     *
     * @param model the model to execute the command on
     * @param currentSelection the current selection of the user
     * @return the selected group
     * @throws CommandException if there is an error executing the command
     */
    private Group handleSelectGroup(Model model, CurrentSelection currentSelection) throws CommandException {
        List<Group> groupList = currentSelection.getSelectedCourse().getUnmodifiableFilteredGroupList();
        if (targetIndex.getZeroBased() >= groupList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }
        Group selectedGroup = groupList.get(targetIndex.getZeroBased());

        currentSelection.selectGroup(selectedGroup);
        return selectedGroup;
    }

    /**
     * Checks if this SessionCommand is equal to another object
     *
     * @param other the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCommand)) {
            return false;
        }

        // state check
        TaskCommand e = (TaskCommand) other;
        return targetIndex.equals(e.targetIndex);
    }
}
