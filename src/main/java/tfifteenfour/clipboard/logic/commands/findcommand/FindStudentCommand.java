package tfifteenfour.clipboard.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.predicates.ShowAllListedPredicate;
import tfifteenfour.clipboard.logic.predicates.StudentNameContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;

public class FindStudentCommand extends FindCommand {
	public static final String COMMAND_TYPE_WORD = "student";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + ": Finds a student. "
            + "Parameters: "
            + "NAME_SEARCH_TERM\n"
            + "Example: " + COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + " " + "kelvin ";

	public static final String MESSAGE_SUCCESS = "Found %1$s results";
    private final StudentNameContainsPredicate predicate;
	private final CurrentSelection currentSelection;



    public FindStudentCommand(StudentNameContainsPredicate predicate, CurrentSelection currentSelection) {
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
		Group selectedGroup = currentSelection.getSelectedGroup();
        selectedGroup.updateFilteredStudents(predicate);
        int filteredSize = selectedGroup.getUnmodifiableFilteredStudentList().size();

        if (filteredSize == 0) {
            ShowAllListedPredicate.resetStudentsFilter(currentSelection);
        }

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, filteredSize), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStudentCommand // instanceof handles nulls
                && predicate.equals(((FindStudentCommand) other).predicate));
    }
}
