package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.UnstarCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new StarCommand object
 */
public class UnstarCommandParser implements Parser<UnstarCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnstarCommand
     * and returns an UnstarCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnstarCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnstarCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnstarCommand.MESSAGE_USAGE), pe);
        }
    }

}
