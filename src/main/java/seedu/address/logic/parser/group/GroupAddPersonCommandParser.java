package seedu.address.logic.parser.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.group.GroupAddPersonCommand;
import seedu.address.logic.commands.group.GroupCreateCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserHelper;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new GroupAddPersonCommand object
 */
public class GroupAddPersonCommandParser implements Parser<GroupAddPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GroupAddPersonCommand
     * and returns a GroupAddPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public GroupAddPersonCommand parse(String arguments) throws ParseException {
        requireNonNull(arguments);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_GROUP);

        if (!ParserHelper.arePrefixesPresent(argumentMultimap, PREFIX_NAME, PREFIX_GROUP)
                || !ParserHelper.isPreambleEmpty(argumentMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCreateCommand.MESSAGE_USAGE));
        }

        Name personName = ParserUtil.parseName(argumentMultimap.getValue(PREFIX_GROUP).get());
        // TODO: Change to String or Create GroupName class, requires Model
        Name groupName = ParserUtil.parseName(argumentMultimap.getValue(PREFIX_GROUP).get());

        // TODO: Use Group in Constructor
        Person person = new
        return new GroupAddPersonCommand(personName, groupName.toString());
    }
}
