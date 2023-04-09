package tfifteenfour.clipboard.logic.commands.addcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.testutil.TypicalModel;

class AddCourseCommandTest {
    private Model model;
    private Model expectedModel;

    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
    }

    @Test
    public void execute_courseAcceptedByModel_addSuccessful() {
        Course validCourse = new Course("New course");

        AddCourseCommand addCourseCommand = new AddCourseCommand(validCourse);
        String expectedMessage = String.format(AddCourseCommand.MESSAGE_SUCCESS, validCourse);
        expectedModel.getRoster().addCourse(validCourse);

        assertCommandSuccess(addCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);

        Course validCourse = new Course("New course");
        AddCourseCommand addCourseCommand = new AddCourseCommand(validCourse);

        assertThrows(CommandException.class,
                AddCourseCommand.MESSAGE_WRONG_PAGE, () -> addCourseCommand.execute(model));
    }

    @Test
    public void execute_duplicateCourse_throwsCommandException() {
        Course validCourse = new Course("New course");

        AddCourseCommand addCourseCommand = new AddCourseCommand(validCourse);
        String expectedMessage = String.format(AddCourseCommand.MESSAGE_SUCCESS, validCourse);
        expectedModel.getRoster().addCourse(validCourse);

        assertCommandSuccess(addCourseCommand, model, expectedMessage, expectedModel);

        AddCourseCommand commandCopy = new AddCourseCommand(new Course("New course"));
        assertThrows(CommandException.class,
                AddCourseCommand.MESSAGE_DUPLICATE_COURSE, () -> commandCopy.execute(model));
    }

    @Test
    public void equals() {

        Course validCourse = new Course("New course");
        Course validCourse2 = new Course("New course2");
        AddCourseCommand addCourseCommand1 = new AddCourseCommand(validCourse);
        AddCourseCommand addCourseCommand2 = new AddCourseCommand(validCourse2);

        // same object -> returns true
        assertEquals(addCourseCommand1, addCourseCommand1);

        // same values -> returns true
        AddCourseCommand addCourseCommandCopy1 = new AddCourseCommand(validCourse);
        assertEquals(addCourseCommand1, addCourseCommandCopy1);

        // different types -> returns false
        assertNotEquals(1, addCourseCommand1);

        // null -> returns false
        assertNotEquals(null, addCourseCommand1);

        // different course -> returns false
        assertNotEquals(addCourseCommand1, addCourseCommand2);
    }

}
