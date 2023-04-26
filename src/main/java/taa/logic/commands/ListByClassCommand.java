package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.commons.core.Messages;
import taa.model.ClassIdMatchesPredicate;
import taa.model.Model;

/**
 * Lists all persons in the class list to the user.
 */
//Credits: with reference to the original AB3 implementations
public class ListByClassCommand extends Command {

    public static final String COMMAND_WORD = "classlist";

    public static final String MESSAGE_SUCCESS = "Listed all students in class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all students in the specified class\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 2";

    private final ClassIdMatchesPredicate predicate;

    public ListByClassCommand(ClassIdMatchesPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClassLists(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListByClassCommand // instanceof handles nulls
                && predicate.equals(((ListByClassCommand) other).predicate)); // state check
    }
}
