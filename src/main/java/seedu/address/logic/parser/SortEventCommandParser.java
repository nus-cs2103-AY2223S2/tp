package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.SortEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortEventCommand object
 */
public class SortEventCommandParser implements Parser<SortEventCommand> {

    private static final Logger logger = LogsCenter.getLogger(SortEventCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the SortEventCommand
     * and returns a SortEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortEventCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEventCommand.MESSAGE_USAGE));
        }

        String[] sortTypes = trimmedArgs.split("\\s+");

        return new SortEventCommand(getSortType(sortTypes));
    }

    /**
     * Gets the last user input as the sort type and returns it in SortEventType if it is a valid sort event type.
     * @param sortEventTypes User inputs
     * @return SortEventType object converted from user's last input
     * @throws ParseException if the user input does not conform the expected format
     */
    private SortEventType getSortType(String[] sortEventTypes) throws ParseException {
        SortEventType sortEventType = SortEventType.getSortEventType(sortEventTypes[sortEventTypes.length - 1]);
        if (sortEventType == null) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEventCommand.MESSAGE_USAGE));
        }
        logger.info(String.format("User wish to sort events based on %s", sortEventType.toString()));
        return sortEventType;
    }
}
