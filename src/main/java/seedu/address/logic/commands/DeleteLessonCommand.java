package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Deletes a lesson from a student.
 */
public class DeleteLessonCommand extends Command {

    public static final String COMMAND_WORD = "delete-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a lesson from a student.\n"
        + "Parameters: "
        + "n/STUDENT_NAME "
        + "i/INDEX\n"
        + "Example: " + COMMAND_WORD + " "
        + "n/John Doe "
        + "i/1";

    private final NameContainsKeywordsPredicate predicate;
    private final Index targetIndex;

    /**
     * Creates a DeleteLessonCommand to delete the specified lesson from the specified student.
     */
    public DeleteLessonCommand(NameContainsKeywordsPredicate predicate, Index targetIndex) {
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
                sb.append(String.format(Messages.MESSAGE_LESSON_DELETED_SUCCESS, targetIndex.getOneBased(),
                    student.getLesson(targetIndex).toString(), student.getName().toString()));
                sb.append("\n");
                student.deleteLesson(targetIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
            }
        }

        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteLessonCommand // instanceof handles nulls
            && predicate.equals(((DeleteLessonCommand) other).predicate)); // state check
    }
}

