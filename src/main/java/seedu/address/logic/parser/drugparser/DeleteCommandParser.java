package seedu.address.logic.parser.drugparser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.drugcommands.DeleteCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.drug.TradeName;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    @Override
    public DeleteCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_INDEX);
        if (arePrefixesPresent(argumentMultimap, PREFIX_INDEX)) {
            Index index = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());
            return new DeleteCommand(index);
        } else {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_TRADE_NAME);
            if (arePrefixesPresent(argMultimap, PREFIX_TRADE_NAME)) {
                TradeName tradeName = ParserUtil.parseTradeName(argMultimap.getValue(PREFIX_TRADE_NAME).get());
                return new DeleteCommand(tradeName);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        seedu.address.logic.commands.drugcommands.DeleteCommand.MESSAGE_USAGE));
            }
        }
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
