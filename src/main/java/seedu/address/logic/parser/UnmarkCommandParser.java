package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnmarkCommandParser implements Parser<UnmarkCommand> {
    @Override
    public UnmarkCommand parse(String userInput) throws ParseException {
        String[] args = userInput.split(" ");

        LocalDate date;
        if (args.length == 1) {
            date = LocalDate.now();
        } else if (args.length > 1) {
            try {
                date = LocalDate.parse(args[1], DateTimeFormatter.ofPattern(MarkCommand.EXPECTED_DATE_FORMAT));
            } catch (DateTimeParseException e) {
                throw new ParseException(String.format("%s expects the date in the following format: %s", MarkCommand.COMMAND_WORD, MarkCommand.EXPECTED_DATE_FORMAT));
            } 
        } else {
            throw new ParseException(String.format("%s needs an index!", MarkCommand.COMMAND_WORD));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(args[0]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), ive);
        }

        return new UnmarkCommand(index, date);
    }
}
