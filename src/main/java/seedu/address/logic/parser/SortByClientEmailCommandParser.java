package seedu.address.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.sortcommand.SortByClientEmailCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Command Parser of SortByClientEmailCommand
 */
public class SortByClientEmailCommandParser implements Parser<SortByClientEmailCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    @Override
    public SortByClientEmailCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        try {
            userInput = userInput.trim();
            int index = Integer.parseInt(userInput);
            if (index == 0) {
                logger.info("Parsed: " + userInput);
                return new SortByClientEmailCommand(false);
            }
            logger.info("Parsed: " + userInput);
            return new SortByClientEmailCommand(true);
        } catch (NumberFormatException ive) {
            logger.info("Missing parameters: " + userInput);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientEmailCommand.MESSAGE_USAGE), ive);
        }
    }
}
