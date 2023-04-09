package tfifteenfour.clipboard.logic.commands.findcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.predicates.TaskNameContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.testutil.TypicalModel;

class FindTaskCommandTest {
    private Model model;
    private Model expectedModel;
    private Session selectedSession;
    private Task selectedTask;
    private CurrentSelection actualSelection;
    private TaskNameContainsPredicate predicate;



    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        selectedSession = model.getCurrentSelection().getSelectedSession();
        selectedTask = model.getCurrentSelection().getSelectedTask();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        predicate = new TaskNameContainsPredicate(new String[] {selectedTask.getTaskName()});
    }

    @Test
    public void execute_zeroResultsFound() {
        predicate = new TaskNameContainsPredicate(new String[]{"NonexistentTask"});
        FindTaskCommand command = new FindTaskCommand(predicate, actualSelection);

        expectedModel.getCurrentSelection().getSelectedGroup().updateFilteredTasks(predicate);
        assertCommandSuccess(command, model, String.format(FindStudentCommand.MESSAGE_SUCCESS, 0), expectedModel);
    }

    @Test
    public void execute_multipleResultsFound() {
        predicate = new TaskNameContainsPredicate(new String[]{"1"});
        FindTaskCommand command = new FindTaskCommand(predicate, actualSelection);

        int expectedSize = expectedModel
                .getCurrentSelection()
                .getSelectedGroup()
                .getModifiableTaskList()
                .filtered(predicate).size();

        expectedModel.getCurrentSelection().getSelectedGroup().updateFilteredTasks(predicate);
        assertCommandSuccess(command, model, String.format(FindStudentCommand.MESSAGE_SUCCESS, expectedSize), expectedModel);
    }

    @Test
    public void execute_oneResultFound() {
        FindTaskCommand command = new FindTaskCommand(predicate, actualSelection);

        int expectedSize = expectedModel
                .getCurrentSelection()
                .getSelectedGroup()
                .getModifiableTaskList()
                .filtered(predicate).size();

        expectedModel.getCurrentSelection().getSelectedGroup().updateFilteredTasks(predicate);
        assertCommandSuccess(command, model, String.format(FindStudentCommand.MESSAGE_SUCCESS, expectedSize), expectedModel);
    }

    @Test
    public void equals() {
        TaskNameContainsPredicate predicate1 = predicate;
        FindTaskCommand findTaskCommand1 = new FindTaskCommand(predicate1, actualSelection);

        TaskNameContainsPredicate predicate2 =
                new TaskNameContainsPredicate(new String[]{"Task predicate 2"});
        FindTaskCommand findTaskCommand2 = new FindTaskCommand(predicate2, actualSelection);

        // same object -> returns true
        assertEquals(findTaskCommand1, findTaskCommand1);

        // same values -> returns true
        FindTaskCommand findTaskCommand1Copy = new FindTaskCommand(predicate1, actualSelection);
        assertEquals(findTaskCommand1, findTaskCommand1Copy);

        // different types -> returns false
        assertNotEquals(1, findTaskCommand1);

        // null -> returns false
        assertNotEquals(null, findTaskCommand1);

        // different search term -> returns false
        assertNotEquals(findTaskCommand1, findTaskCommand2);
    }
}
