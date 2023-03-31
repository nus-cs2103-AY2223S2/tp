package tfifteenfour.clipboard.logic.commands.sortcommand;

import java.util.Comparator;

import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Sorts all students in the student list by the specified category.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all students by "
            + "the specified category (case-insensitive).\n"
            + "Parameters: CATEGORY\n"
            + "Possible categories: name, id\n"
            + "Example: " + COMMAND_WORD + " id";
    public static final String MESSAGE_SUCCESS = "Sorted by: %s";

    private Comparator<Student> categoryComparator;
    private String categoryName;

    /**
     * Creates a FindCommand to search the list for names containing the specified string.
     *
     * @param categoryComparator the comparator for the specified category.
     * @param categoryName the name of the specified category.
     */
    public SortCommand(Comparator<Student> categoryComparator, String categoryName) {
        super(true);
        this.categoryComparator = categoryComparator;
        this.categoryName = categoryName;
    }

    @Override
    public CommandResult execute(Model model) {
        model.getCurrentSelection().getSelectedGroup().getModifiableStudentList().sort(categoryComparator);
        return new CommandResult(this, String.format(MESSAGE_SUCCESS, categoryName), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortCommand
                && categoryName.equals(((SortCommand) other).categoryName));
    }
}
