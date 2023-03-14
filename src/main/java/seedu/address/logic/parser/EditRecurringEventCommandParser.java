package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAYOFWEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGEVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditRecurringEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser class for EditRecurringEventCommandParser
 */
public class EditRecurringEventCommandParser implements Parser<EditRecurringEventCommand> {
    @Override
    public EditRecurringEventCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_RECURRINGEVENT, PREFIX_DAYOFWEEK, PREFIX_STARTDATETIME,
                        PREFIX_ENDDATETIME);

        Index personIndex;
        Index eventIndex;

        try {
            String inputWithBothIndex = argMultimap.getPreamble().trim();

            if (inputWithBothIndex.length() > 3) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditRecurringEventCommand.MESSAGE_USAGE));
            }
            personIndex = ParserUtil.parseIndex(String.valueOf(inputWithBothIndex.charAt(0)));
            eventIndex = ParserUtil.parseIndex(String.valueOf(inputWithBothIndex.charAt(2)));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRecurringEventCommand.MESSAGE_USAGE));
        } catch (IndexOutOfBoundsException ie) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRecurringEventCommand.MESSAGE_USAGE));
        }

        EditRecurringEventCommand.EditEventDescriptor editEventDescriptor =
                new EditRecurringEventCommand.EditEventDescriptor();

        if (argMultimap.getValue(PREFIX_RECURRINGEVENT).isPresent()) {
            editEventDescriptor.setEventName(ParserUtil.parseEventName(
                    argMultimap.getValue(PREFIX_RECURRINGEVENT).get()));
        }
        if (argMultimap.getValue(PREFIX_DAYOFWEEK).isPresent()) {
            editEventDescriptor.setDayOfWeek(ParserUtil.parseDayOfWeek(argMultimap.getValue(PREFIX_DAYOFWEEK).get()));
        }
        if (argMultimap.getValue(PREFIX_STARTDATETIME).isPresent()) {
            editEventDescriptor.setStartTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_STARTDATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_ENDDATETIME).isPresent()) {
            editEventDescriptor.setEndTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_ENDDATETIME).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRecurringEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRecurringEventCommand(personIndex, eventIndex, editEventDescriptor);

    }
}
