package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.patientist.logic.commands.FindStaffCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.staff.StaffIdContainsKeywordsPredicate;
import seedu.patientist.model.person.staff.StaffNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindStaffCommand object
 */
public class FindStaffCommandParser implements Parser<FindStaffCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindPatientCommand
     * and returns a FindStaffCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStaffCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID) || !argMultimap.getPreamble().isEmpty()
                || areBothPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStaffCommand.MESSAGE_USAGE));
        }

        Optional<String> name = argMultimap.getValue(PREFIX_NAME);
        Optional<String> idNumber = argMultimap.getValue(PREFIX_ID);

        if (idNumber.isEmpty()) {
            String[] nameKeywords = name.get().trim().split("\\s+");
            return new FindStaffCommand(new StaffNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            String[] pidKeywords = idNumber.get().trim().split("\\s+");
            return new FindStaffCommand(new StaffIdContainsKeywordsPredicate(Arrays.asList(pidKeywords)));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areBothPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
