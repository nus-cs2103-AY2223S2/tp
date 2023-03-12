package seedu.address.logic.parser.group;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.group.GroupCommand;
import seedu.address.logic.commands.group.GroupCreateCommand;
import seedu.address.logic.commands.group.GroupDeleteCommand;
import seedu.address.logic.commands.group.GroupFindCommand;
import seedu.address.logic.commands.group.GroupListCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GroupCommand object
 */
public class GroupCommandParser implements Parser<GroupCommand> {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern GROUP_COMMAND_FORMAT =
            Pattern.compile("(?<subCommandWord>\\S+)(?<arguments>.*)");

    @Override
    public GroupCommand parse(String userInput) throws ParseException {
        final Matcher matcher = GROUP_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String subCommandWord = matcher.group("subCommandWord");
        final String arguments = matcher.group("arguments");

        switch (subCommandWord) {

        case GroupListCommand.SUB_COMMAND_WORD:
            return new GroupListCommand();

        case GroupCreateCommand.SUB_COMMAND_WORD:
            return new GroupCreateCommandParser().parse(arguments);

        case GroupFindCommand.SUB_COMMAND_WORD:
            return new GroupFindCommandParser().parse(arguments);

        case GroupDeleteCommand.SUB_COMMAND_WORD:
            return new GroupDeleteCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
