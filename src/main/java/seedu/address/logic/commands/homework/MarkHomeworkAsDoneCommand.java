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
import seedu.address.model.student.Homework;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Marks an assignment as done for a student.
 */
public class MarkHomeworkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "mark-homework";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an assignment as done for a student.\n"
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
     * Creates a MarkHomeworkAsDoneCommand to mark the specified assignment as done for the specified student.
     */
    public MarkHomeworkAsDoneCommand(List<String> names, NamePredicate predicate, Index targetIndex) {
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
        String message = formatString(studentList);

        return new CommandResult(message);
    }

    /**
     * Formats the string to be displayed to the user.
     *
     * @param studentList List of students to be displayed.
     * @return String to be displayed to the user.
     * @throws CommandException if the command's preconditions are not met
     */
    public String formatString(List<Student> studentList) throws CommandException {
        if (studentList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_NAME);
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            markHomework(sb, student);
        }
        return sb.toString();
    }

    /**
     * Marks the homework as done for the student.
     *
     * @param sb String builder to be displayed to the user.
     * @param student Student to be marked as done.
     * @throws CommandException if the command's preconditions are not met
     */
    public void markHomework(StringBuilder sb, Student student) throws CommandException {
        try {
            Homework homeworkToMarkAsDone = student.getHomework(targetIndex);
            differentiateHomework(sb, homeworkToMarkAsDone, student);
            sb.append("\n");
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
        }
    }

    /**
     * Differentiates between the homework being marked as done and the homework already being marked as done.
     *
     * @param sb String builder to be displayed to the user.
     * @param homeworkToMarkAsDone Homework to be marked as done.
     * @param student Student to be marked as done.
     */
    public void differentiateHomework(StringBuilder sb, Homework homeworkToMarkAsDone, Student student) {
        if (homeworkToMarkAsDone.isCompleted()) {
            sb.append(String.format(Messages.MESSAGE_HOMEWORK_ALREADY_MARKED_AS_DONE,
                    homeworkToMarkAsDone.getDescription(), student.getName().toString()));
        } else {
            sb.append(String.format(Messages.MESSAGE_HOMEWORK_MARKED_AS_DONE,
                    homeworkToMarkAsDone.getDescription(), student.getName().toString()));
            student.markHomeworkAsDone(targetIndex);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkHomeworkAsDoneCommand // instanceof handles nulls
                && predicate.equals(((MarkHomeworkAsDoneCommand) other).predicate)); // state check
    }
}
