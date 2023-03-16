package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewPartCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.service.PartIdPredicate;

/**
 * Parses input arguments and creates a new ViewPartCommand object
 */
public class ViewPartCommandParser implements Parser<ViewPartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewPartCommand
     * and returns a ViewPartCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewPartCommand parse(String args) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewPartCommand(new PartIdPredicate(index));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewPartCommand.MESSAGE_USAGE), pe);
        }
    }

}
