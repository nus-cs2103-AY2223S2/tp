package tfifteenfour.clipboard.logic.commands.editcommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;

/**
 * Edits the name of a session in the gorup.
 */
public class EditSessionCommand extends EditCommand {
    public static final String COMMAND_TYPE_WORD = "session";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Edits a session name."
            + " Parameters: INDEX NEW_SESSION_NAME\n"
            + "Note: INDEX must be a positive integer\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " 1 Tutorial1";

    public static final String MESSAGE_SUCCESS = "Edited session: %1$s to %2$s";
    public static final String MESSAGE_DUPLICATE_SESSION = "This session already exists in the course";

    private final Index index;
    private final Session newSession;

    /**
     * Constructs an {@code EditSessionCommand} with the specified index and new session.
     * @param index The index of the session to be edited.
     * @param newSession The new session to be set.
     */
    public EditSessionCommand(Index index, Session newSession) {
        this.index = index;
        this.newSession = newSession;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.SESSION_PAGE) {
            throw new CommandException("Wrong page. Navigate to session page to edit session");
        }

        Group selectedGroup = currentSelection.getSelectedGroup();
        List<Session> lastShownList = selectedGroup.getUnmodifiableFilteredSessionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        } else if (selectedGroup.hasSession(newSession)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }

        Session sessionToEdit = lastShownList.get(index.getZeroBased());
        newSession.setAttendance(sessionToEdit.getAttendance());

        selectedGroup.setSession(sessionToEdit, newSession);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, sessionToEdit, newSession), willModifyState);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EditSessionCommand)) {
            return false;
        }
        EditSessionCommand other = (EditSessionCommand) obj;
        return index.equals(other.index) && newSession.equals(other.newSession);
    }

}
