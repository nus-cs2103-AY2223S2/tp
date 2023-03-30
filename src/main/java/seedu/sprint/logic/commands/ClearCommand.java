package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.Model;


/**
 * Clears the sprint book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Your Internship Book has been cleared!\n"
            + "If this was a mistake, you can enter the undo command.";;

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        requireNonNull(model);
        model.setInternshipBook(new InternshipBook());
        model.commitInternshipBookChange();
        commandHistory.setLastCommandAsModify();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
