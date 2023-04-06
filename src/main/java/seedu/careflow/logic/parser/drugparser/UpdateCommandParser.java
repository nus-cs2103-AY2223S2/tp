package seedu.careflow.logic.parser.drugparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_UPDATE_BY;

import java.util.stream.Stream;

import seedu.careflow.logic.commands.drugcommands.UpdateCommand;
import seedu.careflow.logic.parser.ArgumentMultimap;
import seedu.careflow.logic.parser.ArgumentTokenizer;
import seedu.careflow.logic.parser.Parser;
import seedu.careflow.logic.parser.ParserUtil;
import seedu.careflow.logic.parser.Prefix;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.model.drug.TradeName;

/**
 * Parses input arguments and creates a new UpdateCommand object
 */
public class UpdateCommandParser implements Parser<UpdateCommand> {
    public static final String INVALID_VALUE_MESSAGE = "Update value is invalid, Please enter only integer values with"
            + " '+' or '-' prefixed.";
    public static final String INVALID_UNKNOWN_SYMBOL_MESSAGE = "Unknown/missing symbol ('+' for addition and '-' "
            + "for subtraction concatenated in front) for update value";
    private final char positive = '+';
    private final char negative = '-';
    @Override
    public UpdateCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_UPDATE_BY);
        if (arePrefixesPresent(argumentMultimap, PREFIX_UPDATE_BY)) {
            TradeName tradeName = ParserUtil.parseTradeName(argumentMultimap.getPreamble());
            String update = argumentMultimap.getValue(PREFIX_UPDATE_BY).get();
            boolean add;
            if (update.charAt(0) == negative) {
                add = false;
            } else if (update.charAt(0) == positive) {
                add = true;
            } else {
                throw new ParseException(INVALID_UNKNOWN_SYMBOL_MESSAGE);
            }
            try {
                Integer value = Integer.parseInt(update);
                return new UpdateCommand(tradeName, Math.abs(value), value > 0);
            } catch (NumberFormatException e) {
                throw new ParseException(INVALID_VALUE_MESSAGE);
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    seedu.careflow.logic.commands.drugcommands.UpdateCommand.MESSAGE_USAGE));
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
