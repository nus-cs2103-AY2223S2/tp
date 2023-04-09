package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Parses input arguments and creates a new TagCommand object
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, Prefix.MODULE_TAG, Prefix.GROUP_TAG);

        ContactIndex contactIndex;

        try {
            contactIndex = ParserUtil.parseContactIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE), pe);
        }

        Optional<Set<ModuleTag>> modulesToAdd =
                ParserUtil.parseModuleTagsForCommands(argMultiMap.getAllValues(Prefix.MODULE_TAG));

        Optional<Set<GroupTag>> groupsToAdd =
                ParserUtil.parseGroupTagsForCommands(argMultiMap.getAllValues(Prefix.GROUP_TAG));

        if (modulesToAdd.isEmpty() && groupsToAdd.isEmpty()) {
            throw new ParseException(TagCommand.MESSAGE_NO_TAGS);
        }

        if (modulesToAdd.isPresent() && groupsToAdd.isPresent()) {
            throw new ParseException(TagCommand.MESSAGE_BOTH_TAGS_INPUTTED);
        }

        if (groupsToAdd.isPresent()) {
            Set<GroupTag> groups = groupsToAdd.get();
            if (groups.isEmpty()) {
                throw new ParseException(TagCommand.MESSAGE_NO_TAGS);
            }
            return new TagCommand(contactIndex, groups, TagType.GROUP);
        }

        Set<ModuleTag> modules = modulesToAdd.get();
        if (modules.isEmpty()) {
            throw new ParseException(TagCommand.MESSAGE_NO_TAGS);
        }

        return new TagCommand(contactIndex, modules, TagType.MODULE);
    }
}
