package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.util.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.util.TaskBuilder;

public class IndexCommandTest {

    private Model model;
    private OfficeConnectModel officeConnectModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        officeConnectModel = new OfficeConnectModel();
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index invalidPersonIndex = Index.fromZeroBased(model.getFilteredPersonList().size() + 1);
        IndexCommand command = new IndexCommand(invalidPersonIndex, IndexCommand.COMMAND_WORD_PERSON);

        assertThrows(CommandException.class, () -> command.execute(model, officeConnectModel),
            Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index invalidTaskIndex = Index.fromZeroBased(officeConnectModel
            .getTaskModelManagerFilteredItemList().size() + 1);
        IndexCommand command = new IndexCommand(invalidTaskIndex, IndexCommand.COMMAND_WORD_TASK);

        assertThrows(CommandException.class, () -> command.execute(model, officeConnectModel),
            Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validPersonIndex_focusesOnPerson() throws CommandException {
        Index validPersonIndex = Index.fromZeroBased(0);
        IndexCommand command = new IndexCommand(validPersonIndex, IndexCommand.COMMAND_WORD_PERSON);

        Person personToFocus = model.getFilteredPersonList().get(validPersonIndex.getZeroBased());
        CommandResult result = command.execute(model, officeConnectModel);

        assertEquals("Focus on person : " + personToFocus.getName(), result.getFeedbackToUser());
    }

    @Test
    public void execute_validTaskIndex_focusesOnTask() throws CommandException {
        // Add a sample task to the officeConnectModel
        Task sampleTask = TaskBuilder.ofRandomTask();
        officeConnectModel.addTaskModelManagerItem(sampleTask);

        Index validTaskIndex = Index.fromZeroBased(0);
        IndexCommand command = new IndexCommand(validTaskIndex, IndexCommand.COMMAND_WORD_TASK);

        Task taskToFocus = officeConnectModel.getTaskModelManagerFilteredItemList().get(validTaskIndex.getZeroBased());
        CommandResult result = command.execute(model, officeConnectModel);

        assertEquals("Focus on task : " + taskToFocus.getTitle(), result.getFeedbackToUser());
    }
}
