package expresslibrary.logic.parser;

import expresslibrary.commons.core.index.Index;
import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.logic.commands.BorrowCommand;
import expresslibrary.logic.parser.exceptions.ParseException;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_BOOK;
import static java.util.Objects.requireNonNull;

public class BorrowCommandParser implements Parser<BorrowCommand> {

    public BorrowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOK);

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

        return new BorrowCommand(personIndex, bookIndex);
    }

}
