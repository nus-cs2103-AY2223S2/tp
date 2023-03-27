package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_STUDENT;

import seedu.address.logic.commands.SortStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class SortStudentCommandParser implements Parser<SortStudentCommand> {

    public static final int GROUP_INDEX = 1;
    public static final int METRIC_INDEX = 2;
    public static final int ORDER_INDEX = 3;
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_STUDENT);

        String group;
        String metric;
        boolean increasingOrder;

        try {
            String[] terms = args.split("\\s+");
            if (terms.length != 4) {
                throw new IllegalArgumentException("Wrong number of items");
            } else {
                group = terms[GROUP_INDEX];
                metric = terms[METRIC_INDEX];
                increasingOrder = (terms[ORDER_INDEX].equals("reverse")) ? false : true;
            }
        } catch (IllegalArgumentException iae) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortStudentCommand.MESSAGE_USAGE), iae);
        }
        return new SortStudentCommand(group, metric, increasingOrder);
    }
}
