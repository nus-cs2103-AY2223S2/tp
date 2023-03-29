package seedu.address.logic.commands.task;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_NOTES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TODO;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "list_task";

    public static final String MESSAGE_SUCCESS = "Listed all todos and notes";
    public static final String MESSAGE_NO_APPLICATIONS = "No task (todo and note) at the moment";

    private static final TaskType type = TaskType.BOTH;


    @Override
    public CommandResult execute(Model model) {
        assert model != null;
        model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TODO);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        List<InternshipTodo> lastShownTodoList = model.getFilteredTodoList();
        List<Note> lastShownNoteList = model.getFilteredNoteList();
        if (lastShownTodoList.size() > 0 || lastShownNoteList.size() > 0) {
            return new CommandResult(MESSAGE_SUCCESS, type);
        } else {
            return new CommandResult(MESSAGE_NO_APPLICATIONS, type);
        }
    }
}
