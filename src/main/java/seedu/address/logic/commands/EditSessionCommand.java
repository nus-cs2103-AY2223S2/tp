package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.Location;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;


/**
 * Edits the details of an existing session in the address book.
 */
public class EditSessionCommand extends Command {

    public static final String COMMAND_WORD = "session-edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the session identified "
            + "by the index number used in the displayed session list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_SESSION + "SESSION] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Hall "
            + PREFIX_SESSION + "10-03-2022 10:00 to 10-03-2022 11:00 "
            + PREFIX_LOCATION + "MPSH";

    public static final String MESSAGE_EDIT_SESSION_SUCCESS = "Edited Session: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SESSION_TIME = "This session overlaps with an existing session.";
    public static final String MESSAGE_DUPLICATE_SESSION = "This session already exists in the address book.";


    private final Index index;
    private final EditSessionDescriptor editSessionDescriptor;

    /**
     * @param index of the session in the filtered session list to edit
     * @param editSessionDescriptor details to edit the session with
     */
    public EditSessionCommand(Index index, EditSessionDescriptor editSessionDescriptor) {
        requireNonNull(index);
        requireNonNull(editSessionDescriptor);

        this.index = index;
        this.editSessionDescriptor = new EditSessionDescriptor(editSessionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Session> lastShownList = model.getFilteredSessionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        Session sessionToEdit = lastShownList.get(index.getZeroBased());

        Session editedSession = createEditedSession(sessionToEdit, editSessionDescriptor);

        if (!sessionToEdit.isSameSession(editedSession)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }

        if (sessionToEdit.overlaps(editedSession)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION_TIME);
        }

        model.setSession(sessionToEdit, editedSession);
        model.updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_EDIT_SESSION_SUCCESS, editedSession));
    }

    /**
     * Creates and returns a {@code Session} with the details of {@code sessionToEdit}
     * edited with {@code editSessionDescriptor}.
     */
    private static Session createEditedSession(Session sessionToEdit, EditSessionDescriptor editSessionDescriptor) {
        assert sessionToEdit != null;
        SessionName updatedSessionName = editSessionDescriptor.getSessionName().orElse(
                sessionToEdit.getSessionName());
        String updatedSessionStartDateTime = editSessionDescriptor.getStartDateTime().orElse(
                sessionToEdit.getStartDateTime());
        String updatedSessionEndDateTime = editSessionDescriptor.getEndDateTime().orElse(
                sessionToEdit.getEndDateTime());
        Location updatedLocation = editSessionDescriptor.getLocation().orElse(
                sessionToEdit.getLocation());

        return new Session(updatedSessionStartDateTime, updatedSessionEndDateTime, updatedSessionName, updatedLocation);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSessionCommand)) {
            return false;
        }

        // state check
        EditSessionCommand e = (EditSessionCommand) other;
        return index.equals(e.index)
                && editSessionDescriptor.equals(e.editSessionDescriptor);
    }

    /**
     * Stores the details to edit the Session with. Each non-empty field value will replace the
     * corresponding field value of the Session.
     */
    public static class EditSessionDescriptor {
        private SessionName sessionName;
        private String startDateTime;
        private String endDateTime;
        private Location location;

        public EditSessionDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy is used internally.
         */
        public EditSessionDescriptor(EditSessionDescriptor toCopy) {
            setSessionName(toCopy.sessionName);
            setStartDateTime(toCopy.startDateTime);
            setEndDateTime(toCopy.endDateTime);
            setLocation(toCopy.location);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(sessionName, startDateTime, endDateTime, location);
        }

        public void setSessionName(SessionName sessionName) {
            this.sessionName = sessionName;
        }

        public Optional<SessionName> getSessionName() {
            return Optional.ofNullable(sessionName);
        }

        public void setStartDateTime(String startDateTime) {
            this.startDateTime = startDateTime;
        }

        public void setEndDateTime(String endDateTime) {
            this.endDateTime = endDateTime;
        }

        public Optional<String> getStartDateTime() {
            return Optional.ofNullable(startDateTime);
        }

        public Optional<String> getEndDateTime() {
            return Optional.ofNullable(endDateTime);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSessionDescriptor)) {
                return false;
            }

            // state check
            EditSessionDescriptor e = (EditSessionDescriptor) other;

            return getSessionName().equals(e.getSessionName())
                    && getStartDateTime().equals(e.getStartDateTime())
                    && getEndDateTime().equals(e.getEndDateTime())
                    && getLocation().equals(e.getLocation());
        }
    }
}
