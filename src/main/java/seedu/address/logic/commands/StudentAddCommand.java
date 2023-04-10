package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;

/**
 * Command that adds a student to a session
 */
public class StudentAddCommand extends Command {
    public static final String COMMAND_WORD = "student-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an athlete (by index) to a specified session (by name).\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "SESSION_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "hall";

    public static final String SESSION_ADD_PERSON_SUCCESS = "Added athlete %1$s to session %2$s.";
    public static final String SESSION_NOT_FOUND_FAILURE = "Session %1$s cannot be found. "
            + "Here is the list of existing sessions:\n %2$s";
    public static final String STUDENT_ALREADY_ADDED_FAILURE = "Student already belongs to %1$s.";

    private SessionName sessionName;
    private Index index;
    /**
     * Creates a new {@code StudentAddCommand} to add a student to a session.
     * @param index Index of the student in the student list to add.
     * @param sessionName Name of the session to add the student to.
     */
    public StudentAddCommand(Index index, SessionName sessionName) {
        this.index = index;
        this.sessionName = sessionName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person studentToAdd = lastShownList.get(index.getZeroBased());

        if (!model.hasSessionName(sessionName)) {
            throw new CommandException(String.format(
                    SESSION_NOT_FOUND_FAILURE,
                    sessionName,
                    model.getAddressBook().getSessionList()
            ));
        }
        //Find session
        Session sessionToBeAddedTo = model.getSessionFromName(sessionName);

        if (sessionToBeAddedTo.contains(studentToAdd
                .getName().formattedName)) {
            throw new CommandException(
                    String.format(
                            STUDENT_ALREADY_ADDED_FAILURE,
                            sessionName
                    ));
        }
        model.addPersonToSession(studentToAdd, sessionToBeAddedTo);
        model.commitAddressBook();
        return new CommandResult(String.format(SESSION_ADD_PERSON_SUCCESS, studentToAdd.getName(), sessionToBeAddedTo));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentAddCommand // instanceof handles nulls
                && index.equals(((StudentAddCommand) other).index)
                && sessionName.equals(((StudentAddCommand) other).sessionName)); // state check
    }
}
