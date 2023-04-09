package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static bookopedia.logic.parser.CliSyntax.PREFIX_EMAIL;
import static bookopedia.logic.parser.CliSyntax.PREFIX_NAME;
import static bookopedia.logic.parser.CliSyntax.PREFIX_PARCEL;
import static bookopedia.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;
import java.util.stream.Stream;

import bookopedia.logic.commands.AddCommand;
import bookopedia.logic.parser.exceptions.ParseException;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Address;
import bookopedia.model.person.Email;
import bookopedia.model.person.Name;
import bookopedia.model.person.Person;
import bookopedia.model.person.Phone;


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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_PARCEL);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Parcel> parcelList = ParserUtil.parseParcels(argMultimap.getAllValues(PREFIX_PARCEL));

        Phone phone = new Phone("");
        if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }

        Email email = new Email("");
        if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }

        Person person = new Person(name, phone, email, address, parcelList, DeliveryStatus.PENDING, 0);

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
