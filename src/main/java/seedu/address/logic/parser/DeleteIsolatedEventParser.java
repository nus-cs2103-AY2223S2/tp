package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteIsolatedEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteIsolatedEventCommand object
 */
public class DeleteIsolatedEventParser implements Parser<DeleteIsolatedEventCommand> {

    @Override
    public DeleteIsolatedEventCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        Index personIndex;
        Index eventIndex;

        String trimmedInput = userInput.trim();
        if (trimmedInput.isBlank() || trimmedInput.length() == 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteIsolatedEventCommand.MESSAGE_USAGE));
        }

        try {
            personIndex = ParserUtil.parseIndex(String.valueOf(trimmedInput.charAt(0)));
            eventIndex = ParserUtil.parseIndex(String.valueOf(trimmedInput.charAt(2)));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteIsolatedEventCommand.MESSAGE_USAGE), ive);
        }
        return new DeleteIsolatedEventCommand(personIndex, eventIndex);
    }
}
