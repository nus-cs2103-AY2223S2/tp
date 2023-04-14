package tfifteenfour.clipboard.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;

/**
 * Adds a new session to the selected group.
 */
public class AddSessionCommand extends AddCommand {
    public static final String COMMAND_TYPE_WORD = "session";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + ": Adds a session to the selected group. "
            + "Parameters: "
            + "SESSION_NAME\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE_WORD
            + " " + "Tutorial1\n"
            + "Note: Whitespaces are not allowed in the session name";

    public static final String MESSAGE_SUCCESS = "New session added in %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_SESSION = "This session already exists in the course";
    public static final String MESSAGE_WRONG_PAGE = "Wrong page. Navigate to session page to add session";

    private final Session sessionToAdd;

    /**
     * Creates an AddSessionCommand object to add a session.
     * @param session The session to be added.
     */
    public AddSessionCommand(Session session) {
        this.sessionToAdd = session;
    }

    /**
     * Executes the AddSessionCommand to add a session to the selected group.
     *
     * @param model The model of the application.
     * @return The result of the command execution.
     * @throws CommandException If the session already exists in the course or if the user is not on the session page.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentSelection currentSelection = model.getCurrentSelection();

        if (currentSelection.getCurrentPage() != PageType.SESSION_PAGE) {
            throw new CommandException(MESSAGE_WRONG_PAGE);
        }

        Group targetGroup = currentSelection.getSelectedGroup();
        if (targetGroup.hasSession(sessionToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }

        targetGroup.addSession(sessionToAdd);
        System.out.println(targetGroup.getUnmodifiableSessionList());
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, targetGroup, sessionToAdd), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSessionCommand // instanceof handles nulls
                && sessionToAdd.equals(((AddSessionCommand) other).sessionToAdd));
    }
}
