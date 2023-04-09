package seedu.address.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.sortcommand.SortByClientNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Command Parser for SortByClientNameCommand
 */
public class SortByClientNameCommandParser implements Parser<SortByClientNameCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * @param args any integer number (inOrder) or 0 (reverseOrder)
     * @return command
     * @throws ParseException
     */
    @Override
    public SortByClientNameCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            args = args.trim();
            int index = Integer.parseInt(args);
            if (index == 0) {
                logger.info("Parsed: " + args);
                return new SortByClientNameCommand(false);
            }
            logger.info("Parsed: " + args);
            return new SortByClientNameCommand(true);
        } catch (NumberFormatException ive) {
            logger.info("Missing parameters: " + args);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientNameCommand.MESSAGE_USAGE), ive);
        }
    }
}
