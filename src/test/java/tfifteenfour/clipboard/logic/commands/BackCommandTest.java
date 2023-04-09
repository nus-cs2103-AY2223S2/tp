package tfifteenfour.clipboard.logic.commands;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.testutil.TypicalModel;

class BackCommandTest {
    private Model model;
    private Course selectedCourse;
    private Group selectedGroup;

    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.TASK_STUDENT_PAGE);
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();

        actualSelection = this.model.getCurrentSelection();
    }

    @Test
    public void execute_validCommandOnTaskStudentPage_returnsCommandResult() throws CommandException {
        actualSelection.setCurrentPage(PageType.TASK_STUDENT_PAGE);
        BackCommand backCommand = new BackCommand(actualSelection);

        String expectedMessage = String.format(BackCommand.MESSAGE_SUCCESS_BACK_TO_TASK, selectedGroup);
        Model expectedModel = model.copy();
        expectedModel.getCurrentSelection().navigateBackFromTaskStudentPage();

        assertCommandSuccess(backCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCommandOnSessionPage_success() {
        actualSelection.setCurrentPage(PageType.SESSION_PAGE);

        BackCommand backCommand = new BackCommand(actualSelection);

        String expectedMessage = String.format(BackCommand.MESSAGE_SUCCESS_BACK_TO_GROUP, selectedCourse);
        Model expectedModel = model.copy();
        expectedModel.getCurrentSelection().navigateBackFromSessionPage();

        assertCommandSuccess(backCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCommandOnGroupPage_success() throws CommandException {
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);

        BackCommand backCommand = new BackCommand(actualSelection);

        String expectedMessage = String.format(BackCommand.MESSAGE_SUCCESS_BACK_TO_COURSE, selectedCourse);
        Model expectedModel = model.copy();
        expectedModel.getCurrentSelection().navigateBackFromGroupPage();

        assertCommandSuccess(backCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCommandOnTaskPage_success() {
        actualSelection.setCurrentPage(PageType.TASK_PAGE);

        BackCommand backCommand = new BackCommand(actualSelection);

        String expectedMessage = String.format(BackCommand.MESSAGE_SUCCESS_BACK_TO_GROUP, selectedCourse);
        Model expectedModel = model.copy();
        expectedModel.getCurrentSelection().navigateBackFromTaskPage();

        assertCommandSuccess(backCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCommandOnSessionStudentPage_success() {
        actualSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        BackCommand backCommand = new BackCommand(actualSelection);

        String expectedMessage = String.format(BackCommand.MESSAGE_SUCCESS_BACK_TO_SESSION, selectedGroup);
        Model expectedModel = model.copy();
        expectedModel.getCurrentSelection().navigateBackFromSessionStudentPage();

        assertCommandSuccess(backCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCommandOnStudentPage_success() {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
        BackCommand backCommand = new BackCommand(actualSelection);

        String expectedMessage = String.format(BackCommand.MESSAGE_SUCCESS_BACK_TO_GROUP, selectedCourse);
        Model expectedModel = model.copy();
        expectedModel.getCurrentSelection().navigateBackFromStudentPage();

        assertCommandSuccess(backCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCommandOnCoursePage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);

        BackCommand backCommand = new BackCommand(actualSelection);

        assertThrows(CommandException.class, () -> backCommand.execute(model));
    }

    @Test
    public void execute_invalidPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.INVALID_PAGE);

        BackCommand backCommand = new BackCommand(actualSelection);

        assertThrows(CommandException.class, () -> backCommand.execute(model));
    }
}

