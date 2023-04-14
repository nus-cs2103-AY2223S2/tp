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

/**
 * Edits the name of an existing course.
 */
public class EditCourseCommand extends EditCommand {
    public static final String COMMAND_TYPE_WORD = "course";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Edits a course name."
            + " Parameters: INDEX NEW_COURSE_NAME\n"
            + "Note: INDEX must be a positive integer\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " 1 CS2105";

    public static final String MESSAGE_SUCCESS = "Edited course: %1$s to %2$s";
    public static final String MESSAGE_DUPLICATE_COURSE = "This course already exists";


    private final Index index;
    private final Course newCourse;

    /**
     * Constructs an {@code EditCourseCommand} with the specified index and new course.
     * @param index The index of the course to be edited.
     * @param newCourse The new course to replace the existing course.
     */
    public EditCourseCommand(Index index, Course newCourse) {
        this.index = index;
        this.newCourse = newCourse;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.COURSE_PAGE) {
            throw new CommandException("Wrong page. Navigate to course page to edit course");
        }

        List<Course> lastShownList = model.getRoster().getUnmodifiableFilteredCourseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
        } else if (lastShownList.contains(newCourse)) {
            throw new CommandException(MESSAGE_DUPLICATE_COURSE);
        }

        Course courseToEdit = lastShownList.get(index.getZeroBased());
        List<Group> groups = courseToEdit.getUnmodifiableGroupList();
        for (Group group : groups) {
            newCourse.addGroup(group);
        }

        model.getRoster().setCourse(courseToEdit, newCourse);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, courseToEdit, newCourse), willModifyState);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EditCourseCommand)) {
            return false;
        }
        EditCourseCommand other = (EditCourseCommand) obj;
        return index.equals(other.index) && newCourse.equals(other.newCourse);
    }

}
