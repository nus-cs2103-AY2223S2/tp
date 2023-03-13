package seedu.address.logic.parser.group;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.group.GroupFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.MemberOfGroupPredicate;

/**
 * Parses input arguments and creates a new GroupFindCommand object
 */
public class GroupFindCommandParser implements Parser<GroupFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GroupFindCommand
     * and returns a GroupFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupFindCommand parse(String arguments) throws ParseException {
        String trimmedArguments = arguments.trim();
        if (trimmedArguments.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArguments.split("\\s+");

        return new GroupFindCommand(new GroupNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                new MemberOfGroupPredicate(Arrays.asList(nameKeywords)));
    }

}
