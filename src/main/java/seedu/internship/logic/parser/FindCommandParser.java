package seedu.internship.logic.parser;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.DeleteCommand;
import seedu.internship.logic.commands.FindCommand.FilterInternshipDescriptor;
import seedu.internship.logic.commands.FindCommand;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.tag.Tag;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.parser.CliSyntax.*;

public class FindCommandParser implements Parser<FindCommand> {


    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_POSITION, PREFIX_COMPANY, PREFIX_STATUS, PREFIX_TAG);


        FilterInternshipDescriptor filterInternshipDescriptor = new FilterInternshipDescriptor();
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            filterInternshipDescriptor.setPosition(ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            filterInternshipDescriptor.setCompany(ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            filterInternshipDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        parseTagsForFind(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(filterInternshipDescriptor::setTags);
        if (!filterInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(filterInternshipDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForFind(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
