package tfifteenfour.clipboard.logic.commands;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class AttendanceCommand extends Command {
    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the sessions of the selected group at the index number in the current displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Creates a AttendanceCommand to select a group at the specified index
     */
    public AttendanceCommand(Index targetIndex) {
        super(false);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
        requireNonNull(model);

        if (currentSelection.getCurrentPage() != PageType.GROUP_PAGE) {
            throw new CommandException("Wrong page. Navigate to group page to view sessions");
        }

        Group selectedGroup = handleSelectGroup(model, currentSelection);
        currentSelection.setCurrentPage(PageType.SESSION_PAGE);
        return new CommandResult(this, String.format("Viewing session of : %s", selectedGroup), willModifyState);
    }


    private Group handleSelectGroup(Model model, CurrentSelection currentSelection) throws CommandException {
        List<Group> groupList = currentSelection.getSelectedCourse().getUnmodifiableGroupList();
        if (targetIndex.getZeroBased() >= groupList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }
        Group selectedGroup = groupList.get(targetIndex.getZeroBased());

        currentSelection.selectGroup(selectedGroup);
        return selectedGroup;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        // state check
        AttendanceCommand e = (AttendanceCommand) other;
        return targetIndex.equals(e.targetIndex);
    }
}
