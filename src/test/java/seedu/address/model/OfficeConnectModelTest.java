package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

class OfficeConnectModelTest {
    private OfficeConnectModel officeModel;
    private Task testTask;
    private AssignTask testAssignTask;

    @BeforeEach
    void setUp() {
        officeModel = new OfficeConnectModel();
        Person testPerson = new PersonBuilder().build();
        testTask = TaskBuilder.ofRandomTask();
        testAssignTask = new AssignTask(testPerson.getId(), testTask.getId());

    }

    @Test
    void addTask_validTask_returnsTrue() {
        officeModel.getTaskModelManager().addItem(testTask);
        assertTrue(officeModel.getTaskModelManager().hasItem(testTask));
    }

    @Test
    void removeTask_validTask_returnsTrue() {
        officeModel.getTaskModelManager().addItem(testTask);
        officeModel.getTaskModelManager().deleteItem(testTask);
        assertFalse(officeModel.getTaskModelManager().hasItem(testTask));
    }

    @Test
    void addPersonTask_validPersonTask_returnsTrue() {
        officeModel.getAssignTaskModelManager().addItem(testAssignTask);
        assertTrue(officeModel.getAssignTaskModelManager().hasItem(testAssignTask));
    }

    @Test
    void removePersonTask_validPersonTask_returnsTrue() {
        officeModel.getAssignTaskModelManager().addItem(testAssignTask);
        officeModel.getAssignTaskModelManager().deleteItem(testAssignTask);

        assertFalse(officeModel.getAssignTaskModelManager().hasItem(testAssignTask));
    }


}
