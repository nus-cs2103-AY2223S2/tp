package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER;

import java.util.Set;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    public static final int METRIC_INDEX = 1;
    public static final int THRESHOLD_INDEX = 2;
    public static final Set<String> VALIDMETRICS = Set.of("performance", "urgency");

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILTER);

        String metric;
        int threshold;

        try {
            String[] terms = args.split("\\s+");
            if (terms.length != 3) {
                throw new IllegalArgumentException("Wrong number of items");
            } else {
                metric = terms[METRIC_INDEX];
                threshold = Integer.valueOf(terms[THRESHOLD_INDEX]);
                double checkDouble = Double.valueOf(terms[THRESHOLD_INDEX]);
                if (threshold < 0 || threshold > 100 || (int) checkDouble != threshold) {
                    throw new IllegalArgumentException("Invalid integer format!");
                }
                if (!VALIDMETRICS.contains(metric)) {
                    throw new IllegalArgumentException("Invalid metric!");
                }
            }
        } catch (IllegalArgumentException iae) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE), iae);
        }
        return new FilterCommand(metric, threshold);
    }
}
