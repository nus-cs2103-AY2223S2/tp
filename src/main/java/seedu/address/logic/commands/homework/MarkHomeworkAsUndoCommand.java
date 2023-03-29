package seedu.address.logic.commands.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Homework;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Marks a homework as undone for a student.
 */
public class MarkHomeworkAsUndoCommand extends Command {

    public static final String COMMAND_WORD = "unmark-homework";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a homework as undone for a student.\n"
            + "Parameters: "
            + PREFIX_NAME + "STUDENT_NAME "
            + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_INDEX + "1";
    private final NamePredicate predicate;
    private final Index targetIndex;
    private final List<String> names;

    /**
     * Creates a MarkHomeworkAsUndoCommand to mark the specified homework as undone for the specified student.
     */
    public MarkHomeworkAsUndoCommand(List<String> names, NamePredicate predicate, Index targetIndex) {
        requireNonNull(predicate);
        requireNonNull(targetIndex);

        this.predicate = predicate;
        this.targetIndex = targetIndex;
        this.names = names;
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
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        StringBuilder nonExistNames = new StringBuilder();
        for (String name : names) {
            if (model.noSuchStudent(name)) {
                nonExistNames.append(name).append(", ");
            }
        }
        if (nonExistNames.length() != 0) {
            nonExistNames = new StringBuilder(nonExistNames.substring(0, nonExistNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_NO_SUCH_STUDENT, nonExistNames));
        }
        StringBuilder dupNames = new StringBuilder();
        for (String name : names) {
            if (model.hasDuplicateName(name)) {
                dupNames.append(name).append(", ");
            }
        }
        if (dupNames.length() != 0) {
            dupNames = new StringBuilder(dupNames.substring(0, dupNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_HAS_DUPLICATE_NAMES, dupNames));
        }
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
