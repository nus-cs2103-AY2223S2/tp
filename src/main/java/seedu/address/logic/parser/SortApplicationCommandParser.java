package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortApplicationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortApplicationCommand object.
 */
public class SortApplicationCommandParser implements ApplicationParser<SortApplicationCommand> {
    /**
     * Represents permitted values for the inputted order.
     */
    public enum SortingOrder {
        ALPHABETICAL, DEADLINE;

        /**
         * Returns true if a given string is a valid sorting order.
         */
        public static boolean isValidSortingOrder(String test) {
            for (SortingOrder s : SortingOrder.values()) {
                if (s.name().equalsIgnoreCase(test)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Represents permitted values for the inputted sequence.
     */
    public enum SortingSequence {
        ASCENDING, DESCENDING;

        /**
         * Returns true if a given string is a valid sorting sequence.
         */
        public static boolean isValidSortingSequence(String test) {
            if (test.equalsIgnoreCase("a") || test.equalsIgnoreCase("d")) {
                return true;
            }
            return false;
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SortApplicationCommand
     * and returns a SortApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortApplicationCommand parse(String args) throws ParseException {
        String[] trimmedArgs = args.trim().split("\\s+");
        if (trimmedArgs.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortApplicationCommand.MESSAGE_USAGE));
        }
        String userInputtedSequence = trimmedArgs[0].toLowerCase();
        String userInputtedOrder = trimmedArgs[1].toLowerCase();
        SortingOrder sortingOrder = ApplicationParserUtil.parseSortingOrder(userInputtedOrder);
        SortingSequence sortingSequence = ApplicationParserUtil.parseSortingSequence(userInputtedSequence);
        return new SortApplicationCommand(sortingOrder, sortingSequence);
    }
}
