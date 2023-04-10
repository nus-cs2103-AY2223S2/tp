package tfifteenfour.clipboard.logic.commands.findcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.predicates.CourseNameContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.testutil.TypicalModel;

class FindCourseCommandTest {
    private Model model;
    private Model expectedModel;
    private CurrentSelection actualSelection;
    private CourseNameContainsPredicate predicate;


    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
    }

    @Test
    public void execute_zeroKeywords_noCourseFound() {
        String expectedMessage = String.format(FindCourseCommand.MESSAGE_SUCCESS, 0);
        predicate = new CourseNameContainsPredicate(new String[] {});
        FindCourseCommand command = new FindCourseCommand(predicate);
        expectedModel.getRoster().updateFilteredCourses(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        CourseNameContainsPredicate firstPredicate =
                new CourseNameContainsPredicate(new String[] {"CS2103T"});
        CourseNameContainsPredicate secondPredicate =
                new CourseNameContainsPredicate(new String[] {"CS2101"});

        FindCourseCommand findFirstCommand = new FindCourseCommand(firstPredicate);
        FindCourseCommand findSecondCommand = new FindCourseCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCourseCommand findFirstCommandCopy = new FindCourseCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different course -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }
}
