package seedu.address.logic.commands.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Homework;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateEntryException;

/**
 * Update the information of an existing homework.
 */
public class UpdateHomeworkCommand extends Command {
    public static final String COMMAND_WORD = "update-homework";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update the information of an existing homework.\n"
            + "Parameters: "
            + PREFIX_NAME + "STUDENT_NAME "
            + PREFIX_INDEX + "HOMEWORK_INDEX "
            + PREFIX_HOMEWORK + "HOMEWORK_NAME "
            + PREFIX_DEADLINE + "DEADLINE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_INDEX + "1 "
            + PREFIX_HOMEWORK + "Math Homework ";

    private final Index index;
    private final Optional<String> homeworkName;
    private final Optional<LocalDateTime> deadline;
    private final NamePredicate predicate;
    private final List<String> names;

    /**
     * Creates an UpdateHomeworkCommand to update the information of an existing homework.
     *
     * @param index        of the homework in the filtered homework list to update
     * @param predicate    of the student to update the homework
     * @param homeworkName of the homework to be updated to
     * @param deadline     of the homework to be updated to
     */
    public UpdateHomeworkCommand(List<String> names, Index index, NamePredicate predicate,
                                 Optional<String> homeworkName, Optional<LocalDateTime> deadline) {
        requireNonNull(predicate);
        requireNonNull(index);

        this.index = index;
        this.predicate = predicate;
        this.homeworkName = homeworkName;
        this.deadline = deadline;
        this.names = names;
    }

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

        Student student = studentList.get(0);

        try {
            Homework homeworkToUpdate = student.getHomework(index);

            String newHomeworkName = this.homeworkName.orElse(homeworkToUpdate.getDescription());
            LocalDateTime newDeadline = this.deadline.orElse(homeworkToUpdate.getDeadline());
            Homework newHomework = new Homework(newHomeworkName, newDeadline);
            student.setHomework(homeworkToUpdate, newHomework);

            return new CommandResult(
                    String.format(Messages.MESSAGE_HOMEWORK_UPDATED_SUCCESS, index.getOneBased(),
                            student.getName().getFirstName(),
                            newHomeworkName, newDeadline));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
        } catch (DuplicateEntryException e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateHomeworkCommand // instanceof handles nulls
                && predicate.equals(((UpdateHomeworkCommand) other).predicate)
                && index == ((UpdateHomeworkCommand) other).index
                && homeworkName.equals(((UpdateHomeworkCommand) other).homeworkName)
                && deadline.equals(((UpdateHomeworkCommand) other).deadline));
    }
}
