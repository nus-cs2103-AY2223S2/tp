package seedu.event.logic.parser;

import static seedu.event.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.event.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.event.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.event.logic.commands.NewContactCommand;
import seedu.event.logic.parser.exceptions.ParseException;
import seedu.event.model.contact.Contact;
import seedu.event.model.contact.ContactName;
import seedu.event.model.contact.ContactPhone;

/**
 * Parses input arguments and creates a new NewContactCommand object
 */
public class NewContactCommandParser implements Parser<NewContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NewContactCommand
     * and returns an NewContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewContactCommand.MESSAGE_USAGE));
        }

        ContactName name = ParserUtil.parseContactName(argMultimap.getValue(PREFIX_NAME).get());
        ContactPhone phone = ParserUtil.parseContactPhone(argMultimap.getValue(PREFIX_PHONE).get());

        Contact contact = new Contact(name, phone);

        return new NewContactCommand(contact);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
