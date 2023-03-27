package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.TypicalPersons.ALICE;
import static seedu.address.model.util.TypicalPersons.BENSON;
import static seedu.address.model.util.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.task.Task;
import seedu.address.model.util.TypicalTasks;

class ListAssignmentTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final OfficeConnectModel officeConnectModel = new OfficeConnectModel();
    private final List<Task> tasks = TypicalTasks.getTypicalTasks();
    private final Task taskA = tasks.get(0);
    private final Task taskB = tasks.get(1);

    @BeforeEach
    public void setUp() {
        officeConnectModel.getTaskModelManager().addItem(taskA);
        officeConnectModel.getTaskModelManager().addItem(taskB);

        AssignTask assignTask1 = new AssignTask(ALICE.getId(), taskA.getId());
        AssignTask assignTask2 = new AssignTask(BENSON.getId(), taskB.getId());
        officeConnectModel.getAssignTaskModelManager().addItem(assignTask1);
        officeConnectModel.getAssignTaskModelManager().addItem(assignTask2);
    }

    @Test
    void execute_personType_assigned() {
        ListAssignment listAssignment = new ListAssignment(ListAssignment.TYPE_PERSON, true);
        CommandResult commandResult = listAssignment.execute(model, officeConnectModel);

        String expectedMessage = String.format(ListAssignment.MESSAGE_SUCCESS, "assign", "person(s)");
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(2, model.getFilteredPersonList().size());
    }

    @Test
    void execute_personType_unassigned() {
        ListAssignment listAssignment = new ListAssignment(ListAssignment.TYPE_PERSON, false);
        CommandResult commandResult = listAssignment.execute(model, officeConnectModel);

        String expectedMessage = String.format(ListAssignment.MESSAGE_SUCCESS, "unassign", "person(s)");
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(model.getAddressBook().getPersonList().size() - 2, model.getFilteredPersonList().size());
    }

    @Test
    void execute_taskType_assigned() {
        ListAssignment listAssignment = new ListAssignment(ListAssignment.TYPE_TASK, true);
        CommandResult commandResult = listAssignment.execute(model, officeConnectModel);

        String expectedMessage = String.format(ListAssignment.MESSAGE_SUCCESS, "assign", "task(s)");
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(2, officeConnectModel.getTaskModelManagerFilteredItemList().size());
    }

    @Test
    void execute_taskType_unassigned() {
        ListAssignment listAssignment = new ListAssignment(ListAssignment.TYPE_TASK, false);
        CommandResult commandResult = listAssignment.execute(model, officeConnectModel);

        String expectedMessage = String.format(ListAssignment.MESSAGE_SUCCESS, "unassign", "task(s)");
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(0, officeConnectModel.getTaskModelManagerFilteredItemList().size());
    }
    @Test
    void execute_noType_assigned() {
        ListAssignment listAssignment = new ListAssignment("", true);
        CommandResult commandResult = listAssignment.execute(model, officeConnectModel);

        String expectedMessage = String.format(ListAssignment.MESSAGE_SUCCESS, "assign", "person(s) and task(s)");
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(2, model.getFilteredPersonList().size());
        assertEquals(2, officeConnectModel.getTaskModelManagerFilteredItemList().size());
    }

}
