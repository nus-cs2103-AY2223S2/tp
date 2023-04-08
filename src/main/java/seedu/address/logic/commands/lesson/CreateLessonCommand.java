package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.ConflictingLessonsException;

/**
 * Adds an assignment to a student.
 */
public class CreateLessonCommand extends Command {

    public static final String COMMAND_WORD = "new-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to students.\n"
        + "Parameters: "
        + PREFIX_NAME + "STUDENT_NAME "
        + PREFIX_LESSON + "LESSON_NAME "
        + PREFIX_STARTTIME + "Start time "
        + PREFIX_ENDTIME + "End time\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_LESSON + "Math Lesson "
        + PREFIX_STARTTIME + "2023-05-21 12:00 "
        + PREFIX_ENDTIME + "2023-05-21 14:00";

    public static final String MESSAGE_DATE = "endTime must be after startTime, both in the format YYYY-MM-DD HH:mm";

    private final String lessonName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final NamePredicate predicate;
    private final List<String> names;

    /**
     * Creates a CreateHomeworkCommand to add the specified assignment to the specified student.
     */
    public CreateLessonCommand(List<String> names, NamePredicate predicate, String lessonName, LocalDateTime startTime,
                               LocalDateTime endTime) {
        requireNonNull(lessonName);
        requireNonNull(startTime);
        requireNonNull(endTime);
        requireNonNull(predicate);

        this.lessonName = lessonName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.predicate = predicate;
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

        Lesson lesson = new Lesson(lessonName, startTime, endTime);

        if (model.hasConflictingLessonTime(lesson)) {
            throw new CommandException(Messages.MESSAGE_CONFLICTING_LESSON_TIME);
        }

        //        if (model.hasConflictingExamTime(lesson)) {
        //            throw new CommandException(Messages.MESSAGE_CONFLICTING_EXAM_TIME);
        //        }

        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new CommandException("start time must be in the future.");
        }

        if (startTime.isAfter(endTime)) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_TIME);
        }

        if (Duration.between(startTime, endTime).toMinutes() < 30 || Duration.between(startTime,
            endTime).toHours() > 3) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DURATION);
        }

        try {
            for (Student student : studentList) {
                student.addLesson(lesson);
            }
        } catch (ConflictingLessonsException e) {
            throw new CommandException(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student.getName().fullName);
            sb.append("\n");
        }

        return new CommandResult(
            String.format(Messages.MESSAGE_LESSON_ADDED_SUCCESS, lesson, sb));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CreateLessonCommand // instanceof handles nulls
            && predicate.equals(((CreateLessonCommand) other).predicate)
            && lessonName.equals(((CreateLessonCommand) other).lessonName)
            && startTime.equals(((CreateLessonCommand) other).startTime)
            && endTime.equals(((CreateLessonCommand) other).endTime));
    }
}

