package trackr.logic.parser;

import static java.util.Objects.requireNonNull;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import trackr.commons.core.index.Index;
import trackr.logic.commands.EditSupplierCommand;
import trackr.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditSupplierCommand object
 */
public class EditSupplierCommandParser implements Parser<EditSupplierCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditSupplierCommand
     * and returns an EditSupplierCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditSupplierCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSupplierCommand.MESSAGE_USAGE), pe);
        }

        EditSupplierDescriptor editSupplierDescriptor = new EditSupplierDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editSupplierDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editSupplierDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editSupplierDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editSupplierDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editSupplierDescriptor::setTags);

        if (!editSupplierDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditSupplierCommand.MESSAGE_NOT_EDITED);
        }

        return new EditSupplierCommand(index, editSupplierDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
