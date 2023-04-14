package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.Collection;
//import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.logic.parser.exceptions.IndexException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RESOURCE, PREFIX_TIMESLOT, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_REMARK, PREFIX_DEADLINE, PREFIX_TEACHER);

        Index index;
        try {
            //This should return only a valid index.
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IndexException indexException) {
            //thrown if the index is too big to be stored as an int
            throw new ParseException(String.format(MESSAGE_INVALID_MODULE_DISPLAYED_INDEX, EditCommand.MESSAGE_USAGE),
                    indexException);
        } catch (ParseException pe) {
            //thrown if the index contains incorrect prefixes (eg. z/)
            //these incorrect indexes will not be filtered out by ArgumentTokenizer
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editModuleDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_RESOURCE).isPresent()) {
            editModuleDescriptor.setResource(ParserUtil.parseResource(argMultimap.getValue(PREFIX_RESOURCE).get()));
        }
        if (argMultimap.getValue(PREFIX_TIMESLOT).isPresent()) {
            editModuleDescriptor.setTimeSlot(ParserUtil.parseTimeSlot(argMultimap.getValue(PREFIX_TIMESLOT).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editModuleDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editModuleDescriptor::setTags);
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editModuleDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editModuleDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_TEACHER).isPresent()) {
            editModuleDescriptor.setTeacher(ParserUtil.parseTeacher(argMultimap.getValue(PREFIX_TEACHER).get()));
        }
        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(index, editModuleDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        boolean tagsAreEmpty = tags.isEmpty();
        boolean tagsContainEmptyString = tags.contains("");

        /*
        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        */

        if (tagsAreEmpty) {
            return Optional.empty();
        } if (tagsContainEmptyString) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        Collection<String> tagSet = tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
