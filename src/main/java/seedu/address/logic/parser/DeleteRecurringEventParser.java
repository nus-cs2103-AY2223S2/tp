package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteRecurringEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser class for Recurring Event
 */
public class DeleteRecurringEventParser implements Parser<DeleteRecurringEventCommand> {
    @Override
    public DeleteRecurringEventCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        Index personIndex;
        Index eventIndex;

        try {
            String trimmedInput = userInput.trim();
            personIndex = ParserUtil.parseIndex(String.valueOf(trimmedInput.charAt(0)));
            eventIndex = ParserUtil.parseIndex(String.valueOf(trimmedInput.charAt(2)));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteRecurringEventCommand.MESSAGE_USAGE), ive);
        }
        return new DeleteRecurringEventCommand(personIndex, eventIndex);
    }
}
