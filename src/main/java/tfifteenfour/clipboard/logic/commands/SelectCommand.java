package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelected;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Views a student in the student list.
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the selected item at the index number in the current displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Creates a SelectCommand to select a student at the specified index
     */
    public SelectCommand(Index targetIndex) {
        super(false);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CurrentSelected currentSelected) throws CommandException {
        requireNonNull(model);



        switch (currentSelected.getCurrentPage()) {
        case COURSE_PAGE:
            Course selectedCourse = handleSelectCourse(model, currentSelected);
            return new CommandResult(this, String.format("Viewing Course: %s", selectedCourse), willModifyState);

        case GROUP_PAGE:
            Group selectedGroup = handleSelectGroup(model, currentSelected);
            return new CommandResult(this, String.format("Viewing Group: %s of %s", selectedGroup,
                    currentSelected.getSelectedCourse()), willModifyState);

        case STUDENT_PAGE:
            Student selectedStudent = handleSelectStudent(model, currentSelected);
            return new CommandResult(this, String.format("Viewing: %s", selectedStudent), willModifyState);
        default:
            throw new CommandException("Unable to select");
        }
    }

    private Course handleSelectCourse(Model model, CurrentSelected currentSelected) throws CommandException {

        List<Course> courseList = model.getUnmodifiableFilteredCourseList();
        if (targetIndex.getZeroBased() >= courseList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
        }
        Course selectedCourse = courseList.get(targetIndex.getZeroBased());
        currentSelected.setSelectedCourse(selectedCourse);
        currentSelected.setCurrentPage(PageType.GROUP_PAGE);

        return selectedCourse;
    }

    private Group handleSelectGroup(Model model, CurrentSelected currentSelected) throws CommandException {
        List<Group> groupList = currentSelected.getSelectedCourse().getUnmodifiableGroupList();
        if (targetIndex.getZeroBased() >= groupList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }
        Group selectedGroup = groupList.get(targetIndex.getZeroBased());
        currentSelected.setSelectedGroup(selectedGroup);
        currentSelected.setCurrentPage(PageType.STUDENT_PAGE);

        return selectedGroup;
    }

    private Student handleSelectStudent(Model model, CurrentSelected currentSelected) throws CommandException {
        List<Student> studentList = currentSelected.getSelectedGroup().getUnmodifiableStudentList();
        if (targetIndex.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Student selectedStudent = studentList.get(targetIndex.getZeroBased());
        // end of navigation, no longer need to call setters of currentSelected
        return selectedStudent;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SelectCommand)) {
            return false;
        }

        // state check
        SelectCommand e = (SelectCommand) other;
        return targetIndex.equals(e.targetIndex);
    }
}
