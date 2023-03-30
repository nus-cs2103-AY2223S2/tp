package seedu.address.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.sortcommand.SortByClientEmailCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Command Parser of SortByClientEmailCommand
 */
public class SortByClientEmailCommandParser implements Parser<SortByClientEmailCommand> {
    @Override
    public SortByClientEmailCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        try {
            userInput = userInput.trim();
            int index = Integer.parseInt(userInput);
            if (index == 0) {
                return new SortByClientEmailCommand(false);
            }
            return new SortByClientEmailCommand(true);
        } catch (NumberFormatException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientEmailCommand.MESSAGE_USAGE), ive);
        }
    }
}
