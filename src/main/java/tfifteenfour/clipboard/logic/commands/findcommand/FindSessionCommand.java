package tfifteenfour.clipboard.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.predicates.SessionNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.ShowAllListedPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;

/**
 * Finds sessions in the selected group in the model that contains any of the specified keywords.
 */
public class FindSessionCommand extends FindCommand {
    public static final String COMMAND_TYPE_WORD = "session";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + ": Finds a session. "
            + "Parameters: "
            + "SESSION_SEARCH_TERM\n"
            + "Example: " + COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + " " + "SESSON1 ";

    public static final String MESSAGE_SUCCESS = "Found %1$s results";
    private final SessionNameContainsPredicate predicate;
    private final CurrentSelection currentSelection;

    /**
     * Creates a FindSessionCommand to find the specified sessions with the given predicate.
     * @param predicate The predicate to filter sessions with.
     * @param currentSelection The current selection of the LogicManager.
     */
    public FindSessionCommand(SessionNameContainsPredicate predicate, CurrentSelection currentSelection) {
        this.predicate = predicate;
        this.currentSelection = currentSelection;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Group selectedGroup = currentSelection.getSelectedGroup();
        selectedGroup.updateFilteredSessions(predicate);
        int filteredSize = selectedGroup.getUnmodifiableFilteredSessionList().size();

        if (filteredSize == 0) {
            ShowAllListedPredicate.resetSessionsFilter(currentSelection);
        }

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, filteredSize), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindSessionCommand // instanceof handles nulls
                && predicate.equals(((FindSessionCommand) other).predicate));
    }
}
