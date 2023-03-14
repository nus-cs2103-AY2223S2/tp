package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, Prefix.NAME, Prefix.PHONE, Prefix.EMAIL, Prefix.ADDRESS,
                        Prefix.TELEGRAM_HANDLE, Prefix.GROUP_TAG, Prefix.MODULE_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(Prefix.NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(Prefix.NAME).get()));
        }
        if (argMultimap.getValue(Prefix.PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(Prefix.PHONE).get()));
        }
        if (argMultimap.getValue(Prefix.EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(Prefix.EMAIL).get()));
        }
        if (argMultimap.getValue(Prefix.ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(Prefix.ADDRESS).get()));
        }
        if (argMultimap.getValue(Prefix.TELEGRAM_HANDLE).isPresent()) {
            editPersonDescriptor.setTelegramHandle(ParserUtil
                    .parseTelegramHandle(argMultimap.getValue(Prefix.TELEGRAM_HANDLE).get()));
        }

        parseGroupTagsForEdit(argMultimap.getAllValues(Prefix.GROUP_TAG))
                .ifPresent(editPersonDescriptor::setGroupTags);
        parseModuleTagsForEdit(argMultimap.getAllValues(Prefix.MODULE_TAG))
                .ifPresent(editPersonDescriptor::setModuleTags);
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<GroupTag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<GroupTag>} containing zero tags.
     */
    private Optional<Set<GroupTag>> parseGroupTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseGroupTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<ModuleTag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<ModuleTag>} containing zero tags.
     */
    private Optional<Set<ModuleTag>> parseModuleTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseModuleTags(tagSet));
    }

}
