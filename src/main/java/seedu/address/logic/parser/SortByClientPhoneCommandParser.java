package seedu.address.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.sortcommand.SortByClientPhoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for SortByClientPhoneCommand
 */
public class SortByClientPhoneCommandParser implements Parser<SortByClientPhoneCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    @Override
    public SortByClientPhoneCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        try {
            userInput = userInput.trim();
            int index = Integer.parseInt(userInput);
            if (index == 0) {
                logger.info("Parsed: " + userInput);
                return new SortByClientPhoneCommand(false);
            }
            logger.info("Parsed: " + userInput);
            return new SortByClientPhoneCommand(true);
        } catch (NumberFormatException ive) {
            logger.info("Missing parameters: " + userInput);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientPhoneCommand.MESSAGE_USAGE), ive);
        }
    }
}
