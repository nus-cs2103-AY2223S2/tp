package seedu.address.logic.commands.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandUtil.handleDuplicateName;
import static seedu.address.logic.commands.CommandUtil.handleNonExistName;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Homework;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateEntryException;

/**
 * Adds an assignment to a student.
 */
public class CreateHomeworkCommand extends Command {
    public static final String COMMAND_WORD = "new-homework";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to students.\n"
            + "Parameters: "
            + PREFIX_NAME + "STUDENT_NAME "
            + PREFIX_HOMEWORK + "HOMEWORK_NAME "
            + PREFIX_DEADLINE + "DEADLINE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_HOMEWORK + "Math Homework "
            + PREFIX_DEADLINE + "2023-03-01T12:00";

    private final String homeworkName;
    private final LocalDateTime deadline;
    private final NamePredicate predicate;
    private final List<String> names;

    /**
     * Creates a CreateHomeworkCommand to add the specified assignment to the specified student.
     */
    public CreateHomeworkCommand(List<String> names, NamePredicate predicate, String homeworkName,
                                 LocalDateTime deadline) {
        requireNonNull(homeworkName);
        requireNonNull(deadline);
        requireNonNull(predicate);

        this.homeworkName = homeworkName;
        this.deadline = deadline;
        this.predicate = predicate;
        this.names = names;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        handleNonExistName(model, names);
        handleDuplicateName(model, names);
        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();
        Homework homework = new Homework(homeworkName, deadline);
        addHomework(studentList, homework);
        String message = formatMessage(studentList, homework);

        return new CommandResult(
                String.format(Messages.MESSAGE_HOMEWORK_ADDED_SUCCESS, homework, message));
    }

    /**
     * Adds the homework to the students.
     *
     * @param studentList The list of students to add the homework to.
     * @param homework The homework to be added.
     * @throws DuplicateEntryException If the homework already exists in the student.
     * @throws CommandException If the homework already exists in the student.
     */
    public void addHomework(List<Student> studentList, Homework homework)
            throws DuplicateEntryException, CommandException {
        try {
            for (Student student : studentList) {
                student.addHomework(homework);
            }
        } catch (DuplicateEntryException e) {
            throw new CommandException(String.format(Messages.MESSAGE_RESULT_IN_DUPLICATE, "homework"));
        }
    }

    /**
     * Formats the message to be displayed to the user.
     *
     * @param studentList The list of students to add the homework to.
     * @param homework The homework to be added.
     * @return The formatted message.
     */
    public String formatMessage(List<Student> studentList, Homework homework) {
        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student.getName().fullName);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateHomeworkCommand // instanceof handles nulls
                && predicate.equals(((CreateHomeworkCommand) other).predicate)
                && homeworkName.equals(((CreateHomeworkCommand) other).homeworkName)
                && deadline.equals(((CreateHomeworkCommand) other).deadline));
    }
}
