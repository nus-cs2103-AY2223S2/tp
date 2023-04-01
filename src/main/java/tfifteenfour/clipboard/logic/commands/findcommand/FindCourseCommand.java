package tfifteenfour.clipboard.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.predicates.CourseNameContainsPredicate;
import tfifteenfour.clipboard.logic.predicates.ShowAllListedPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.Roster;

/**
 * Finds a course whose name contains any of the specified keywords.
 */
public class FindCourseCommand extends FindCommand {
    public static final String COMMAND_TYPE_WORD = "course";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + ": Finds a course. "
            + "Parameters: "
            + "COURSE_SEARCH_TERM\n"
            + "Example: " + COMMAND_WORD
            + " " + COMMAND_TYPE_WORD
            + " " + "CS2103T ";

    public static final String MESSAGE_SUCCESS = "Found %1$s results";
    private final CourseNameContainsPredicate predicate;

    /**
     * Creates a FindCourseCommand to find the specified courses with the given predicate.
     * @param predicate The predicate to filter courses with.
     */
    public FindCourseCommand(CourseNameContainsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Roster roster = model.getRoster();
        roster.updateFilteredCourses(predicate);
        int filteredSize = roster.getUnmodifiableFilteredCourseList().size();

        if (filteredSize == 0) {
            ShowAllListedPredicate.resetCoursesFilter(model);
        }

        return new CommandResult(this, String.format(MESSAGE_SUCCESS, filteredSize), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCourseCommand // instanceof handles nulls
                && predicate.equals(((FindCourseCommand) other).predicate));
    }
}
