package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISOEVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditIsolatedEventCommand;
import seedu.address.logic.commands.EditIsolatedEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditIsolatedEventCommand object
 */
public class EditIsolatedEventCommandParser implements Parser<EditIsolatedEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditIsolatedEventCommand
     * and returns an EditIsolatedEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditIsolatedEventCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_ISOEVENT, PREFIX_STARTDATETIME, PREFIX_ENDDATETIME);

        Index personIndex;
        Index eventIndex;
        try {
            String inputWithBothIndex = argMultimap.getPreamble().trim();

            if (inputWithBothIndex.length() > 3) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditIsolatedEventCommand.MESSAGE_USAGE));
            }
            personIndex = ParserUtil.parseIndex(String.valueOf(inputWithBothIndex.charAt(0)));
            eventIndex = ParserUtil.parseIndex(String.valueOf(inputWithBothIndex.charAt(2)));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditIsolatedEventCommand.MESSAGE_USAGE));
        } catch (IndexOutOfBoundsException ie) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditIsolatedEventCommand.MESSAGE_USAGE));
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();

        if (argMultimap.getValue(PREFIX_ISOEVENT).isPresent()) {
            editEventDescriptor.setEventName(ParserUtil.parseEventName((argMultimap.getValue(PREFIX_ISOEVENT).get())));
        }
        if (argMultimap.getValue(PREFIX_STARTDATETIME).isPresent()) {
            editEventDescriptor.setStartDate(ParserUtil.parseDate((argMultimap.getValue(PREFIX_STARTDATETIME).get())));
        }
        if (argMultimap.getValue(PREFIX_ENDDATETIME).isPresent()) {
            editEventDescriptor.setEndDate(ParserUtil.parseDate((argMultimap.getValue(PREFIX_ENDDATETIME).get())));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditIsolatedEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditIsolatedEventCommand(personIndex, eventIndex, editEventDescriptor);

    }
}
