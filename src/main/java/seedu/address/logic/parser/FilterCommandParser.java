package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactContainsDescriptionPredicate;
import seedu.address.model.person.ContactContainsEmailPredicate;
import seedu.address.model.person.ContactContainsPhoneNumberPredicate;
import seedu.address.model.person.ContactContainsTagPredicate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new FilterCommand object
 *
 * @author Haiqel Bin Hanaffi
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                            PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

            int numOfPrefixesPresent = getNumOfPrefixesPresent(argMultimap,
                    PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_TAG);

            if (numOfPrefixesPresent > 1) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FilterCommand.MESSAGE_USAGE));
            }

            if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_TAG)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FilterCommand.MESSAGE_USAGE));
            }

            if (!isAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_TAG)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FilterCommand.MESSAGE_USAGE));
            }

            Prefix prefix = getPrefix(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_TAG);

            if (prefix.getPrefix().equals("n/") && argMultimap.getAllValues(PREFIX_NAME).size() == 1) {
                Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
                String fullName = name.toString();
                String trimmedFullName = fullName.trim();
                String[] nameKeywords = trimmedFullName.split("\\s+");
                return new FilterCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else if (prefix.getPrefix().equals("p/") && argMultimap.getAllValues(PREFIX_PHONE).size() == 1) {
                Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
                String phoneNumber = phone.toString();
                return new FilterCommand(new ContactContainsPhoneNumberPredicate(phoneNumber));
            } else if (prefix.getPrefix().equals("e/") && argMultimap.getAllValues(PREFIX_EMAIL).size() == 1) {
                Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
                String emailAddr = email.toString();
                return new FilterCommand(new ContactContainsEmailPredicate(emailAddr));
            } else if (prefix.getPrefix().equals("d/") && argMultimap.getAllValues(PREFIX_ADDRESS).size() == 1) {
                Address description = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
                String fullDescription = description.toString();
                return new FilterCommand(new ContactContainsDescriptionPredicate(fullDescription));
            } else if (prefix.getPrefix().equals("t/")) {
                Set<Tag> tagSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
                return new FilterCommand(new ContactContainsTagPredicate(tagSet));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FilterCommand.MESSAGE_USAGE));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns the type of prefix
     * {@code ArgumentMultimap}.
     */
    private static Prefix getPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        Optional<Prefix> optionalPrefix = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .findFirst();

        return optionalPrefix.orElse(null);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns the number of prefixes that user inputs
     */
    private static int getNumOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return (int) Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count();
    }
}
