package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.task.Task;

/**
 * Lists all tasks in the address book to the user.
 */
public class ListTaskCommand extends Command {
    public static final String COMMAND_WORD = "listtask";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    @Override
    public CommandResult execute(Model model, OfficeConnectModel officeConnectModel) {
        RepositoryModelManager<Task> taskModelManager = officeConnectModel.getTaskModelManager();
        taskModelManager.updateFilteredItemList(OfficeConnectModel.PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
