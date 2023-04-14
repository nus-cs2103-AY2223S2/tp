package tfifteenfour.clipboard.logic.commands.findcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.predicates.GroupNameContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.testutil.TypicalModel;

class FindGroupCommandTest {
    private Model model;
    private Model expectedModel;
    private CurrentSelection actualSelection;
    private GroupNameContainsPredicate predicate;
    private FindGroupCommand findGroupCommand;


    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);

        predicate = new GroupNameContainsPredicate(new String[] {"T"});
        findGroupCommand = new FindGroupCommand(predicate, actualSelection);
    }

    @Test
    public void execute_validGroupName_success() {
        String expectedMessage = String.format(FindGroupCommand.MESSAGE_SUCCESS, 3);
        expectedModel.getCurrentSelection().getSelectedCourse().updateFilteredGroups(predicate);

        assertCommandSuccess(findGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noGroupFound_success() {
        GroupNameContainsPredicate noGroupFoundPredicate = new GroupNameContainsPredicate(new String[]{"not a group "
                + "name"});
        FindGroupCommand findGroupCommand = new FindGroupCommand(noGroupFoundPredicate, actualSelection);
        String expectedMessage = String.format(FindGroupCommand.MESSAGE_SUCCESS, 0);
        expectedModel.getCurrentSelection().getSelectedCourse().updateFilteredGroups(noGroupFoundPredicate);

        assertCommandSuccess(findGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(findGroupCommand, findGroupCommand);

        // same values -> returns true
        GroupNameContainsPredicate samePredicate = new GroupNameContainsPredicate(new String[]{"T"});
        FindGroupCommand findGroupCommandCopy = new FindGroupCommand(samePredicate, actualSelection);
        assertEquals(findGroupCommand, findGroupCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findGroupCommand);

        // null -> returns false
        assertNotEquals(null, findGroupCommand);

        // different predicates -> returns false
        GroupNameContainsPredicate differentPredicate = new GroupNameContainsPredicate(new String[]{"T16-4"});
        FindGroupCommand findGroupCommandDifferentPredicate = new FindGroupCommand(differentPredicate, actualSelection);
        assertNotEquals(findGroupCommand, findGroupCommandDifferentPredicate);
    }
}
