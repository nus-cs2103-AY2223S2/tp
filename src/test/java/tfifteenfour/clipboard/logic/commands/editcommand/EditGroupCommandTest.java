package tfifteenfour.clipboard.logic.commands.editcommand;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class EditGroupCommandTest {
    private Model model;
    private Group originalGroup;
    private Group newGroup;
    private Index index;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.GROUP_PAGE);
    }

    @Test
    public void execute_validIndexAndNewGroupName_success() {
        Group groupToEdit = model.getCurrentSelection().getSelectedGroup();
        Group editedGroup = new Group("NewGroup");

        EditGroupCommand editGroupCommand = new EditGroupCommand(INDEX_FIRST, editedGroup);
        String expectedMessage = String.format(EditGroupCommand.MESSAGE_SUCCESS, groupToEdit, editedGroup);
        Model expectedModel = model.copy();
        Course selectedCourse = expectedModel.getCurrentSelection().getSelectedCourse();
        selectedCourse.setGroup(selectedCourse.getUnmodifiableFilteredGroupList().get(0), editedGroup);
        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicateGroup_throwsCommandException() {
        Group firstGroup = model.getCurrentSelection().getSelectedCourse()
                .getUnmodifiableGroupList()
                .get(INDEX_FIRST.getZeroBased());
        EditGroupCommand editGroupCommand = new EditGroupCommand(INDEX_SECOND, firstGroup);

        assertThrows(CommandException.class, () -> editGroupCommand.execute(model));
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Group editedGroup = new Group("NewGroup");

        EditGroupCommand editGroupCommand = new EditGroupCommand(INDEX_OUT_OF_BOUND, editedGroup);

        assertThrows(CommandException.class, () -> editGroupCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        model.getCurrentSelection().setCurrentPage(PageType.STUDENT_PAGE);
        EditGroupCommand editGroupCommand = new EditGroupCommand(index, newGroup);

        assertThrows(CommandException.class, () -> editGroupCommand.execute(model));
    }
}
