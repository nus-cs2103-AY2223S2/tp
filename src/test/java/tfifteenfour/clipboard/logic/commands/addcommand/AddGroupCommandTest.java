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
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.testutil.TypicalModel;

class AddGroupCommandTest {
    private Model model;
    private Model expectedModel;
    private Course selectedCourse;
    private Group selectedGroup;

    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        selectedCourse = model.getCurrentSelection().getSelectedCourse();
        selectedGroup = model.getCurrentSelection().getSelectedGroup();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
    }

    @Test
    public void execute_groupAcceptedByModel_addSuccessful() {
        Group validGroup = new Group("New Group");

        AddGroupCommand addGroupCommand = new AddGroupCommand(validGroup);
        String expectedMessage = String.format(AddGroupCommand.MESSAGE_SUCCESS, selectedCourse, validGroup);

        Course expectedSelectedCourse = expectedModel.getCurrentSelection().getSelectedCourse();
        expectedSelectedCourse.addGroup(validGroup);

        assertCommandSuccess(addGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);

        Group validGroup = new Group("New Group");
        AddGroupCommand addGroupCommand = new AddGroupCommand(validGroup);

        assertThrows(CommandException.class, AddGroupCommand.MESSAGE_WRONG_PAGE,
                () -> addGroupCommand.execute(model));
    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() {
        Group existingGroup = selectedGroup;

        AddGroupCommand commandCopy = new AddGroupCommand(new Group(existingGroup.getGroupName()));
        assertThrows(CommandException.class, AddGroupCommand.MESSAGE_DUPLICATE_GROUP,
                () -> commandCopy.execute(model));
    }

    @Test
    public void equals() {

        Group validGroup = new Group("New Group");
        Group validGroup2 = new Group("New Group2");
        AddGroupCommand addGroupCommand1 = new AddGroupCommand(validGroup);
        AddGroupCommand addGroupCommand2 = new AddGroupCommand(validGroup2);

        // same object -> returns true
        assertEquals(addGroupCommand1, addGroupCommand1);

        // same values -> returns true
        AddGroupCommand addGroupCommandCopy1 = new AddGroupCommand(validGroup);
        assertEquals(addGroupCommand1, addGroupCommandCopy1);

        // different types -> returns false
        assertNotEquals(1, addGroupCommand1);

        // null -> returns false
        assertNotEquals(null, addGroupCommand1);

        // different Group -> returns false
        assertNotEquals(addGroupCommand1, addGroupCommand2);
    }

}
