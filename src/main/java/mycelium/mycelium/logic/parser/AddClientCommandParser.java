package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.Optional;

import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Phone;


/**
 * A command to add a new client.
 */
public class AddClientCommandParser implements Parser<AddClientCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the AddClientCommand
     * and returns an AddClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_NAME, PREFIX_CLIENT_EMAIL, PREFIX_SOURCE,
                PREFIX_CLIENT_YEAR_OF_BIRTH, PREFIX_CLIENT_MOBILE_NUMBER);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CLIENT_NAME, PREFIX_CLIENT_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        // Only the client's name and email are required fields. We parse them into their respective types here.
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_CLIENT_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get());

        Optional<Phone> mobileNumber = ParserUtil.parseOptionalWith(
            argMultimap.getValue(PREFIX_CLIENT_MOBILE_NUMBER),
            ParserUtil::parsePhone);
        Optional<String> source = ParserUtil.parseOptionalWith(
            argMultimap.getValue(PREFIX_SOURCE),
            ParserUtil::parseNonEmptyString);
        Optional<YearOfBirth> yearOfBirth = ParserUtil.parseOptionalWith(
            argMultimap.getValue(PREFIX_CLIENT_YEAR_OF_BIRTH),
            ParserUtil::parseYearOfBirth);

        Client client = new Client(name, email, yearOfBirth, source, mobileNumber);

        return new AddClientCommand(client);
    }
}
