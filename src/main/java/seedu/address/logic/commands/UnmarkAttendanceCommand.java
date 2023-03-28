package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;

import java.util.List;

import static java.util.Objects.compare;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class UnmarkAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmark attendance of specified person\n"
            + "Parameters: SESSION INDEX " + PREFIX_NAME + "STUDENT_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "John Doe";

    public static final String MESSAGE_SUCCESS = "Unmarked attendance of %1s in  %2s\n";

    public static final String MESSAGE_PERSON_NOT_FOUND = "person specified cannot be found";

    private Name personName;
    private Index index;

    public UnmarkAttendanceCommand(Index index, Name personName) {
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

        if (!sessionToEdit.contains(personName.fullName)) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        //unmark student in session
        editedSession.markStudentAbsent(personName.fullName);
        model.setSession(sessionToEdit, editedSession);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, personName, sessionToEdit.getName()));

    }
}
