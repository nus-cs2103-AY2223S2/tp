package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLIEDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final Prefix[] ALLOWED_PREFIXES = {PREFIX_NAME, PREFIX_PHONE};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ALLOWED_PREFIXES);
        Predicate<Person> findPredicate = x -> true; //always true predicate as default

        if (args.isBlank()
                || arePrefixesPresent(argMultimap, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_APPLIEDTIME, PREFIX_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            String[] nameKeywords = name.fullName.split("\\s+");
            findPredicate = findPredicate.and(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            String[] phoneKeywords = phone.value.split("\\s+");
            findPredicate = findPredicate.and(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        }

        return new FindCommand(findPredicate);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
