package seedu.address.logic.parser.drugparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRADE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE;

import java.util.stream.Stream;

import seedu.address.logic.commands.drugcommands.UpdateCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.drug.TradeName;

/**
 * Parses input arguments and creates a new UpdateCommand object
 */
public class UpdateCommandParser implements Parser<UpdateCommand> {
    private final char positive = '+';
    private final char negative = '-';
    @Override
    public UpdateCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_TRADE_NAME, PREFIX_UPDATE);
        if (arePrefixesPresent(argumentMultimap, PREFIX_TRADE_NAME, PREFIX_UPDATE)) {
            TradeName tradeName = ParserUtil.parseTradeName(argumentMultimap.getValue(PREFIX_TRADE_NAME).get());
            String update = argumentMultimap.getValue(PREFIX_UPDATE).get();
            boolean add;
            if (update.charAt(0) == negative) {
                add = false;
            } else if (update.charAt(0) == positive) {
                add = true;
            } else {
                throw new ParseException("Unknown/missing symbol"
                        + " ('+' for addition and '-' for subtraction concatenated in front) "
                        + "for update value");
            }
            try {
                Integer value = Integer.parseInt(update.substring(1));
                return new UpdateCommand(tradeName, value, add);
            } catch (NumberFormatException e) {
                throw new ParseException("Update value is invalid, "
                        + "Please enter only integer values with '+' or '-' prefixed.");
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    seedu.address.logic.commands.drugcommands.UpdateCommand.MESSAGE_USAGE));
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
