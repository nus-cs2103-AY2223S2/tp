package seedu.recipe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.EditCommand;
import seedu.recipe.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_STEP, PREFIX_TITLE,
                        PREFIX_INGREDIENT, PREFIX_DESCRIPTION, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        EditRecipeDescriptor editRecipeDescriptor = new EditRecipeDescriptor();
        if (argMultimap.getValue(PREFIX_STEP).isPresent()) {
            editRecipeDescriptor.setSteps(ParserUtil
                    .parseSteps(argMultimap.getAllValues(PREFIX_STEP)));
        }
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editRecipeDescriptor.setTitle(ParserUtil
                    .parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_INGREDIENT).isPresent()) {
            editRecipeDescriptor
                    .setIngredients(ParserUtil.parseIngredients(argMultimap.getAllValues(PREFIX_INGREDIENT)));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editRecipeDescriptor.setDesc(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editRecipeDescriptor::setTags);

        if (!editRecipeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editRecipeDescriptor);
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
