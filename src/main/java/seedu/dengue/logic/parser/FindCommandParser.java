package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_ENDAGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_STARTAGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.dengue.logic.commands.FindCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.predicate.FindPredicate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.variant.Variant;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POSTAL, PREFIX_DATE,
                        PREFIX_AGE, PREFIX_VARIANT, PREFIX_ENDDATE, PREFIX_STARTDATE,
                        PREFIX_STARTAGE, PREFIX_ENDAGE);
        if (!isValidFormat(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        assert isValidFormat(argMultimap);

        Optional<Name> name = ParserUtil.parseOptionalName(argMultimap.getValue(PREFIX_NAME));
        Optional<SubPostal> subPostal = ParserUtil.parseOptionalSubPostal(
                argMultimap.getValue(PREFIX_POSTAL));
        Optional<Age> age = ParserUtil.parseOptionalAge(argMultimap.getValue(PREFIX_AGE));
        Optional<Date> date = ParserUtil.parseOptionalDate(argMultimap.getValue(PREFIX_DATE));
        Set<Variant> variantList = ParserUtil.parseVariants(argMultimap.getAllValues(PREFIX_VARIANT));
        Range<Date> dateRange = ParserUtil.parseDateRange(argMultimap.getValue(PREFIX_STARTDATE),
                argMultimap.getValue(PREFIX_ENDDATE));
        Range<Age> ageRange = ParserUtil.parseAgeRange(argMultimap.getValue(PREFIX_STARTAGE),
                argMultimap.getValue(PREFIX_ENDAGE));

        FindPredicate canFilter = new FindPredicate(name, subPostal, age, date,
                variantList, dateRange, ageRange);

        return new FindCommand(canFilter);
    }

    /**
     * Returns true if at least one of the prefixes contains non-empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if at least one of the prefixes for STARTDATE, ENDDATE is used in conjunction with DATE
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean hasMixedDates(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_DATE).isPresent()
                & (argumentMultimap.getValue(PREFIX_STARTDATE).isPresent()
                | argumentMultimap.getValue(PREFIX_ENDDATE).isPresent());
    }

    /**
     * Returns true if at least one of the prefixes for STARTAGE, ENDAGE is used in conjunction with AGE
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean hasMixedAges(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_AGE).isPresent()
                & (argumentMultimap.getValue(PREFIX_STARTAGE).isPresent()
                | argumentMultimap.getValue(PREFIX_ENDAGE).isPresent());
    }

    /**
     * Returns true if at least one of the prefixes for AGE and DATE are not mixed, and that the user input has
     * at least one prefix, and that the user input contains no preamble.
     */
    private static boolean isValidFormat(ArgumentMultimap argumentMultimap) {
        boolean isCommandNonEmpty = isAPrefixPresent(argumentMultimap, PREFIX_NAME, PREFIX_POSTAL,
                PREFIX_DATE, PREFIX_AGE, PREFIX_VARIANT, PREFIX_ENDDATE,
                PREFIX_STARTDATE, PREFIX_STARTAGE, PREFIX_ENDAGE);
        boolean isPreambleEmpty = argumentMultimap.getPreamble().isEmpty();
        boolean areAgePrefixesNotMixed = !hasMixedAges(argumentMultimap);
        boolean areDatePrefixesNotMixed = !hasMixedDates(argumentMultimap);
        return isCommandNonEmpty && isPreambleEmpty && areAgePrefixesNotMixed && areDatePrefixesNotMixed;
    }
}
