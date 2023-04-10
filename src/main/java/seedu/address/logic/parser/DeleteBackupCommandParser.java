package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteBackupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteBackupCommand object.
 */
public class DeleteBackupCommandParser implements Parser<DeleteBackupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteBackupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(args);
        } catch (IllegalValueException | IllegalArgumentException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteBackupCommand.MESSAGE_USAGE), ive);
        }

        return new DeleteBackupCommand(index);
    }

}
