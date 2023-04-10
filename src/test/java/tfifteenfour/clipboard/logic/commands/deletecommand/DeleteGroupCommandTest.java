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
import tfifteenfour.clipboard.testutil.TypicalModel;

class DeleteGroupCommandTest {
    private Model model;
    private Course selectedCourse;
    private Group selectedGroup;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_STUDENT_PAGE);
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
    }

    @Test
    public void execute_validIndex_success() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(INDEX_FIRST);

        //Group groupToDelete = selectedCourse.getUnmodifiableGroupList().get(INDEX_FIRST.getZeroBased());
        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_SUCCESS, selectedCourse, selectedGroup);
        Model expectedModel = model.copy();
        Course expectedSelectedCourse = expectedModel.getCurrentSelection().getSelectedCourse();
        expectedSelectedCourse.deleteGroup(
                expectedSelectedCourse.getUnmodifiableGroupList().get(INDEX_FIRST.getZeroBased()));

        assertCommandSuccess(deleteGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> deleteGroupCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> deleteGroupCommand.execute(model));
    }

}
