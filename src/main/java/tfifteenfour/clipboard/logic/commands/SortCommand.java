package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.student.Student;

public class SortCommand extends Command {
	public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

	public static final String MESSAGE_SUCCESS = "Sorted by: %s";

	private Comparator<Student> categoryComparator;
	private String categoryName;

    /**
     * Creates a FindCommand to search the list for names containing the specified string
     *
     * @param predicate the predicate tester for checking if the student name contains the search string
     */
    public SortCommand(Comparator<Student> categoryComparator, String categoryName) {
        super(false);
		this.categoryComparator = categoryComparator;
		this.categoryName = categoryName;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
		model.getFilteredStudentList().sort(categoryComparator);
		return new CommandResult(this, String.format(MESSAGE_SUCCESS, categoryName), willModifyState);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && categoryName.equals(((SortCommand) other).categoryName)); // state check
    }
}

