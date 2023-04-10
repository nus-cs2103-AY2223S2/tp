package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Parses input arguments and creates a new UntagCommand object
 */
public class UntagCommandParser implements Parser<UntagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UntagCommand
     * and returns an UntagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UntagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultiMap = ArgumentTokenizer.tokenize(args, Prefix.MODULE_TAG, Prefix.GROUP_TAG);

        ContactIndex contactIndex;

        try {
            contactIndex = ParserUtil.parseContactIndex(argumentMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE), pe);
        }

        Optional<Set<ModuleTag>> modulesToRemove =
                ParserUtil.parseModuleTagsForCommands(argumentMultiMap.getAllValues(Prefix.MODULE_TAG));

        Optional<Set<GroupTag>> groupsToRemove =
                ParserUtil.parseGroupTagsForCommands(argumentMultiMap.getAllValues(Prefix.GROUP_TAG));
        if (modulesToRemove.isEmpty() && groupsToRemove.isEmpty()) {
            throw new ParseException(UntagCommand.MESSAGE_NO_TAGS);
        }

        if (modulesToRemove.isPresent() && groupsToRemove.isPresent()) {
            throw new ParseException(UntagCommand.MESSAGE_BOTH_TAGS_INPUTTED);
        }

        if (groupsToRemove.isPresent()) {
            Set<GroupTag> groups = groupsToRemove.get();
            if (groups.isEmpty()) {
                throw new ParseException((UntagCommand.MESSAGE_NO_TAGS));
            }
            return new UntagCommand(contactIndex, groups, TagType.GROUP);
        }

        Set<ModuleTag> modules = modulesToRemove.get();
        if (modules.isEmpty()) {
            throw new ParseException((UntagCommand.MESSAGE_NO_TAGS));
        }
        return new UntagCommand(contactIndex, modules, TagType.MODULE);
    }

}
