package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.parent.ParentAddCommand;
import seedu.address.logic.commands.parent.ParentCommand;
import seedu.address.logic.commands.parent.ParentDeleteCommand;
import seedu.address.logic.commands.student.StudentDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Relationship;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.tag.Tag;

/**
 * ParentCommandParser that parses commands starting with "parent"
 */
public class ParentCommandParser {
    public static final String HELP_MESSAGE = "Parent command has to include a class and action.\n"
            + ParentCommand.MESSAGE_USAGE;
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<class>\\S+)(?<arguments>.*)");

    /**
     * Parse the command into their respective prefixes
     * @param args the command input by user
     * @return A ParentCommand
     * @throws ParseException
     */
    public ParentCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }

        final String studentClass = matcher.group("class");
        final String arguments = matcher.group("arguments");

        ArgumentMultimap argMultimapAdd =
                ArgumentTokenizer.tokenize(arguments, PREFIX_ADD, PREFIX_ADDRESS,
                        PREFIX_INDEXNUMBER, PREFIX_NAME,
                        PREFIX_RELATIONSHIP, PREFIX_AGE, PREFIX_IMAGEPARENT, PREFIX_PHONE,
                        PREFIX_EMAIL);

        ArgumentMultimap argMultimapDelete =
                ArgumentTokenizer.tokenize(arguments, PREFIX_DELETE, PREFIX_ADDRESS,
                        PREFIX_INDEXNUMBER, PREFIX_NAME,
                        PREFIX_RELATIONSHIP, PREFIX_AGE, PREFIX_IMAGEPARENT, PREFIX_PHONE,
                        PREFIX_EMAIL);



        if (argMultimapAdd.getValue(PREFIX_ADD).isPresent()) {
            return addCommand(studentClass, argMultimapAdd);
        } else if (argMultimapAdd.getValue(PREFIX_DELETE).isPresent()) {
            return deleteCommand(argMultimapDelete);
        } else {
            //Rest of logic (Need to edit)
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }
    }

    private ParentAddCommand addCommand(String studentClass, ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_INDEXNUMBER, PREFIX_NAME, PREFIX_RELATIONSHIP)
                || !argMultimap.getPreamble().isEmpty()
                || studentClass.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParentAddCommand.MESSAGE_USAGE));
        }
        Class sc = ParserUtil.parseStudentClass(studentClass);
        IndexNumber indexNumber = ParserUtil.parseIndexNumber(argMultimap.getValue(PREFIX_INDEXNUMBER).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Relationship rls = ParserUtil.parseRelationship(argMultimap.getValue(PREFIX_RELATIONSHIP).get());
        Age age = ParserUtil.parseAge((argMultimap.getValue(PREFIX_AGE).get()));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Image image = ParserUtil.parseImage(argMultimap.getValue(PREFIX_IMAGESTUDENT).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONESTUDENT).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAILSTUDENT).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));


        Parent parent = new Parent(sc, indexNumber, name, rls, age, image, email, phone, address, tagList);
        return new ParentAddCommand(parent);
    }

    public ParentDeleteCommand deleteCommand(ArgumentMultimap argMultimap) throws ParseException {
        try {
            Phone phoneNumber = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Name parentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            return new ParentDeleteCommand(parentName, phoneNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
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
