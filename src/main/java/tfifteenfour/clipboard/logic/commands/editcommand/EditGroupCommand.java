package tfifteenfour.clipboard.logic.commands.editcommand;

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

import java.util.List;

import static java.util.Objects.requireNonNull;

public class EditGroupCommand extends EditCommand {
    public static final String COMMAND_TYPE_WORD = "group";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits a group name."
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + " 1 T01";

    public static final String MESSAGE_SUCCESS = "Edited group: %1$s to %2$s";

    private final Index index;
    private final Group newGroup;


    public EditGroupCommand(Index index, Group newGroup) {
        this.index = index;
        this.newGroup = newGroup;
    }

    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
        requireNonNull(model);

        if (currentSelection.getCurrentPage() != PageType.GROUP_PAGE) {
            throw new CommandException("Wrong page. Navigate to group page to edit group");
        }

        Course selectCourse = currentSelection.getSelectedCourse();
        List<Group> lastShownList = selectCourse.getModifiableGrouplist();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group GroupToEdit = lastShownList.get(index.getZeroBased());
        List<Student> students = GroupToEdit.getUnmodifiableStudentList();
        List<Session> sessions = GroupToEdit.getUnmodifiableSessionList();
        students.forEach(newGroup::addStudent);
        sessions.forEach(newGroup::addSession);

        lastShownList.set(index.getZeroBased(), newGroup);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, GroupToEdit, newGroup), willModifyState);
    }

}
