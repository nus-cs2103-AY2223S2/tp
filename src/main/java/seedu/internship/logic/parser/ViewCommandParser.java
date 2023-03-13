package seedu.internship.logic.parser;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.ViewCommand;
import seedu.internship.logic.parser.exceptions.ParseException;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }
    }

}
