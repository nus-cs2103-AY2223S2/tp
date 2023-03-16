package seedu.socket.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.EditCommand;
import seedu.socket.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.person.Address;
import seedu.socket.model.person.Email;
import seedu.socket.model.person.GitHubProfile;
import seedu.socket.model.person.Phone;
import seedu.socket.model.tag.Language;
import seedu.socket.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PROFILE, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_LANGUAGE, PREFIX_TAG);

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
        if (argMultimap.getValue(PREFIX_PROFILE).isPresent()) {
            String updatedProfile = argMultimap.getValue(PREFIX_PROFILE).get();
            if (!updatedProfile.isEmpty()) {
                editPersonDescriptor.setProfile(ParserUtil.parseProfile(updatedProfile));
            } else {
                throw new ParseException(GitHubProfile.MESSAGE_CONSTRAINTS);
            }
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String updatedPhone = argMultimap.getValue(PREFIX_PHONE).get();
            if (!updatedPhone.isEmpty()) {
                editPersonDescriptor.setPhone(ParserUtil.parsePhone(updatedPhone));
            } else {
                throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
            }
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String updatedEmail = argMultimap.getValue(PREFIX_EMAIL).get();
            if (!updatedEmail.isEmpty()) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(updatedEmail));
            } else {
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            }
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String updatedAddress = argMultimap.getValue(PREFIX_ADDRESS).get();
            if (!updatedAddress.isEmpty()) {
                editPersonDescriptor.setAddress(ParserUtil.parseAddress(updatedAddress));
            } else {
                throw new ParseException(Address.MESSAGE_CONSTRAINTS);
            }
        }
        parseLanguagesForEdit(argMultimap.getAllValues(PREFIX_LANGUAGE)).ifPresent(editPersonDescriptor::setLanguages);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> languages} into a {@code Set<Language>} if {@code languages} is non-empty.
     * If {@code languages} contain only one element which is an empty string, it will be return
     * {@code Optional.empty()}.
     */
    private Optional<Set<Language>> parseLanguagesForEdit(Collection<String> languages) throws ParseException {
        assert languages != null;

        if (languages.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> languageSet = languages.size() == 1 && languages.contains("")
                ? Collections.emptySet()
                : languages;
        if (languageSet.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseLanguages(languageSet));
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
