package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ELDERLY_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VOLUNTEER_NRIC;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.PrefixUtil;
import seedu.address.logic.commands.AddPairCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPairCommand object
 */
public class AddPairCommandParser implements Parser<AddPairCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPairCommand
     * and returns an AddPairCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPairCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ELDERLY_NRIC, PREFIX_VOLUNTEER_NRIC);

        if (!PrefixUtil.arePrefixesPresent(argMultimap, PREFIX_ELDERLY_NRIC, PREFIX_VOLUNTEER_NRIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPairCommand.MESSAGE_USAGE));
        }

        Name elderly = ParserUtil.parseName(argMultimap.getValue(PREFIX_ELDERLY_NRIC).get());
        Name volunteer = ParserUtil.parseName(argMultimap.getValue(PREFIX_VOLUNTEER_NRIC).get());
        Phone phone = ParserUtil.parsePhone("88888888");
        Email email = ParserUtil.parseEmail("dummy@email.com");
        Address address = ParserUtil.parseAddress("dummy address");
        Set<Tag> tagList = ParserUtil.parseTags(new HashSet<>());

        Pair pair = new Pair(new Person(elderly, phone, email, address, tagList),
                new Person(volunteer, phone, email, address, tagList));

        return new AddPairCommand(pair);
    }

}
