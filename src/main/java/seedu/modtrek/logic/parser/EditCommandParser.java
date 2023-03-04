package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.modtrek.logic.commands.EditCommand;
import seedu.modtrek.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_CREDIT, PREFIX_SEMYEAR, PREFIX_GRADE, PREFIX_TAG);

        Code code;

        try {
            code = ParserUtil.parseCode(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        if (argMultimap.getValue(PREFIX_CODE).isPresent()) {
            editModuleDescriptor.setCode(ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get()));
        }
        if (argMultimap.getValue(PREFIX_CREDIT).isPresent()) {
            editModuleDescriptor.setCredit(ParserUtil.parseCredit(argMultimap.getValue(PREFIX_CREDIT).get()));
        }
        if (argMultimap.getValue(PREFIX_SEMYEAR).isPresent()) {
            editModuleDescriptor.setSemYear(ParserUtil.parseSemYear(argMultimap.getValue(PREFIX_SEMYEAR).get()));
        }
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editModuleDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editModuleDescriptor::setTags);

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(code, editModuleDescriptor);
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
