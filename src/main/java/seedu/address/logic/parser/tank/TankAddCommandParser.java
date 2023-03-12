package seedu.address.logic.parser.tank;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.tank.TankAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TankAddCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code TaskAddCommand}
     * and returns a {@code TaskAddCommand} object for execution.
     *
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public TankAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    TankAddCommand.MESSAGE_USAGE));
        }

        TankName tankName = ParserUtil.parseTankName(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Tank tank = new Tank(tankName, new AddressBook());

        return new TankAddCommand(tank);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

