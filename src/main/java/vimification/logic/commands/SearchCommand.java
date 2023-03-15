package vimification.logic.commands;

import vimification.commons.core.Messages;
import vimification.model.Model;
import vimification.model.TaskList;
import vimification.model.task.DescriptionContainsKeywordsPredicate;
import static java.util.Objects.requireNonNull;

/**
 * Searches and lists all tasks in the task list whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchCommand extends LogicCommand {

    private final DescriptionContainsKeywordsPredicate predicate;

    public SearchCommand(TaskList taskList, DescriptionContainsKeywordsPredicate predicate) {
        super(taskList);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public CommandResult undo() {
        return new CommandResult(String.format("%s", ""));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)); // state check
    }
}
