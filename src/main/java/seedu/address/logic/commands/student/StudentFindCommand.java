package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.student.StudentNameContainsKeywordsPredicate;

/**
 * Finds and lists all students in address book whose name contains any of the argument keywords.
 */
public class StudentFindCommand extends StudentCommand {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final StudentNameContainsKeywordsPredicate predicate;
    private final Class studentClass;

    /**
     * Creates a StudentFindCommand to find the specified {@code Student}
     * @param studentClass
     * @param predicate
     */
    public StudentFindCommand(String studentClass, StudentNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
        this.studentClass = Class.of(studentClass);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentListFind(predicate, studentClass);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()),
                true, studentClass);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentFindCommand // instanceof handles nulls
                && predicate.equals(((StudentFindCommand) other).predicate)); // state check
    }
}
