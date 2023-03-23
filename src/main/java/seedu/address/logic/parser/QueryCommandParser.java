package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.QueryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for the {@link QueryCommand}
 */
public class QueryCommandParser implements Parser<QueryCommand> {
    @Override
    public QueryCommand parse(String userInput) throws ParseException {
        String[] args = userInput.split(" ");

        if (args.length == 1) {
            // No index specified
            throw new ParseException(String.format("%s needs an index!", QueryCommand.COMMAND_WORD));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(args[1]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), ive);
        }

        if (args.length == 2) {
            // No date specified
            return new QueryCommand(index);
        }

        try {
            LocalDate date = LocalDate.parse(args[2], DateTimeFormatter.ofPattern(QueryCommand.EXPECTED_DATE_FORMAT));
            return new QueryCommand(index, date);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format("%s expects the date in the following format: %s",
                QueryCommand.COMMAND_WORD,
                QueryCommand.EXPECTED_DATE_FORMAT
            ));
        }
    }
}
