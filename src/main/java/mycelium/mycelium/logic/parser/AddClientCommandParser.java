package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_SOURCE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER;


import java.util.stream.Stream;
import java.util.Optional;

import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Phone;



public class AddClientCommandParser implements Parser<AddClientCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddClientCommand
     * and returns an AddClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_NAME, PREFIX_CLIENT_EMAIL, PREFIX_SOURCE,
                        PREFIX_CLIENT_MOBILE_NUMBER, PREFIX_CLIENT_MOBILE_NUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_NAME, PREFIX_CLIENT_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_CLIENT_NAME).get());
        Optional<Phone> mobileNumber =
                Optional.of(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CLIENT_MOBILE_NUMBER).get()));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get());
        Optional<String> source = Optional.of(argMultimap.getValue(PREFIX_SOURCE).get());
        Optional<YearOfBirth> yearOfBirth =
                Optional.of(ParserUtil.parseYearOfBirth(argMultimap.getValue(PREFIX_CLIENT_YEAR_OF_BIRTH).get()));

        Client client = new Client(name, email, yearOfBirth, source, mobileNumber);

        return new AddClientCommand(client);
    }
}
