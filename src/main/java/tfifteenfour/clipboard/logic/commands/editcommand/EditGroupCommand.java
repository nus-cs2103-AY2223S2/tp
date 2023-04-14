package tfifteenfour.clipboard.logic.commands.editcommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Edits the name of a group.
 */
public class EditGroupCommand extends EditCommand {
    public static final String COMMAND_TYPE_WORD = "group";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Edits a group name."
            + " Parameters: INDEX NEW_GROUP_NAME\n"
            + "Note: INDEX must be a positive integer\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " 1 T01";

    public static final String MESSAGE_SUCCESS = "Edited group: %1$s to %2$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the course";

    private final Index index;
    private final Group newGroup;

    /**
     * Constructs a EditGroupCommand object with the given index and new group.
     *
     * @param index   The index of the group to edit.
     * @param newGroup The new Group object with the updated name.
     */

    public EditGroupCommand(Index index, Group newGroup) {
        this.index = index;
        this.newGroup = newGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.GROUP_PAGE) {
            throw new CommandException("Wrong page. Navigate to group page to edit group");
        }

        Course selectedCourse = currentSelection.getSelectedCourse();
        List<Group> lastShownList = selectedCourse.getUnmodifiableFilteredGroupList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        } else if (selectedCourse.hasGroup(newGroup)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        Group groupToEdit = lastShownList.get(index.getZeroBased());
        List<Student> students = groupToEdit.getUnmodifiableStudentList();
        List<Session> sessions = groupToEdit.getUnmodifiableSessionList();
        students.forEach(newGroup::addStudent);
        sessions.forEach(newGroup::addSession);

        selectedCourse.setGroup(groupToEdit, newGroup);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, groupToEdit, newGroup), willModifyState);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EditGroupCommand)) {
            return false;
        }
        EditGroupCommand other = (EditGroupCommand) obj;
        return index.equals(other.index) && newGroup.equals(other.newGroup);
    }
}
