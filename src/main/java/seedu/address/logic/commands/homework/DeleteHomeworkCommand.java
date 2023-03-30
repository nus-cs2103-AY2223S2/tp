package seedu.address.logic.commands.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandUtil.handleDuplicateName;
import static seedu.address.logic.commands.CommandUtil.handleNonExistName;
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
import seedu.address.model.student.NamePredicate;
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

    private final NamePredicate predicate;
    private final Index targetIndex;
    private final List<String> names;
    /**
     * Creates a DeleteHomeworkCommand to delete the specified assignment from the specified student.
     */
    public DeleteHomeworkCommand(List<String> names, NamePredicate predicate, Index targetIndex) {
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

        handleNonExistName(model, names);
        handleDuplicateName(model, names);
        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();

        String message = formatMessage(studentList);
        return new CommandResult(message);
    }

    /**
     * Formats the string to be displayed.
     *
     * @param sb StringBuilder to be formatted.
     * @param student Student to be formatted.
     * @throws CommandException if the command's preconditions are not met
     */
    public void removeHomework(StringBuilder sb, Student student) throws CommandException {
        try {
            sb.append(String.format(Messages.MESSAGE_HOMEWORK_DELETED_SUCCESS, targetIndex.getOneBased(),
                    student.getHomework(targetIndex).toString(), student.getName().toString()));
            sb.append("\n");
            student.deleteHomework(targetIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
        }
    }

    /**
     * Formats the string to be displayed.
     *
     * @param studentList List of students to be formatted.
     * @return String to be displayed.
     * @throws CommandException if the command's preconditions are not met
     */
    public String formatMessage(List<Student> studentList) throws CommandException {
        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            removeHomework(sb, student);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteHomeworkCommand // instanceof handles nulls
                && predicate.equals(((DeleteHomeworkCommand) other).predicate)); // state check
    }
}
