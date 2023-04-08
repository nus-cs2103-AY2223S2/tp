package seedu.wife.logic.parser.foodcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.wife.commons.core.index.Index;
import seedu.wife.commons.util.StringUtil;
import seedu.wife.logic.commands.foodcommands.DecreaseCommand;
import seedu.wife.logic.commands.foodcommands.DecreaseCommand.DecreaseFoodDescriptor;
import seedu.wife.logic.parser.ArgumentMultimap;
import seedu.wife.logic.parser.ArgumentTokenizer;
import seedu.wife.logic.parser.Parser;
import seedu.wife.logic.parser.ParserUtil;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.food.Quantity;

/**
 * Parses input arguments and creates a new DecreaseCommand object
 */
public class DecreaseCommandParser implements Parser<DecreaseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DecreaseCommand
     * and returns an DecreaseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DecreaseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY);

        Index index;

        index = StringUtil.getIndexFromCommand(argMultimap.getPreamble().trim(), DecreaseCommand.MESSAGE_USAGE);

        DecreaseFoodDescriptor decreaseFoodDescriptor = new DecreaseFoodDescriptor();
        //quantity needs to be > 0
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            decreaseFoodDescriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        } else {
            decreaseFoodDescriptor.setQuantity(new Quantity("1"));
        }

        return new DecreaseCommand(index, decreaseFoodDescriptor);

    }
}
