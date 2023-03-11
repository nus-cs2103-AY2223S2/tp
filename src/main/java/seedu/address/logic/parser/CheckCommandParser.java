package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Creates check command parser to parse the user input.
 */
public class CheckCommandParser implements Parser<CheckCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CheckCommand
     * and returns a CheckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CheckCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE), pe);
        }
    }
}

