package tfifteenfour.clipboard.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.predicates.ShowAllListedPredicate;
import tfifteenfour.clipboard.logic.predicates.StudentParticularsContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Group;

/**
 * Finds a student in a group.
 */
public class FindStudentCommand extends FindCommand {

    public static final String COMMAND_TYPE_WORD = "student";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + ": Finds a student by their name or student ID. "
            + "Parameters: "
            + "NAME_SEARCH_TERM\n"
            + "Example: " + COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + " " + "kelvin"
            + " or"
            + "SID_SEARCH_TERM\n"
            + "Example: " + COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + " " + "A1234567X";

    public static final String MESSAGE_SUCCESS = "Found %1$s results";
    private final StudentParticularsContainsPredicate predicate;
    private final CurrentSelection currentSelection;

    /**
     * Creates a FindStudentCommand with the given StudentNameContainsPredicate and CurrentSelection.
     *
     * @param predicate The StudentParticularsContainsPredicate to use for finding the student.
     * @param currentSelection The CurrentSelection object to get the selected group from.
     */
    public FindStudentCommand(StudentParticularsContainsPredicate predicate,
                                CurrentSelection currentSelection) {
        this.predicate = predicate;
        this.currentSelection = currentSelection;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
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

