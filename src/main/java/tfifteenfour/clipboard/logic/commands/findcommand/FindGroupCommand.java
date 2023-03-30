package tfifteenfour.clipboard.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.predicates.GroupNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.ShowAllListedPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;

/**
 * Finds a group in the selected course whose name contains any of the specified keywords.
 */
public class FindGroupCommand extends FindCommand {

    public static final String COMMAND_TYPE_WORD = "group";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + ": Finds a group. "
            + "Parameters: "
            + "GROUP_SEARCH_TERM\n"
            + "Example: " + COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + " " + "T15-4 ";

    public static final String MESSAGE_SUCCESS = "Found %1$s results";
    private final GroupNameContainsPredicate predicate;
    private final CurrentSelection currentSelection;


    /**
     * Creates a FindGroupCommand to find the specified group with the given predicate.
     * @param predicate The predicate to filter group with.
     * @param currentSelection The current selection of the LogicManager.
     */
    public FindGroupCommand(GroupNameContainsPredicate predicate, CurrentSelection currentSelection) {
        this.predicate = predicate;
        this.currentSelection = currentSelection;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @return The CommandResult containing the message indicating the number of results found.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Course selectedCourse = currentSelection.getSelectedCourse();
        selectedCourse.updateFilteredGroups(predicate);
        int filteredSize = selectedCourse.getUnmodifiableFilteredGroupList().size();

        if (filteredSize == 0) {
            ShowAllListedPredicate.resetGroupsFilter(currentSelection);
        }

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, filteredSize), willModifyState);
    }

    /**
     * Returns true if both FindGroupCommands have the same predicate.
     * This defines a weaker notion of equality between two FindGroupCommands.
     * @param other The other object to compare to.
     * @return True if the other object is a FindGroupCommand with the same predicate.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindGroupCommand // instanceof handles nulls
                && predicate.equals(((FindGroupCommand) other).predicate));
    }
}
