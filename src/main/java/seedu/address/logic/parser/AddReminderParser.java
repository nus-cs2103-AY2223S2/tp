package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.AddReminder;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reminder.Reminder;

/**
 * Parses input arguments and creates a new AddReminder object
 */
public class AddReminderParser implements Parser<AddReminder> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddReminder
     * and returns an AddReminder object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddReminder parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminder.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");
        String dateTimeString = argMultimap.getValue(PREFIX_TIME).orElse("none");
        LocalDateTime dateTime;
        try {
            dateTime = DateTimeUtil.toDateTime(dateTimeString);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminder.MESSAGE_USAGE));
        }

        Reminder reminder = new Reminder(description, dateTime);

        return new AddReminder(reminder);
    }
}
