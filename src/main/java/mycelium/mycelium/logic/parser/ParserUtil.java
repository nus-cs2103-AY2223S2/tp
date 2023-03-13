package mycelium.mycelium.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.commons.core.index.Index;
import mycelium.mycelium.commons.util.StringUtil;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.person.Address;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Phone;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.tag.Tag;
import mycelium.mycelium.model.client.YearOfBirth;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /** Parses a {@code String year of birth} into an {@code YearOfBirth}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static YearOfBirth parseYearOfBirth(String yearOfBirth) throws ParseException {
        requireNonNull(yearOfBirth);
        String trimmedYearOfBirth = yearOfBirth.trim();
        if (!YearOfBirth.isValidYearOfBirth(trimmedYearOfBirth)) {
            throw new ParseException(YearOfBirth.MESSAGE_CONSTRAINTS);
        }
        return new YearOfBirth(trimmedYearOfBirth);
    }

    public static ProjectStatus parseProjectStatus(String projectStatus) throws ParseException {
        requireNonNull(projectStatus);
       /* String trimmedProjectStatus = projectStatus.trim();
        if (!ProjectStatus.isValidProjectStatus(trimmedProjectStatus)) {
            throw new ParseException(ProjectStatus.MESSAGE_CONSTRAINTS);
        }*/
        return ProjectStatus.NOT_STARTED;
    }

    public static String parseNonEmptyString(String source) throws ParseException {
        requireNonNull(source);
        String trimmedSource = source.trim();
        if (source.isEmpty()) {
            throw new ParseException(Messages.MESSAGE_EMPTY_SOURCE);
        }
        return trimmedSource;
    }

}
