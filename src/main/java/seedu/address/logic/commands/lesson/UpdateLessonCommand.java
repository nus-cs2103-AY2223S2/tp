package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Update the information of an existing homework.
 */
public class UpdateLessonCommand extends Command {
    public static final String COMMAND_WORD = "update-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update the information of an existing lesson.\n"
        + "Parameters: "
        + PREFIX_NAME + "STUDENT_NAME "
        + PREFIX_INDEX + "LESSON_INDEX "
        + PREFIX_LESSON + "HOMEWORK_NAME "
        + PREFIX_STARTTIME + "START TIME"
        + PREFIX_ENDTIME + "END TIME\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_INDEX + "1 "
        + PREFIX_LESSON + "Math Lesson ";

    private final Index index;
    private final Optional<String> lessonName;
    private final Optional<LocalDateTime> startTime;
    private final Optional<LocalDateTime> endTime;
    private final NamePredicate predicate;
    private final List<String> names;

    /**
     * Creates an UpdateHomeworkCommand to update the information of an existing homework.
     *
     * @param index of the homework in the filtered homework list to update
     * @param predicate of the student to update the homework
     * @param lessonName of the lesson to be updated to
     * @param startTime of the lesson to be updated to
     * @param endTime of the lesson to be updated to
     */
    public UpdateLessonCommand(List<String> names, Index index, NamePredicate predicate,
                                 Optional<String> lessonName, Optional<LocalDateTime> startTime,
                               Optional<LocalDateTime> endTime) {
        requireNonNull(predicate);
        requireNonNull(index);

        this.index = index;
        this.predicate = predicate;
        this.lessonName = lessonName;
        this.startTime = startTime;
        this.endTime = endTime;
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
        Lesson lessonToUpdate;
        try {
            lessonToUpdate = student.getLesson(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        String newLessonName = this.lessonName.orElse(lessonToUpdate.getTitle());
        LocalDateTime newStartTime = this.startTime.orElse(lessonToUpdate.getStartTime());
        LocalDateTime newEndTime = this.endTime.orElse(lessonToUpdate.getEndTime());
        if (newStartTime.isAfter(newEndTime)) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_TIME);
        }

        if (Duration.between(newStartTime, newEndTime).toMinutes() < 30 || Duration.between(newStartTime,
            newEndTime).toHours() > 3) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DURATION);
        }

        Lesson newLesson = new Lesson(newLessonName, newStartTime, newEndTime);
        model.updateFilteredStudentList(s -> s != student);

        if (model.hasConflictingLessonTime(newLesson)) {
            throw new CommandException(Messages.MESSAGE_CONFLICTING_LESSON_TIME);
        }

        if (model.hasConflictingExamTime(newLesson)) {
            throw new CommandException(Messages.MESSAGE_CONFLICTING_EXAM_TIME);
        }

        try {
            student.setLesson(index.getZeroBased(), newLesson);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(
            String.format(Messages.MESSAGE_LESSON_UPDATED_SUCCESS, index.getOneBased(),
                student.getName().getFirstName(),
                newLessonName, newStartTime, newEndTime));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UpdateLessonCommand // instanceof handles nulls
            && predicate.equals(((UpdateLessonCommand) other).predicate)
            && index == ((UpdateLessonCommand) other).index
            && lessonName.equals(((UpdateLessonCommand) other).lessonName)
            && startTime.equals(((UpdateLessonCommand) other).startTime)
            && endTime.equals((((UpdateLessonCommand) other).endTime)));
    }
}
