package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.BackupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code BackupCommand} object.
 */
public class BackupCommandParser implements Parser<BackupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code BackupCommand}
     * and returns a {@code BackupCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public BackupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        String desc;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESC);
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            desc = argMultimap.getValue(PREFIX_DESC).orElse("");
            checkArgument(isValidIndex(index));
        } catch (IllegalValueException | IllegalArgumentException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BackupCommand.MESSAGE_USAGE), ive);
        }


        return new BackupCommand(index, desc);
    }

    /**
     * Returns true if a given index is valid.
     */
    public static boolean isValidIndex(Index test) {
        return test.getOneBased() <= 10 && test.getOneBased() > 0;
    }
}
