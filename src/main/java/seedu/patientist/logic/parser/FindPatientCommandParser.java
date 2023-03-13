package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PID;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.patientist.logic.commands.FindPatientCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.NameContainsKeywordsPredicate;
import seedu.patientist.model.person.patient.PatientIdNumber;
import seedu.patientist.model.person.patient.PidContainsKeywordsPredicate;

/**
 * Finds and lists all patients in patientist book whose name/pid contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPatientCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the FindPatientCommand
     * and returns a FindPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPatientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PID);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PID) || !argMultimap.getPreamble().isEmpty()
                || areBothPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        }

        Optional<String> name = argMultimap.getValue(PREFIX_NAME);
        Optional<String> idNumber = argMultimap.getValue(PREFIX_PID);

        if (idNumber.isEmpty()) {
            String[] nameKeywords = name.get().trim().split("\\s+");
            return new FindPatientCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            String[] pidKeywords = idNumber.get().trim().split("\\s+");
            return new FindPatientCommand(new PidContainsKeywordsPredicate(Arrays.asList(pidKeywords)));
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
