package seedu.address.logic.parser.group;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.group.GroupDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GroupDeleteCommand object
 */
public class GroupDeleteCommandParser implements Parser<GroupDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GroupDeleteCommand
     * and returns a GroupDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public GroupDeleteCommand parse(String arguments) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(arguments);
            return new GroupDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
