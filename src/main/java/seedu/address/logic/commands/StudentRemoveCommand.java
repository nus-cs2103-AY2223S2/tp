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
 * Command to remove a student from a session
 */
public class StudentRemoveCommand extends Command {
    public static final String COMMAND_WORD = "student-remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes an athlete (by index) from a specified session (by name).\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "SESSION_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "hall";

    public static final String SESSION_REMOVE_PERSON_SUCCESS = "Removed athlete %1$s from session %2$s.";
    public static final String SESSION_NOT_FOUND_FAILURE = "Session %1$s cannot be found.";
    public static final String STUDENT_NOT_FOUND_FAILURE = "Athlete cannot be found.";

    private SessionName sessionName;
    private Index index;
    /**
     * Represents a command to remove a student from a session.
     */
    public StudentRemoveCommand(Index index, SessionName sessionName) {
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

        Person studentToRemove = lastShownList.get(index.getZeroBased());

        if (!model.hasSessionName(sessionName)) {
            throw new CommandException(String.format(
                    SESSION_NOT_FOUND_FAILURE,
                    sessionName
            ));
        }

        //Find session
        Session session = model.getSessionFromName(sessionName);

        if (!session.contains(studentToRemove
                .getName().formattedName)) {
            throw new CommandException(STUDENT_NOT_FOUND_FAILURE);
        }

        model.removePersonFromSession(studentToRemove, session);
        model.commitAddressBook();
        return new CommandResult(String.format(SESSION_REMOVE_PERSON_SUCCESS, studentToRemove.getName(), session));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentRemoveCommand // instanceof handles nulls
                && index.equals(((StudentRemoveCommand) other).index)
                && sessionName.equals(((StudentRemoveCommand) other).sessionName)); // state check
    }
}
