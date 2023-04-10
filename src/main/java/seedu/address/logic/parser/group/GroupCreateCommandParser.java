package seedu.address.logic.parser.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.commands.group.GroupCreateCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserHelper;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new GroupCreateCommand object
 */
public class GroupCreateCommandParser implements Parser<GroupCreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GroupCreateCommand
     * and returns a GroupCreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public GroupCreateCommand parse(String arguments) throws ParseException {
        requireNonNull(arguments);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_GROUP);

        if (!ParserHelper.arePrefixesPresent(argumentMultimap, PREFIX_GROUP)
                || !ParserHelper.isPreambleEmpty(argumentMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCreateCommand.MESSAGE_USAGE));
        }

        Group group = ParserUtil.parseGroup(argumentMultimap.getValue(PREFIX_GROUP).get());
        return new GroupCreateCommand(group);
    }
}
