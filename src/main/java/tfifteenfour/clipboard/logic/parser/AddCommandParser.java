package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_NAME;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.stream.Stream;

import tfifteenfour.clipboard.logic.commands.addcommand.AddCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddCourseCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddGroupCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddSessionCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddStudentCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddTaskCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.task.Task;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    public static final String MESSAGE_INVALID_TYPE_FOR_ADD_COMMAND = "Invalid type for add command";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        CommandTargetType addCommandType;
        try {
            addCommandType = CommandTargetType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Add type missing! Please enter a valid add command. \n"
                    + "Available add commands are: add course, add group, add session, add task, add student");
        }

        switch (addCommandType) {
        case MODULE:
            Course course = parseCourseInfo(args);
            return new AddCourseCommand(course);
        case GROUP:
            Group group = parseGroupInfo(args);
            return new AddGroupCommand(group);
        case SESSION:
            Session session = parseSessionInfo(args);
            return new AddSessionCommand(session);
        case TASK:
            Task task = parseTaskInfo(args);
            return new AddTaskCommand(task);
        case STUDENT:
            Student student = parseStudentInfo(args);
            return new AddStudentCommand(student);
        default:
            throw new ParseException(MESSAGE_INVALID_TYPE_FOR_ADD_COMMAND);
        }
    }

    private Course parseCourseInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCourseCommand.MESSAGE_USAGE));
        }

        Course course = ParserUtil.parseCourse(tokens[2]);
        return course;
    }

    private Group parseGroupInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE));
        }

        Group group = ParserUtil.parseGroup(tokens[2]);
        return group;
    }

    private Session parseSessionInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE));
        }

        Session session = ParserUtil.parseSession(tokens[2]);
        return session;
    }

    private Task parseTaskInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length < 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        String taskName = args.split(" ", 2)[1].split(" ", 2)[1];

        Task task = ParserUtil.parseTask(taskName);
        return task;
    }

    private Student parseStudentInfo(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizePrefixes(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_STUDENTID);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STUDENTID, PREFIX_PHONE, PREFIX_EMAIL)
                || !CommandTargetType.isValidAddType(argMultimap.getPreamble())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
        // Set<Course> course = ParserUtil.parseModules(argMultimap.getAllValues(PREFIX_COURSE));
        Remark remark = new Remark("");
        // Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Student student = new Student(name, phone, email, studentId, remark);

        return student;
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
