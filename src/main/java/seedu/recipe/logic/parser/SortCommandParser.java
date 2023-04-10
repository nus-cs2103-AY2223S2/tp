package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.recipe.logic.commands.SortCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;

/**
 * Parses the given {@code String} of arguments in the context of the SortCommand
 * and returns a SortCommand object for execution.
 * @throws ParseException if the user input does not conform the expected format
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StarCommand
     * and returns a StarCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        try {
            boolean isAsc = ParserUtil.parseAsc(args);
            return new SortCommand(isAsc);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }
    }

}
