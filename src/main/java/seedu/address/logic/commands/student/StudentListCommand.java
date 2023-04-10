package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.student.ClassContainsKeywordsPredicate;

/**
 * A class for the creation of Student list command
 */
public class StudentListCommand extends StudentCommand {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Lists all the students in the specified class \n"
            + "Example: " + "student 1A " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Students from Class ";
    public static final String MESSAGE_NO_STUDENTS = "There are no students in this class!";
    private final ClassContainsKeywordsPredicate predicate;

    private Class sc;

    /**
     * Creates a command for student list command.
     * @param sc
     */
    public StudentListCommand(Class sc, ClassContainsKeywordsPredicate predicate) {
        this.sc = sc;
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        if (model.getFilteredStudentList().size() == 0) {
            return new CommandResult(MESSAGE_NO_STUDENTS,
                    true, this.sc);
        }
        return new CommandResult(MESSAGE_SUCCESS + this.sc.getClassName() + " listed!",
                true, this.sc);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentListCommand // instanceof handles nulls
                && sc.equals(((StudentListCommand) other).sc)); // state check
    }
}
