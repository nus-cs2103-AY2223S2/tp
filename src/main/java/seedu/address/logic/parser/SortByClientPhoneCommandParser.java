package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand.SortByClientNameCommand;
import seedu.address.logic.commands.SortCommand.SortByClientPhoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class SortByClientPhoneCommandParser implements Parser<SortByClientPhoneCommand> {

    @Override
    public SortByClientPhoneCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        try {
            userInput = userInput.trim();
            int index = Integer.parseInt(userInput);
            if (index == 0) {
                return new SortByClientPhoneCommand(false);
            }
            return new SortByClientPhoneCommand(true);
        } catch (NumberFormatException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientNameCommand.MESSAGE_USAGE), ive);
        }
    }
}
