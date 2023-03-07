package seedu.address.logic.parser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.SaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new SaveCommand object
 */
public class SaveCommandParser implements Parser<SaveCommand> {
    @Override
    public SaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        List<String> fileNameList = argMultimap.getAllValues(new Prefix(""));

        // we only allow single unspaced strings as filenames
        if (fileNameList.stream().allMatch(String::isEmpty)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE));
        }

        Path filePath = Paths.get("data", fileNameList.get(0) + ".json");

        return new SaveCommand(filePath);
    }
}
