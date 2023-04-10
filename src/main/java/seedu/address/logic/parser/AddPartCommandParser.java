package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddPartCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates new AddPartCommand object
 */
public class AddPartCommandParser implements Parser<AddPartCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPartCommand
     * and returns an AddPartCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPartCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PART_NAME, PREFIX_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_PART_NAME, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPartCommand.MESSAGE_USAGE));
        }

        String partName = argMultimap.getValue(PREFIX_PART_NAME).get();
        if (!ParserUtil.isValidPartName(partName)) {
            throw new ParseException(ParserUtil.PART_MESSAGE_CONSTRAINTS);
        }
        int qty = ParserUtil.parseInt(argMultimap.getValue(PREFIX_QUANTITY).get());

        return new AddPartCommand(partName, qty);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
