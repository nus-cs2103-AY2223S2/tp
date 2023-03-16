package seedu.socket.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.RemoveCommand;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.tag.Language;
import seedu.socket.model.tag.Tag;

/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns an RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROFILE, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_LANGUAGE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }

        RemoveCommand.RemovePersonDescriptor removePersonDescriptor = new RemoveCommand.RemovePersonDescriptor();
        if (argMultimap.getValue(PREFIX_PROFILE).isPresent()) {
            removePersonDescriptor.setProfile(ParserUtil.parseProfile(argMultimap.getValue(PREFIX_PROFILE).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            removePersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            removePersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            removePersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseLanguagesForRemove(argMultimap.getAllValues(PREFIX_LANGUAGE))
                .ifPresent(removePersonDescriptor::setLanguages);
        parseTagsForRemove(argMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(removePersonDescriptor::setTags);

        if (!removePersonDescriptor.isAnyFieldRemoved()) {
            throw new ParseException(RemoveCommand.MESSAGE_NOT_REMOVE);
        }

        return new RemoveCommand(index, removePersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> languages} into a {@code Set<Language>} if {@code languages} is non-empty.
     * If {@code languages} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Language>} containing zero languages.
     */
    private Optional<Set<Language>> parseLanguagesForRemove(Collection<String> languages) throws ParseException {
        assert languages != null;

        if (languages.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> languageSet = languages.size() == 1 && languages.contains("")
                ? Collections.emptySet()
                : languages;
        return Optional.of(ParserUtil.parseLanguages(languageSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForRemove(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
