package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import tfifteenfour.clipboard.logic.commands.UploadCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UploadCommand object
 */
public class UploadCommandParser implements Parser<UploadCommand> {

    public static final String MESSAGE_INVALID_FILEPATH = "File path is not valid!";

    /**
     * Parses the given {@code String} of arguments in the context of the UploadCommand
     * and returns an UploadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UploadCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UploadCommand.MESSAGE_USAGE));
        }

        try {
            Path sourcePath = Paths.get(trimmedArgs);
            return new UploadCommand(sourcePath);
        } catch (InvalidPathException e) {
            throw new ParseException(MESSAGE_INVALID_FILEPATH);
        }
    }
}
