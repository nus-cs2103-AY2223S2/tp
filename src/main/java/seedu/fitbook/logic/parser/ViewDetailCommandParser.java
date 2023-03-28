package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.ViewDetailCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;

public class ViewDetailCommandParser implements Parser<ViewDetailCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an ViewDetailCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewDetailCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewDetailCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewDetailCommand.MESSAGE_USAGE), pe);
        }
    }
}
