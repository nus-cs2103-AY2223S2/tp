package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.parent.ParentAddCommand;
import seedu.address.logic.commands.parent.ParentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.tag.Tag;

/**
 * ParentCommandParser that parses commands starting with "parent"
 */
public class ParentCommandParser {
    public static final String HELP_MESSAGE = "Parent command has to include an action.\n"
            + ParentCommand.MESSAGE_USAGE;
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<arguments>.*)");

    /**
     * Parse the parent commands into their respective prefixes
     * @param args User input.
     * @return ParentAddCommand with an ArgumentMultimap object that is derived from tokenizing the input with Prefixes.
     * @throws ParseException when there's an unexpected error in parsing the user input.
     */
    public ParentAddCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD, PREFIX_ADDRESS,
                        PREFIX_NAME, PREFIX_PARENTAGE, PREFIX_IMAGEPARENT, PREFIX_PHONEPARENT,
                        PREFIX_EMAIL);

        if (argMultimap.getValue(PREFIX_ADD).isPresent()) {
            return addCommand(argMultimap);
        } else {
            //Rest of logic (Need to edit)
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }
    }

    /**
     * Retrieve the relevant information to create a new Parent object from user input and parse it to create a new
     * Parent object and returns ParentAddCommand with the new Parent object.
     *
     * @param argMultimap An ArgumentMultimap object that is derived from tokenizing the user input with Prefixes.
     * @return ParentAddCommand to add the new Parent into PowerConnect.
     * @throws ParseException when there's an unexpected error in parsing the user input.
     */
    private ParentAddCommand addCommand(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONEPARENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParentAddCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Age age = ParserUtil.parseAge((argMultimap.getValue(PREFIX_PARENTAGE).get()));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Image image = ParserUtil.parseImage(argMultimap.getValue(PREFIX_IMAGEPARENT).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONEPARENT).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Parent parent = new Parent(name, age, image, email, phone, address, tagList);
        return new ParentAddCommand(parent);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
