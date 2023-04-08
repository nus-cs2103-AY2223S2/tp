package seedu.address.logic.commands.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
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
import seedu.address.model.student.Exam;
import seedu.address.model.student.Grade;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Update the information of an existing homework.
 */
public class UpdateExamCommand extends Command {

    public static final String COMMAND_WORD = "update-exam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update the information of an existing exam.\n"
        + "Parameters: "
        + PREFIX_NAME + "STUDENT_NAME "
        + PREFIX_INDEX + "EXAM_INDEX "
        + PREFIX_EXAM + "EXAM_NAME "
        + PREFIX_STARTTIME + "START TIME"
        + PREFIX_ENDTIME + "END TIME"
        + PREFIX_WEIGHT + "WEIGHTAGE"
        + PREFIX_GRADE + "GRADE\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_INDEX + "1 "
        + PREFIX_EXAM + "Math Exam ";

    private final Index index;
    private final Optional<String> examName;
    private final Optional<LocalDateTime> startTime;
    private final Optional<LocalDateTime> endTime;
    private final Optional<Double> weightage;
    private final Optional<Grade> grade;

    private final NamePredicate predicate;
    private final List<String> names;

    /**
     * Creates an UpdateHomeworkCommand to update the information of an existing homework.
     *
     * @param index of the homework in the filtered homework list to update
     * @param predicate of the student to update the homework
     * @param examName of the lesson to be updated to
     * @param startTime of the lesson to be updated to
     * @param endTime of the lesson to be updated to
     */
    public UpdateExamCommand(List<String> names, Index index, NamePredicate predicate,
                               Optional<String> examName, Optional<LocalDateTime> startTime,
                               Optional<LocalDateTime> endTime, Optional<Double> weightage,
                               Optional<Grade> grade) {
        requireNonNull(predicate);
        requireNonNull(index);

        this.index = index;
        this.predicate = predicate;
        this.examName = examName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.names = names;
        this.weightage = weightage;
        this.grade = grade;
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
        Exam examToUpdate;
        try {
            examToUpdate = student.getExam(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }

        String newExamName = this.examName.orElse(examToUpdate.getDescription());
        LocalDateTime newStartTime = this.startTime.orElse(examToUpdate.getStartTime());
        LocalDateTime newEndTime = this.endTime.orElse(examToUpdate.getEndTime());
        if (newStartTime.isAfter(newEndTime)) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_TIME);
        }
        if (newEndTime.isAfter(LocalDateTime.now()) && grade.isPresent()) {
            throw new CommandException(Messages.MESSAGE_EXAM_NOT_COMPLETED);
        }
        Double newWeightage = this.weightage.orElse(examToUpdate.getWeightage());
        Grade newGrade = this.grade.orElse(examToUpdate.getGrade());

        //if (Duration.between(newStartTime, newEndTime).toMinutes() < 30 || Duration.between(newStartTime,
        //    newEndTime).toHours() > 3) {
        //    throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DURATION);
        //}
        Exam newExam = new Exam(newExamName, newStartTime, newEndTime, newWeightage, newGrade);

        try {
            student.setExam(index.getZeroBased(), newExam);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(
            String.format(Messages.MESSAGE_EXAM_UPDATED_SUCCESS, index.getOneBased(),
                student.getName().getFirstName(),
                newExamName, newStartTime, newEndTime, newWeightage, newGrade));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UpdateExamCommand // instanceof handles nulls
            && predicate.equals(((UpdateExamCommand) other).predicate)
            && index == ((UpdateExamCommand) other).index
            && examName.equals(((UpdateExamCommand) other).examName)
            && startTime.equals(((UpdateExamCommand) other).startTime)
            && endTime.equals((((UpdateExamCommand) other).endTime)));
    }
}
