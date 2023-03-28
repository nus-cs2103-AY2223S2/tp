package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.TaskBookModel.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.model.Model;
import seedu.address.model.TaskBookModel;

/**
 * Lists statistics of all persons in the address book to the user.
 */
public class ReviewCommand extends Command {

    public static final String COMMAND_WORD = "review";

    public static final String MESSAGE_SUCCESS = "Listed statistics for all persons";


    @Override
    public CommandResult execute(Model model, TaskBookModel taskBookModel) {
        requireAllNonNull(model, taskBookModel);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        taskBookModel.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
