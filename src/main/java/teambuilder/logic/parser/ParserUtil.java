package teambuilder.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import teambuilder.commons.core.index.Index;
import teambuilder.commons.util.StringUtil;
import teambuilder.logic.parser.exceptions.ParseException;
import teambuilder.model.person.Address;
import teambuilder.model.person.Email;
import teambuilder.model.person.Major;
import teambuilder.model.person.Name;
import teambuilder.model.person.Phone;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
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
        return Phone.of(trimmedPhone);
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
        return Address.of(trimmedAddress);
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
        return Email.of(trimmedEmail);
    }

    /**
     * Parses a {@code String major} into an {@code Major}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code major} is invalid.
     */
    public static Major parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        if (!Major.isValidMajor(trimmedMajor)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        return Major.of(trimmedMajor);
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

    /**
     * Parses a {@code String teamName} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code teamName} is invalid.
     */
    public static TeamName parseTeamName(String teamName) throws ParseException {
        requireNonNull(teamName);
        String trimmedTeamName = teamName.trim();
        if (!TeamName.isValidTeamName(trimmedTeamName)) {
            throw new ParseException(Team.MESSAGE_CONSTRAINTS);
        }
        return new TeamName(trimmedTeamName);
    }

    /**
     * Parses a {@code String teamName}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code teamName} is invalid.
     */
    public static Desc parseTeamDesc(String teamDesc) throws ParseException {
        requireNonNull(teamDesc);
        String trimmedTeamName = teamDesc.trim();
        if (!Desc.isValidTeamDesc(trimmedTeamName)) {
            throw new ParseException(Desc.MESSAGE_CONSTRAINTS);
        }
        return new Desc(trimmedTeamName);
    }

}
