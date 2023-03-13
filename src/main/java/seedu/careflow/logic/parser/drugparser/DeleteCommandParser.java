package seedu.careflow.logic.parser.drugparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_TRADE_NAME;

import java.util.stream.Stream;

import seedu.careflow.commons.core.index.Index;
import seedu.careflow.logic.commands.drugcommands.DeleteCommand;
import seedu.careflow.logic.parser.ArgumentMultimap;
import seedu.careflow.logic.parser.ArgumentTokenizer;
import seedu.careflow.logic.parser.Parser;
import seedu.careflow.logic.parser.ParserUtil;
import seedu.careflow.logic.parser.Prefix;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.model.drug.TradeName;

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
                        seedu.careflow.logic.commands.drugcommands.DeleteCommand.MESSAGE_USAGE));
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
