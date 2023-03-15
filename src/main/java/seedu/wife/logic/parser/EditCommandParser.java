package seedu.wife.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_UNIT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.EditCommand;
import seedu.wife.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_UNIT, PREFIX_QUANTITY,
                        PREFIX_EXPIRY_DATE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditFoodDescriptor editFoodDescriptor = new EditFoodDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editFoodDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_UNIT).isPresent()) {
            editFoodDescriptor.setUnit(ParserUtil.parseUnit(argMultimap.getValue(PREFIX_UNIT).get()));
        }
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            editFoodDescriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }
        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            editFoodDescriptor.setExpiryDate(ParserUtil.parseExpiryDate(
                    argMultimap.getValue(PREFIX_EXPIRY_DATE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editFoodDescriptor::setTags);

        if (!editFoodDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editFoodDescriptor);
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
