package tfifteenfour.clipboard.logic.commands.deletecommand;


import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.testutil.TypicalModel;

class DeleteCourseCommandTest {
    private Model model;
    private Course selectedCourse;
    private Group selectedGroup;
    private Session selectedSession;
    private Student selectedStudent;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();
        selectedSession = model.getCurrentSelection().getSelectedSession();
        selectedStudent = model.getCurrentSelection().getSelectedStudent();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
    }

    @Test
    public void execute_validIndex_success() {
        DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(INDEX_FIRST);
        Course courseToDelete = selectedCourse;

        String expectedMessage = String.format(DeleteCourseCommand.MESSAGE_SUCCESS, selectedCourse);
        Model expectedModel = model.copy();
        expectedModel.getRoster().deleteCourse(courseToDelete);

        assertCommandSuccess(deleteCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> deleteCourseCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
        DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> deleteCourseCommand.execute(model));
    }

}
