package expresslibrary.logic.parser;

import expresslibrary.commons.core.index.Index;
import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.logic.commands.AddCommand;
import expresslibrary.logic.commands.BorrowCommand;
import expresslibrary.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.parser.CliSyntax.*;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_EMAIL;
import static java.util.Objects.requireNonNull;

public class BorrowCommandParser implements Parser<BorrowCommand> {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public BorrowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOK, PREFIX_DUEDATE);

        Index personIndex;
        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE), ive);
        }

        // Check if there is a valid due date
        LocalDate dueDate;
        if (argMultimap.getValue(PREFIX_DUEDATE).isPresent()) {
            String dateString = argMultimap.getValue(PREFIX_DUEDATE).orElse("");
            try {
                dueDate = LocalDate.parse(dateString, FORMATTER);
            } catch(DateTimeParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_INVALID_DATE));
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE));
        }

        Index bookIndex;
        try {
            bookIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_BOOK).orElse(""));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE), ive);
        }

        return new BorrowCommand(personIndex, bookIndex, dueDate);
    }

}
