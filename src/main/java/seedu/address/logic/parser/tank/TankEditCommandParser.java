package seedu.address.logic.parser.tank;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishEditCommand;
import seedu.address.logic.commands.tank.TankEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TankEditCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code TaskAddCommand}
     * and returns a {@code TaskAddCommand} object for execution.
     *
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public TankEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TankEditCommand.MESSAGE_USAGE), pe);
        }

        TankEditCommand.EditTankDescriptor editTankDescriptor = new TankEditCommand.EditTankDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTankDescriptor.setName(ParserUtil.parseTankName(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (!editTankDescriptor.isAnyFieldEdited()) {
            throw new ParseException(FishEditCommand.MESSAGE_NOT_EDITED);
        }

        return new TankEditCommand(index, editTankDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

