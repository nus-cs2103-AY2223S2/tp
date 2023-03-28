package tfifteenfour.clipboard.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.predicates.GroupNameContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;

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



    public FindGroupCommand(GroupNameContainsPredicate predicate, CurrentSelection currentSelection) {
        this.predicate = predicate;
		this.currentSelection = currentSelection;
    }
	/**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @param currentSelection of the {@code LogicManager}.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult execute(Model model, CurrentSelection currentSelection) throws CommandException {
        requireNonNull(model);
        Course selectedCourse = currentSelection.getSelectedCourse();
        selectedCourse.updateFilteredGroups(predicate);

        return new CommandResult(this, String.format(MESSAGE_SUCCESS,
                selectedCourse.getUnmodifiableFilteredGroupList().size()), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindGroupCommand // instanceof handles nulls
                && predicate.equals(((FindGroupCommand) other).predicate));
    }
}
