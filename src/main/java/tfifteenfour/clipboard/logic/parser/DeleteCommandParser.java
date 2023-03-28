package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_NAME;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_REMARK;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.addcommand.AddCourseCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddGroupCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddStudentCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteCourseCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteGroupCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteSessionCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteStudentCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final String MESSAGE_USAGE = "delete: Deletes the item at index specified in parameter. "
            + "Parameters: ITEM_TYPE INDEX\n"
            + "Note: INDEX must be a positive integer.\n"
            + "Examples: delete course 1, delete session 3";
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {

        CommandTargetType deleteCommandType;

        try {
            deleteCommandType = CommandTargetType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Delete type missing! Please enter a valid delete command.\n"
                    + "Available delete commands are: delete course, delete group, delete session, delete student");
        }

        Index index;
        try {
            index = parseDeleteCommandIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        switch (deleteCommandType) {
        case MODULE:
            return new DeleteCourseCommand(index);
        case GROUP:
            return new DeleteGroupCommand(index);
        case SESSION:
            return new DeleteSessionCommand(index);
        case STUDENT:
            return new DeleteStudentCommand(index);
        default:
            throw new ParseException("Invalid argument for add command");
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

    private Student parseStudentInfo(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizePrefixes(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_STUDENTID, PREFIX_REMARK, PREFIX_TAG);

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

    private Index parseDeleteCommandIndex(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length != 3) {
            throw new ParseException("Invalid number of arguments");
        }
        return ParserUtil.parseIndex(tokens[2]);
    }
}
