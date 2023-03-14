package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleTagSet;
import seedu.address.model.tag.ModuleTag;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, Prefix.MODULE_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor= new EditPersonDescriptor();

        Optional<Set<ModuleTag>> modulesToAdd = parseModuleTagsForEdit(argMultiMap.getAllValues(Prefix.MODULE_TAG));

        if (modulesToAdd.isEmpty()) {
            throw new ParseException(TagCommand.MESSAGE_NO_TAGS);
        }

        return new TagCommand(index, modulesToAdd.get());
    }

    private Optional<Set<ModuleTag>> parseModuleTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseModuleTags(tagSet));
    }
}
