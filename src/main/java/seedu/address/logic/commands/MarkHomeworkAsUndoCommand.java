package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Homework;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Marks a homework as undone for a student.
 */
public class MarkHomeworkAsUndoCommand extends Command {

    public static final String COMMAND_WORD = "unmark-homework";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a homework as undone for a student.\n"
            + "Parameters: "
            + "n/STUDENT_NAME "
            + "i/INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + "n/John Doe "
            + "i/1";
    private final NameContainsKeywordsPredicate predicate;
    private final Index targetIndex;

    /**
     * Creates a MarkHomeworkAsUndoCommand to mark the specified homework as undone for the specified student.
     */
    public MarkHomeworkAsUndoCommand(NameContainsKeywordsPredicate predicate, Index targetIndex) {
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

        if (studentList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_NAME);
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            try {
                Homework homeworkToMarkAsUndone = student.getHomework(targetIndex);
                if (!homeworkToMarkAsUndone.isCompleted()) {
                    sb.append(String.format(Messages.MESSAGE_HOMEWORK_ALREADY_MARKED_AS_UNDONE,
                            homeworkToMarkAsUndone.getDescription(), student.getName().toString()));
                } else {
                    sb.append(String.format(Messages.MESSAGE_HOMEWORK_MARKED_AS_UNDONE,
                            homeworkToMarkAsUndone.getDescription(), student.getName().toString()));
                    student.markHomeworkAsUndone(targetIndex);
                }
                sb.append("\n");
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
            }
        }

        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkHomeworkAsUndoCommand // instanceof handles nulls
                && predicate.equals(((MarkHomeworkAsUndoCommand) other).predicate)); // state check
    }
}
