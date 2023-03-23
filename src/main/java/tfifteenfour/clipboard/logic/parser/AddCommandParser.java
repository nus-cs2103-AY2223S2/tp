package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_MODULE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_NAME;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_REMARK;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import tfifteenfour.clipboard.logic.commands.addCommand.AddCommand;
import tfifteenfour.clipboard.logic.commands.addCommand.AddGroupCommand;
import tfifteenfour.clipboard.logic.commands.addCommand.AddModuleCommand;
import tfifteenfour.clipboard.logic.commands.addCommand.AddSessionCommand;
import tfifteenfour.clipboard.logic.commands.addCommand.AddStudentCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.student.Course;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        AddCommandType addCommandType;
        try {
            addCommandType = AddCommandType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Add type not found");
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizePrefixes(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_STUDENTID,
                        PREFIX_MODULE, PREFIX_REMARK, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STUDENTID, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_MODULE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
        Set<Course> modules = ParserUtil.parseModules(argMultimap.getAllValues(PREFIX_MODULE));
        Remark remark = new Remark("");
        Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Student student = new Student(name, phone, email, studentId, modules, remark, tags);

        switch (addCommandType) {
        case ADD_MODULE:
            return new AddModuleCommand();
        case ADD_GROUP:
            return new AddGroupCommand();
        case ADD_SESSION:
            return new AddSessionCommand();
        case ADD_STUDENT:
            return new AddStudentCommand();
        default:
            throw new ParseException("Invalid argument for add command");
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

enum AddCommandType {
    ADD_MODULE("module"),
    ADD_GROUP("group"),
    ADD_SESSION("session"),
    ADD_STUDENT("student");

    private String addType;

    private AddCommandType(String addType) {
        this.addType = addType;
    }

    public String getAddType() {
        return this.addType;
    }

    public static AddCommandType fromString(String addType) throws ParseException {
        for (AddCommandType sc : AddCommandType.values()) {
            if (sc.getAddType().equalsIgnoreCase(addType)) {
                return sc;
            }
        }

        throw new ParseException("Unrecognised category for add command: " + addType);
    }

}