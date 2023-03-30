package seedu.sprint.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.commands.EditApplicationCommand;
import seedu.sprint.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditApplicationCommand object.
 */
public class EditApplicationCommandParser implements Parser<EditApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public EditApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_COMPANY_EMAIL, PREFIX_STATUS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApplicationCommand.MESSAGE_USAGE), pe);
        }

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editApplicationDescriptor.setRole(ParserUtil.parseRole(
                    argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editApplicationDescriptor.setCompanyName(ParserUtil.parseCompanyName(
                    argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY_EMAIL).isPresent()) {
            editApplicationDescriptor.setCompanyEmail(ParserUtil.parseCompanyEmail(
                    argMultimap.getValue(PREFIX_COMPANY_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editApplicationDescriptor.setStatus(ParserUtil.parseStatus(
                    argMultimap.getValue(PREFIX_STATUS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editApplicationDescriptor::setTags);

        if (!editApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditApplicationCommand.MESSAGE_NOT_EDITED);
        }

        return new EditApplicationCommand(index, editApplicationDescriptor);
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
