package tfifteenfour.clipboard.logic.commands.editcommand;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class EditCourseCommandTest {
    private Model model;
    private Course originalCourse;
    private Course newCourse;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.COURSE_PAGE);
        originalCourse = model.getCurrentSelection().getSelectedCourse();
        newCourse = new Course("New Course");
    }

    @Test
    public void execute_validIndexAndNewCourseName_success() {
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST, newCourse);

        String expectedMessage = String.format(EditCourseCommand.MESSAGE_SUCCESS, originalCourse, newCourse);
        Model expectedModel = model.copy();
        expectedModel.getRoster().setCourse(originalCourse, newCourse);
        assertCommandSuccess(editCourseCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicateCourse_throwsCommandException() {
        Course firstCourse = model.getRoster()
                .getUnmodifiableFilteredCourseList()
                .get(INDEX_FIRST.getZeroBased());
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_SECOND, firstCourse);

        assertThrows(CommandException.class, () -> editCourseCommand.execute(model));
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Course editedCourse = new Course("NewCourse");

        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_OUT_OF_BOUND, editedCourse);

        assertThrows(CommandException.class, () -> editCourseCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        model.getCurrentSelection().setCurrentPage(PageType.STUDENT_PAGE);
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST, newCourse);

        assertThrows(CommandException.class, () -> editCourseCommand.execute(model));
    }
}
