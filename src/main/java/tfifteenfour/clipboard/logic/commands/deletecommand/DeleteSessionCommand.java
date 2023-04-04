package tfifteenfour.clipboard.logic.commands.deletecommand;

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
 * Deletes a session from the roster.
 */
public class DeleteSessionCommand extends DeleteCommand {
    public static final String COMMAND_TYPE_WORD = "session";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Deletes a specified session."
            + "Parameters: "
            + "INDEX\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted session in %1$s: %2$s";

    private final Index index;

    /**
     * Creates a {@code DeleteSessionCommand} to delete the session at the specified {@code Index}.
     *
     * @param index Index of the session to be deleted.
     */
    public DeleteSessionCommand(Index index) {
        this.index = index;
    }

    /**
     * Deletes the session specified by the index from the selected group in the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of the command execution.
     * @throws CommandException If the selected page is not a session page or if the index is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.SESSION_PAGE) {
            throw new CommandException("Wrong page. Navigate to session page to delete session");
        }

        Group selectedGroup = currentSelection.getSelectedGroup();
        List<Session> lastShownList = selectedGroup.getUnmodifiableFilteredSessionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        Session sessionToDelete = lastShownList.get(index.getZeroBased());
        selectedGroup.deleteSession(sessionToDelete);

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, selectedGroup, sessionToDelete), willModifyState);
    }
}
