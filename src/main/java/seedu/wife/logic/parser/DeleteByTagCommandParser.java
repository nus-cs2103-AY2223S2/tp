package seedu.wife.logic.parser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.wife.logic.commands.deleteCommands.DeleteByTagCommand;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.tag.Tag;
/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteByTagCommandParser implements Parser<DeleteByTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteByTagCommand
     * and returns a DeleteByTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteByTagCommand parse(String args) throws ParseException {
        try {
            Tag tag = ParserUtil.parseTag(args);
            return new DeleteByTagCommand(tag);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteByTagCommand.MESSAGE_USAGE), pe);
        }
    }

}
