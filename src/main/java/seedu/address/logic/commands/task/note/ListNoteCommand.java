package seedu.address.logic.commands.task.note;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.todo.Note;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_NOTES;

/**
 * Lists all persons in the address book to the user.
 */
public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "list note";

    public static final String MESSAGE_SUCCESS = "Listed all notes";
    public static final String MESSAGE_NO_APPLICATIONS = "No note at the moment";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        List<Note> lastShownList = model.getFilteredNoteList();
        if (lastShownList.size() > 0) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_APPLICATIONS);
        }
    }
}
