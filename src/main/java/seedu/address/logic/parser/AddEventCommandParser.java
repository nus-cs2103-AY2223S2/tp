package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;

/**
 * Parses input arguments and creates a new AddEventCommand object.
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {
    private static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    private static final Prefix PREFIX_START_DATE_TIME = new Prefix("s/");
    private static final Prefix PREFIX_END_DATE_TIME = new Prefix("e/");
    private static final Prefix PREFIX_RECURRENCE = new Prefix("r/");

    @Override
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_START_DATE_TIME,
                PREFIX_END_DATE_TIME, PREFIX_RECURRENCE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_START_DATE_TIME, PREFIX_END_DATE_TIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        Description desc = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        DateTime startDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATE_TIME).get());
        DateTime endDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE_TIME).get());

        if (argMultimap.getValue(PREFIX_RECURRENCE).isEmpty()) {
            // Non-recurring event
            return new AddEventCommand(new OneTimeEvent(desc, startDateTime, endDateTime));
        } else {
            // Recurring event
            Recurrence recurrence = ParserUtil.parseRecurrence(argMultimap.getValue(PREFIX_RECURRENCE).get());
            return new AddEventCommand(new RecurringEvent(desc, startDateTime, endDateTime, recurrence));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the
     * given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
