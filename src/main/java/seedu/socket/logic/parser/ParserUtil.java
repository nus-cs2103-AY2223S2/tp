package seedu.socket.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.socket.commons.core.index.Index;
import seedu.socket.commons.util.StringUtil;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.person.Address;
import seedu.socket.model.person.Email;
import seedu.socket.model.person.GitHubProfile;
import seedu.socket.model.person.Name;
import seedu.socket.model.person.Phone;
import seedu.socket.model.person.tag.Language;
import seedu.socket.model.person.tag.Tag;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectMeeting;
import seedu.socket.model.project.ProjectName;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;


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
     * Parses a {@code String profile} into a {@code GitHubProfile}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code profile} is invalid.
     */
    public static GitHubProfile parseProfile(String profile) throws ParseException {
        requireNonNull(profile);
        String trimmedProfile = profile.trim();
        if (!GitHubProfile.isValidProfile(trimmedProfile)) {
            throw new ParseException(GitHubProfile.MESSAGE_CONSTRAINTS);
        }
        return new GitHubProfile(trimmedProfile);
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
     * Parses a {@code String language} into a {@code Language}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code language} is invalid.
     */
    public static Language parseLanguage(String language) throws ParseException {
        requireNonNull(language);
        String trimmedLanguage = language.trim();
        if (!Language.isValidLanguageName(trimmedLanguage)) {
            throw new ParseException(Language.MESSAGE_CONSTRAINTS);
        }
        return new Language(trimmedLanguage);
    }

    /**
     * Parses {@code Collection<String> languages} into a {@code Set<Language>}.
     */
    public static Set<Language> parseLanguages(Collection<String> languages) throws ParseException {
        requireNonNull(languages);
        final Set<Language> languageSet = new HashSet<>();
        for (String languageName : languages) {
            languageSet.add(parseLanguage(languageName));
        }
        return languageSet;
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
     * Parses a {@code String name} into a {@code ProjectName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code projectName} is invalid.
     */
    public static ProjectName parseProjectName(String projectName) throws ParseException {
        requireNonNull(projectName);
        String trimmedName = projectName.trim();
        if (!ProjectName.isValidProjectName(trimmedName)) {
            throw new ParseException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        return new ProjectName(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code ProjectRepoHost}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code repoHost} is invalid.
     */
    public static ProjectRepoHost parseRepoHost(String repoHost) throws ParseException {
        requireNonNull(repoHost);
        String trimmedName = repoHost.trim();
        if (!ProjectRepoHost.isValidProjectRepoHost(trimmedName)) {
            throw new ParseException(ProjectRepoHost.MESSAGE_CONSTRAINTS);
        }
        return new ProjectRepoHost(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code ProjectRepoName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code repoName} is invalid.
     */
    public static ProjectRepoName parseRepoName(String repoName) throws ParseException {
        requireNonNull(repoName);
        String trimmedName = repoName.trim();
        if (!ProjectRepoName.isValidProjectRepoName(trimmedName)) {
            throw new ParseException(ProjectRepoName.MESSAGE_CONSTRAINTS);
        }
        return new ProjectRepoName(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code ProjectDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static ProjectDeadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedName = deadline.trim();
        if (!ProjectDeadline.isValidProjectDeadline(trimmedName)) {
            throw new ParseException(ProjectDeadline.MESSAGE_CONSTRAINTS);
        }
        return new ProjectDeadline(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code ProjectMeeting}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code meeting} is invalid.
     */
    public static ProjectMeeting parseMeeting(String meeting) throws ParseException {
        requireNonNull(meeting);
        String trimmedName = meeting.trim();
        if (!ProjectMeeting.isValidProjectMeeting(trimmedName)) {
            throw new ParseException(ProjectMeeting.MESSAGE_CONSTRAINTS);
        }
        return new ProjectMeeting(trimmedName);
    }

}
