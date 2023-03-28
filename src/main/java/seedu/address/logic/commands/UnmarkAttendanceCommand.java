package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.SessionName;

import static java.util.Objects.requireNonNull;

public class UnmarkAttendanceCommand {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmark attendance of specified person\n"
            + "Parameters: INDEX SESSION_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "hall";

    public static final String MESSAGE_SUCCESS = "Unmarks attendance of person in session\n";

    private SessionName sessionName;
    private Index index;

    public UnmarkAttendanceCommand(Index index, SessionName sessionName) {
        this.index = index;
        this.sessionName = sessionName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortAddressBook(attribute);
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
