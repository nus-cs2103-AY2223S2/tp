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
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for the {@link MarkCommand} command
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    @Override
    public MarkCommand parse(String userInput) throws ParseException {
        String[] args = userInput.split(" ");

        if (args.length == 1) {
            // No index specified
            throw new ParseException(String.format("%s needs an index!", MarkCommand.COMMAND_WORD));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(args[1]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), ive);
        }

        List<LocalDate> dates = new ArrayList<>();
        if (args.length == 2) {
            dates.add(LocalDate.now());
        } else {
            // User provided an index and possibly a date
            for (int i = 2; i < args.length; i++) {
                try {
                    dates.add(LocalDate.parse(args[i], DateTimeFormatter.ofPattern(MarkCommand.EXPECTED_DATE_FORMAT)));
                } catch (DateTimeParseException e) {
                    throw new ParseException(String.format("%s expects the date in the following format: %s",
                        MarkCommand.COMMAND_WORD,
                        MarkCommand.EXPECTED_DATE_FORMAT
                    ));
                }
            }
        }

        return new MarkCommand(index, dates);
    }
}
