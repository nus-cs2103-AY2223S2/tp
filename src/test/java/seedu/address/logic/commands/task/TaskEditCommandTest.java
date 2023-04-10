package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalReadings.getTypicalFullReadingLevels;
import static seedu.address.testutil.TypicalTanks.TYPICAL_TANK_1_STRING;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.testutil.TypicalTasks;

class TaskEditCommandTest {

    @Test
    public void constructor_nullIndex_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskEditCommand(null,
                new TaskEditCommand.EditTaskDescriptor()));
    }

    @Test
    public void constructor_nullEditDescriptor_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskEditCommand(Index.fromOneBased(1),
                null));
    }

    @Test
    public void execute_editTaskOneDescription_success() {
        //attempt to edit task index 0's description
        Index oneIndex = Index.fromOneBased(1);
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        TaskEditCommand.EditTaskDescriptor descriptor = new TaskEditCommand.EditTaskDescriptor();
        descriptor.setDescription(new Description("This is a new description"));
        TaskEditCommand command = new TaskEditCommand(oneIndex, descriptor);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        //Changed from typical tasks [0]
        Task changedTask = new Task(new Description("This is a new description"),
                new Tank(new TankName(TYPICAL_TANK_1_STRING), new AddressBook(),
                        new UniqueIndividualReadingLevels()), new Priority("medium"));
        expectedModel.setTask(expectedModel.getTaskList().getTaskList().get(0), changedTask);
        String expectedMessage = String.format(TaskEditCommand.MESSAGE_EDIT_TASK_SUCCESS, changedTask);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void createEditedTask_basicTask_success() {
        Task expectedTask = new Task(new Description("clean tank 2"),
                new Tank(new TankName(TYPICAL_TANK_1_STRING), new AddressBook(),
                        new UniqueIndividualReadingLevels()), new Priority("medium"));
        TaskEditCommand.EditTaskDescriptor descriptor = new TaskEditCommand.EditTaskDescriptor();
        descriptor.setDescription(new Description("clean tank 2"));
        Task actualTask = TaskEditCommand.createEditedTask(TypicalTasks.TASK_ONE, descriptor);
        assertEquals(expectedTask, actualTask);
    }


}
