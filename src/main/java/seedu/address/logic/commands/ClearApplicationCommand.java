package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.ApplicationModel;
import seedu.address.model.InternshipBook;


/**
 * Clears the address book.
 */
public class ClearApplicationCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Your Internship Book has been cleared!\n"
            + "If this was a mistake, you can enter the undo command.";;

    @Override
    public CommandResult execute(ApplicationModel model, CommandHistory commandHistory) {
        requireNonNull(model);
        model.setInternshipBook(new InternshipBook());
        model.commitInternshipBookChange();
        commandHistory.setLastCommandAsModify();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
