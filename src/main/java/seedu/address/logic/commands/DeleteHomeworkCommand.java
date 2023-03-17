package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Deletes an assignment from a student.
 */
public class DeleteHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "delete-homework";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an assignment from a student.\n"
            + "Parameters: "
            + PREFIX_NAME + "STUDENT_NAME "
            + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_INDEX + "1";

    private final NameContainsKeywordsPredicate predicate;
    private final Index targetIndex;

    /**
     * Creates a DeleteHomeworkCommand to delete the specified assignment from the specified student.
     */
    public DeleteHomeworkCommand(NameContainsKeywordsPredicate predicate, Index targetIndex) {
        requireNonNull(predicate);
        requireNonNull(targetIndex);

        this.predicate = predicate;
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if the command's preconditions are not met
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            try {
                sb.append(String.format(Messages.MESSAGE_HOMEWORK_DELETED_SUCCESS, targetIndex.getOneBased(),
                        student.getHomework(targetIndex).toString(), student.getName().toString()));
                sb.append("\n");
                student.deleteHomework(targetIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
            }
        }

        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteHomeworkCommand // instanceof handles nulls
                && predicate.equals(((DeleteHomeworkCommand) other).predicate)); // state check
    }
}
