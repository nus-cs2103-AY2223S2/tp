package seedu.quickcontacts.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.EditCommand;
import seedu.quickcontacts.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    public static final Prefix[] PREFIXES = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG};

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIXES);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
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

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String args) {
        String[] argsSplit = args.split(" ");
        if (args.isEmpty()) {
            return new AutocompleteResult(PREFIXES[0], false);
        }
        String lastPrefix = argsSplit[argsSplit.length - 1];

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + lastPrefix, PREFIXES);
        boolean returnNext = false;

        for (Prefix p : PREFIXES) {
            if (returnNext) {
                return new AutocompleteResult(p, true);
            }

            if (argMultimap.getValue(p).isPresent() && p.toString().equals(lastPrefix)) {
                returnNext = true;
            }
        }

        return new AutocompleteResult(PREFIXES[0], true);
    }
}
