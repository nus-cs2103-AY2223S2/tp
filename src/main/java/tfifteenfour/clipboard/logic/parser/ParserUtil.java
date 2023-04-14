package tfifteenfour.clipboard.logic.parser;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.commons.util.StringUtil;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.\n";
    public static final String MESSAGE_INDEX_NOT_PROVIDED = "Index is not provided!\n";


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        String[] tokens = ArgumentTokenizer.tokenizeString(trimmedIndex);
        if (tokens.length > 1) {
            trimmedIndex = tokens[0];
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndexes} into an array of {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if any index is invalid (not non-zero unsigned integer).
     */
    public static Index[] parseMultipleIndex(String oneBasedIndexes) throws ParseException {
        String[] indexArray = oneBasedIndexes.split(",");
        if (indexArray.length == 0 || indexArray[0].length() == 0) {
            throw new ParseException(MESSAGE_INDEX_NOT_PROVIDED);
        }

        String[] trimmedArray = new String[indexArray.length];
        Index[] trimmedIndexes = new Index[indexArray.length];

        for (int i = 0; i < indexArray.length; i++) {
            trimmedArray[i] = indexArray[i].trim();
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedArray[i])) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
        }

        for (int i = 0; i < indexArray.length; i++) {
            trimmedIndexes[i] = Index.fromOneBased(Integer.parseInt(trimmedArray[i]));
        }
        return trimmedIndexes;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String studentId} into an {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String course} into a {@code <Course>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code module code} is invalid.
     */
    public static Course parseCourse(String courseCode) throws ParseException {
        requireNonNull(courseCode);
        String trimmedCourse = courseCode.trim();
        if (!Course.isValidCourseCode(trimmedCourse)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(trimmedCourse);
    }

    /**
     * Parses a {@code String group} into a {@code <Group>}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Group parseGroup(String groupName) throws ParseException {
        requireNonNull(groupName);
        String trimmedGroup = groupName.trim();
        if (!Group.isValidGroupName(trimmedGroup)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(trimmedGroup);
    }

    /**
     * Parses a {@code String sessionName} into a {@code <Session>}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Session parseSession(String sessionName) throws ParseException {
        requireNonNull(sessionName);
        String trimmedSession = sessionName.trim();
        if (!Session.isValidSessionName(trimmedSession)) {
            throw new ParseException(Session.MESSAGE_CONSTRAINTS);
        }
        return new Session(trimmedSession);
    }

    /**
     * Parses a {@code String taskName} into a {@code <Task>}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Task parseTask(String taskName) throws ParseException {
        requireNonNull(taskName);
        String trimmedTask = taskName.trim();
        if (!Task.isValidTaskName(trimmedTask)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }
        return new Task(trimmedTask);
    }
}
