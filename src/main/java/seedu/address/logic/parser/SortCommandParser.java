package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private final ArrayList<String> commandString =
            new ArrayList<>(Arrays.asList("name", "priority", "trans", "size"));
    private final ArrayList<String> dirStrings = new ArrayList<>(Arrays.asList("asc", "desc"));

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        //Must have exactly 2 keywords, one for field and one for direction, consider using assertions here
        String[] nameKeywords = trimmedArgs.split("\\s+");
        if (nameKeywords.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        String fieldKeyword = nameKeywords[0].toLowerCase();
        String directionKeyword = nameKeywords[1].toLowerCase();
        //We only allow sorting by 1 field at a time and commands must match pattern of field direction
        if (!commandString.contains(fieldKeyword) || !dirStrings.contains(directionKeyword)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }


        return new SortCommand(fieldKeyword, directionKeyword);
    }

}
