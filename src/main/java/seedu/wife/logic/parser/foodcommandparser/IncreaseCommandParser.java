package seedu.wife.logic.parser.foodcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.wife.commons.core.index.Index;
import seedu.wife.commons.util.StringUtil;
import seedu.wife.logic.commands.foodcommands.IncreaseCommand;
import seedu.wife.logic.commands.foodcommands.IncreaseCommand.IncreaseFoodDescriptor;
import seedu.wife.logic.parser.ArgumentMultimap;
import seedu.wife.logic.parser.ArgumentTokenizer;
import seedu.wife.logic.parser.Parser;
import seedu.wife.logic.parser.ParserUtil;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.food.Quantity;

/**
 * Parses input arguments and creates a new IncreaseCommand object
 */
public class IncreaseCommandParser implements Parser<IncreaseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IncreaseCommand
     * and returns an IncreaseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public IncreaseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY);

        Index index;

        index = StringUtil.getIndexFromCommand(argMultimap.getPreamble().trim(), IncreaseCommand.MESSAGE_USAGE);

        IncreaseFoodDescriptor increaseFoodDescriptor = new IncreaseFoodDescriptor();
        //quantity needs to be > 0
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            increaseFoodDescriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        } else {
            increaseFoodDescriptor.setQuantity(new Quantity("1"));
        }

        return new IncreaseCommand(index, increaseFoodDescriptor);

    }
}
