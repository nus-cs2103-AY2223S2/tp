package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.Session;

/**
 * Creates session and adds it to the session list
 */
public class CreateSessionCommand extends Command {
    public static final String COMMAND_WORD = "create-session";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new session to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_SESSION + "SESSION "
            + PREFIX_LOCATION + "LOCATION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Hall "
            + PREFIX_SESSION + "10-03-2022 10:00 to 10-03-2022 11:00 "
            + PREFIX_LOCATION + "Leon Lim Sports Hall Of Champions";

    public static final String MESSAGE_SUCCESS = "New session added: %1$s";
    public static final String MESSAGE_DUPLICATE_SESSION = "This session already exists in the address book";
    public static final String MESSAGE_SESSION_OVERLAP = "This session overlaps with an existing session";

    private final Session toAdd;

    /**
     * Creates an CreateSessionCommand to add the specified {@code Session}
     */
    public CreateSessionCommand(Session session) {
        requireNonNull(session);
        toAdd = session;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSession(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }

        List<Session> overlappingSessions = model.getFilteredSessionList().stream()
                .filter(session -> session.overlaps(toAdd))
                .collect(Collectors.toList());

        if (!overlappingSessions.isEmpty()) {
            throw new CommandException(MESSAGE_SESSION_OVERLAP);
        }

        model.addSession(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateSessionCommand // instanceof handles nulls
                && toAdd.equals(((CreateSessionCommand) other).toAdd));
    }
}
