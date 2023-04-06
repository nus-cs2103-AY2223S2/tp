package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.MedicineContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;



/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public FindCommand parse(String args) throws ParseException {
        String[] nrics;
        String[] names;
        String[] medicines;
        String[] tags;
        String[] doctors;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_NAME, PREFIX_MEDICINE, PREFIX_DOCTOR, PREFIX_TAG);

        if (moreThanOnePrefixPresent(argMultimap, PREFIX_NRIC, PREFIX_NAME, PREFIX_MEDICINE,
                PREFIX_DOCTOR, PREFIX_TAG)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_PREFIX_MESSAGE));
        }

        if (isPrefixesPresent(argMultimap, PREFIX_NRIC)
                && argMultimap.getPreamble().isEmpty()) {
            nrics = getKeywords(argMultimap.getValue(PREFIX_NRIC).get());

            return new FindCommand(new NricContainsKeywordsPredicate(Arrays.asList(nrics)));

        } else if (isPrefixesPresent(argMultimap, PREFIX_NAME)
                && argMultimap.getPreamble().isEmpty()) {
            names = getKeywords(argMultimap.getValue(PREFIX_NAME).get());

            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(names)));

        } else if (isPrefixesPresent(argMultimap, PREFIX_DOCTOR)
                && argMultimap.getPreamble().isEmpty()) {
            doctors = getKeywords(argMultimap.getValue(PREFIX_DOCTOR).get());

            return new FindCommand(new DoctorContainsKeywordsPredicate(Arrays.asList(doctors)));

        } else if (isPrefixesPresent(argMultimap, PREFIX_MEDICINE)
                && argMultimap.getPreamble().isEmpty()) {
            medicines = getKeywords(argMultimap.getValue(PREFIX_MEDICINE).get());
            System.out.println(medicines);
            return new FindCommand(new MedicineContainsKeywordsPredicate(Arrays.asList(medicines)));

        } else if (isPrefixesPresent(argMultimap, PREFIX_TAG)
                && argMultimap.getPreamble().isEmpty()) {
            tags = getKeywords(argMultimap.getValue(PREFIX_TAG).get());

            return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(tags)));

        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                        FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if the given prefixes does not contain empty
     *  {@code Optional} values in the given {@code ArgumentMultimap}.
     */
    private static boolean isPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if more than one prefix is given
     *  {@code Optional} values in the given {@code ArgumentMultimap}.
     */
    private static boolean moreThanOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        Stream<Prefix> prefixStream = Stream.of(prefixes).filter(
                                        prefix -> argumentMultimap.getValue(prefix).isPresent());
        int value = (int) prefixStream.count();
        if (value > 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if argument is empty before splitting argument
     *
     * @param arg argument passed by user
     * @return argument split into an array of strings
     * @throws ParseException
     */
    private static String[] getKeywords(String arg) throws ParseException {
        if (arg.isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String[] keywords = arg.split("\\s+");
        return keywords;
    }

}
