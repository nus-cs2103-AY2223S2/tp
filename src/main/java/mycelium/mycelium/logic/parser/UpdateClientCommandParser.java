package mycelium.mycelium.logic.parser;

import static java.util.Objects.requireNonNull;
import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_NEW_EMAIL;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_SOURCE;
import static mycelium.mycelium.logic.parser.ParserUtil.parseEmail;
import static mycelium.mycelium.logic.parser.ParserUtil.parseOptionalWith;

import java.util.Optional;

import mycelium.mycelium.logic.commands.UpdateClientCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Parses input arguments and creates a new UpdateClientCommand object
 */
public class UpdateClientCommandParser implements Parser<UpdateClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateClientCommand
     * and returns an UpdateClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateClientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_CLIENT_NAME,
                CliSyntax.PREFIX_CLIENT_NEW_EMAIL,
                CliSyntax.PREFIX_CLIENT_EMAIL,
                CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH,
                CliSyntax.PREFIX_SOURCE,
                CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER
            );
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CLIENT_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateClientCommand.MESSAGE_USAGE));
        }
        Email email = parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get());
        Optional<Name> name = parseOptionalWith(
            argMultimap.getValue(PREFIX_CLIENT_NAME), ParserUtil::parseName);
        Optional<Email> newEmail = parseOptionalWith(
            argMultimap.getValue(PREFIX_CLIENT_NEW_EMAIL), ParserUtil::parseEmail);
        Optional<YearOfBirth> yearOfBirth = parseOptionalWith(
            argMultimap.getValue(PREFIX_CLIENT_YEAR_OF_BIRTH),
            ParserUtil::parseYearOfBirth);
        Optional<NonEmptyString> source = parseOptionalWith(
            argMultimap.getValue(PREFIX_SOURCE),
            ParserUtil::parseSource);
        Optional<Phone> mobileNumber = parseOptionalWith(
            argMultimap.getValue(PREFIX_CLIENT_MOBILE_NUMBER),
            ParserUtil::parsePhone);
        UpdateClientCommand.UpdateClientDescriptor updateClientDescriptor = new UpdateClientCommand
            .UpdateClientDescriptor();
        updateClientDescriptor.setName(name);
        updateClientDescriptor.setEmail(newEmail);
        updateClientDescriptor.setYearOfBirth(yearOfBirth);
        updateClientDescriptor.setSource(source);
        updateClientDescriptor.setMobileNumber(mobileNumber);

        return new UpdateClientCommand(email, updateClientDescriptor);
    }


}
