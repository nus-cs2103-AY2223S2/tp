package teambuilder.logic.parser;

import static teambuilder.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static teambuilder.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static teambuilder.logic.parser.CliSyntax.PREFIX_EMAIL;
import static teambuilder.logic.parser.CliSyntax.PREFIX_MAJOR;
import static teambuilder.logic.parser.CliSyntax.PREFIX_NAME;
import static teambuilder.logic.parser.CliSyntax.PREFIX_PHONE;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TAG;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.Set;
import java.util.stream.Stream;

import teambuilder.logic.commands.AddCommand;
import teambuilder.logic.parser.exceptions.ParseException;
import teambuilder.model.person.Address;
import teambuilder.model.person.Email;
import teambuilder.model.person.Major;
import teambuilder.model.person.Name;
import teambuilder.model.person.Person;
import teambuilder.model.person.Phone;
import teambuilder.model.tag.Tag;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_MAJOR, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_MAJOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Major major = ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Tag> teamList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TEAM));

        Person person = new Person(name, phone, email, address, major, tagList, teamList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
