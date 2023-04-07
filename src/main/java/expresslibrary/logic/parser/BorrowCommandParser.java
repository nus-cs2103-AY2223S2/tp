package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_BOOK;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_DUEDATE;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.commons.util.DateUtil;
import expresslibrary.logic.commands.BorrowCommand;
import expresslibrary.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new BorrowCommand object
 */
public class BorrowCommandParser implements Parser<BorrowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BorrowCommand
     * and returns an BorrowCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public BorrowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOK, PREFIX_DUEDATE);

        Index personIndex;
        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE), ive);
        }

        Index bookIndex;
        try {
            bookIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_BOOK).orElse(""));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE), ive);
        }

        // Check if there is a valid due date
        LocalDate dueDate;
        if (argMultimap.getValue(PREFIX_DUEDATE).isPresent()) {
            String dateString = argMultimap.getValue(PREFIX_DUEDATE).orElse("");
            try {
                dueDate = DateUtil.parseDate(dateString);
            } catch (DateTimeParseException e) {
                throw new ParseException(Messages.MESSAGE_INVALID_DATE);
            }
            // Check if due date is after the current date
            if (!dueDate.isAfter(LocalDate.now())) {
                throw new ParseException(Messages.MESSAGE_EARLY_DATE);
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE));
        }

        return new BorrowCommand(personIndex, bookIndex, dueDate);
    }

}
