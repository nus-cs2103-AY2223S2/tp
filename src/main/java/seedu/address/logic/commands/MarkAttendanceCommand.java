package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.session.Session;
/**
 * Represents a command to mark attendance of a student in a specified session.
 */
public class MarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks an athlete (by name) as present for a specified session (by index).\n"
            + "Parameters: SESSION_INDEX " + PREFIX_NAME + "ATHLETE_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "John Doe";

    public static final String MESSAGE_SUCCESS = "Marked attendance of %1s in %2s.\n";

    public static final String MESSAGE_PERSON_NOT_FOUND = "Athlete specified cannot be found.";

    private Name personName;
    private Index index;
    /**
     * Constructs a MarkAttendanceCommand with the given session index and person name.
     *
     * @param index Index of the session in the filtered session list to mark attendance for.
     * @param personName Name of the person to mark attendance for.
     */
    public MarkAttendanceCommand(Index index, Name personName) {
        this.index = index;
        this.personName = personName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Session> lastShownList = model.getFilteredSessionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        Session sessionToEdit = lastShownList.get(index.getZeroBased());

        Session editedSession = sessionToEdit.copy();

        if (!sessionToEdit.contains(personName.formattedName)) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        //mark student in session
        editedSession.markStudentPresent(personName.formattedName);
        model.setSession(sessionToEdit, editedSession);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, personName, sessionToEdit.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAttendanceCommand // instanceof handles nulls
                && index.equals(((MarkAttendanceCommand) other).index)
                && personName.equals(((MarkAttendanceCommand) other).personName)); // state check
    }
}
