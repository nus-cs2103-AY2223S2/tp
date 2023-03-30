package seedu.dengue.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.dengue.commons.core.index.Index;
import seedu.dengue.logic.commands.EditCommand;
import seedu.dengue.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.variant.Variant;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POSTAL,
                        PREFIX_DATE, PREFIX_AGE, PREFIX_VARIANT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        }
        catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_POSTAL).isPresent()) {
            editPersonDescriptor.setPostal(ParserUtil.parsePostal(argMultimap.getValue(PREFIX_POSTAL).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editPersonDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editPersonDescriptor.setAge(ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
        }
        parseVariantsForEdit(argMultimap.getAllValues(PREFIX_VARIANT)).ifPresent(editPersonDescriptor::setVariants);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> variants} into a {@code Set<Variant>} if {@code variants} is non-empty.
     * If {@code variants} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Variant>} containing zero variants.
     */
    private Optional<Set<Variant>> parseVariantsForEdit(Collection<String> variants) throws ParseException {
        assert variants != null;

        if (variants.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> variantSet = variants.size() == 1 && variants.contains("")
                ? Collections.emptySet()
                : variants;
        return Optional.of(ParserUtil.parseVariants(variantSet));
    }

}
