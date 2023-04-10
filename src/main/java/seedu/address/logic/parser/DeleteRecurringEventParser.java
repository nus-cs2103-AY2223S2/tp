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
            String[] split = userInput.split(" ", 3);

            if (split.length < 3) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteRecurringEventCommand.MESSAGE_USAGE));
            }

            personIndex = ParserUtil.parseIndex(split[1]);
            eventIndex = ParserUtil.parseIndex(split[2]);

        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteRecurringEventCommand.MESSAGE_USAGE), ive);
        }
        return new DeleteRecurringEventCommand(personIndex, eventIndex);
    }
}
