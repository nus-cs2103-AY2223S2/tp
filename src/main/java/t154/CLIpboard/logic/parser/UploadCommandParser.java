package t154.CLIpboard.logic.parser;

import static t154.CLIpboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;
import java.nio.file.Paths;

import t154.CLIpboard.logic.commands.UploadCommand;
import t154.CLIpboard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UploadCommand object
 */
public class UploadCommandParser implements Parser<UploadCommand> {

    public static final String DESTINATION_FILEPATH = "./data";

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

        Path sourcePath = Paths.get(trimmedArgs);
        return new UploadCommand(sourcePath, Paths.get(DESTINATION_FILEPATH));
    }
}
