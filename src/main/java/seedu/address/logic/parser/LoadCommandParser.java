package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.LoadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LoadCommand object
 */
public class LoadCommandParser implements Parser<LoadCommand> {
    /*
     * Must be a non-empty alphanumeric string.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]+";

    @Override
    public LoadCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String fileName = String.join("", argMultimap.getAllValues(Prefix.BLANK));

        // we only allow single unspaced strings as filenames
        if (!fileName.matches(VALIDATION_REGEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoadCommand.MESSAGE_USAGE));
        }

        Path filePath = Paths.get("data", fileName + ".json");

        return new LoadCommand(filePath);
    }
}
