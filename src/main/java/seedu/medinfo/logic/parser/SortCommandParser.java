package seedu.medinfo.logic.parser;

import static seedu.medinfo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_DISCHARGE;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_WARD;
import static seedu.medinfo.logic.parser.ParserUtil.parseSortOrder;

import java.util.stream.Stream;

import seedu.medinfo.logic.commands.SortCommand;
import seedu.medinfo.logic.commands.SortCommand.Field;
import seedu.medinfo.logic.commands.SortCommand.Order;
import seedu.medinfo.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STATUS, PREFIX_DISCHARGE,
                PREFIX_WARD);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STATUS, PREFIX_DISCHARGE, PREFIX_WARD)
                || !argMultimap.getPreamble().isEmpty()
                || manyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STATUS, PREFIX_DISCHARGE, PREFIX_WARD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_NAME)) {
            String trimmedName = argMultimap.getValue(PREFIX_NAME).get();
            checkArgsEmpty(trimmedName);
            Order order = parseSortOrder(trimmedName);
            return new SortCommand(Field.NAME, order);
        } else if (anyPrefixesPresent(argMultimap, PREFIX_STATUS)) {
            String trimmedStatus = argMultimap.getValue(PREFIX_STATUS).get();
            checkArgsEmpty(trimmedStatus);
            Order order = parseSortOrder(trimmedStatus);
            return new SortCommand(Field.STATUS, order);
        } else if (anyPrefixesPresent(argMultimap, PREFIX_DISCHARGE)) {
            String trimmedDischarge = argMultimap.getValue(PREFIX_DISCHARGE).get();
            checkArgsEmpty(trimmedDischarge);
            Order order = parseSortOrder(trimmedDischarge);
            return new SortCommand(Field.DISCHARGE, order);
        } else {
            String trimmedWard = argMultimap.getValue(PREFIX_WARD).get();
            checkArgsEmpty(trimmedWard);
            Order order = parseSortOrder(trimmedWard);
            return new SortCommand(Field.WARD, order);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if more than one of the prefixes contains  {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean manyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count() > 1;
    }

    /**
     * Checks if a given argument is empty
     * @param trimmedArgs if the argument is empty
     */
    private void checkArgsEmpty(String trimmedArgs) throws ParseException {
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

}
