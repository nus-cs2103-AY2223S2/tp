package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.StudentRemoveCommand.STUDENT_NOT_FOUND_FAILURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;

/**
 * Command that adds a student to a session
 */
public class StudentAddCommand extends Command {
    public static final String COMMAND_WORD = "student-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a student of index i to a session specified. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "hall";

    public static final String SESSION_ADD_PERSON_SUCCESS = "Added Person: %1$s to Session: %2$s";
    public static final String SESSION_NOT_FOUND_FAILURE = "Session: %1$s cannot be found. "
            + "Here are the list of existing sessions: %2$s";
    public static final String STUDENT_ALREADY_ADDED_FAILURE = "Student already belongs to %1$s";

    private SessionName sessionName;
    private Index index;

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
                .getName().fullName)) {
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
}
