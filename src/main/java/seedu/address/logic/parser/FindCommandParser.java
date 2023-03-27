package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.NricContainsKeywordsPredicate;
import seedu.address.model.patient.StatusContainsKeywordsPredicate;
import seedu.address.model.patient.WardNameContainsKeywordsPredicate;




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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC, PREFIX_STATUS, PREFIX_WARD);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC, PREFIX_STATUS, PREFIX_WARD)
                || !argMultimap.getPreamble().isEmpty()
                || manyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC, PREFIX_STATUS, PREFIX_WARD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_NAME)) {
            String trimmedNames = argMultimap.getValue(PREFIX_NAME).get();
            checkArgsEmpty(trimmedNames);
            String[] nameKeywords = trimmedNames.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (anyPrefixesPresent(argMultimap, PREFIX_NRIC)) {
            String trimmedNric = argMultimap.getValue(PREFIX_NRIC).get();
            checkArgsEmpty(trimmedNric);
            String [] nricKeywords = trimmedNric.split("\\s+");
            return new FindCommand(new NricContainsKeywordsPredicate(Arrays.asList(nricKeywords)));
        } else if (anyPrefixesPresent(argMultimap, PREFIX_STATUS)) {
            String trimmedStatus = argMultimap.getValue(PREFIX_STATUS).get();
            checkArgsEmpty(trimmedStatus);
            String [] statusKeywords = trimmedStatus.split("\\s+");
            return new FindCommand(new StatusContainsKeywordsPredicate(Arrays.asList(statusKeywords)));
        } else {
            String trimmedWardName = argMultimap.getValue(PREFIX_WARD).get();
            checkArgsEmpty(trimmedWardName);
            String [] statusKeywords = trimmedWardName.split("\\s+");
            return new FindCommand(new WardNameContainsKeywordsPredicate(Arrays.asList(statusKeywords)));
        }
    }

    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean manyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count() > 1;
    }

    private void checkArgsEmpty(String trimmedArgs) throws ParseException {
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
