package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for the {@link UnmarkCommand}
 */
public class UnmarkCommandParser implements Parser<UnmarkCommand> {
    @Override
    public UnmarkCommand parse(String userInput) throws ParseException {
      String[] args = userInput.split(" ");

        if (args.length == 1) {
            // No index specified
            throw new ParseException(String.format("%s needs an index!", UnmarkCommand.COMMAND_WORD));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(args[1]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), ive);
        }

        List<LocalDate> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MarkCommand.EXPECTED_DATE_FORMAT);
        if (args.length >= 2) {
            dates.add(LocalDate.now());
        } else {
            // User provided an index and possibly a date
            for (int i = 1; i < args.length; i++) {
                try {
                    dates.add(LocalDate.parse(args[i], formatter));
                } catch (DateTimeParseException e) {
                    throw new ParseException(String.format("%s expects the date in the following format: %s",
                        UnmarkCommand.COMMAND_WORD,
                        MarkCommand.EXPECTED_DATE_FORMAT
                    ));
                }
            }
        }

        return new UnmarkCommand(index, dates);
    }
}
