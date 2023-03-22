package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Comment;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Date;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;

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
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param companyName The raw company name string.
     * @return a {@CompanyName} object encapsulating the company name.
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedName = companyName.trim();
        if (!CompanyName.isValidCompanyName(trimmedName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param role The raw role string to be parsed.
     * @return the {@code Role} object encapsulating the role.
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param status The raw status string to be parsed.
     * @return the {@code Status} object encapsulating the status.
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param date The raw date string to be parsed.
     * @return the {@code Date} object encapsulating the date.
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String comment} into an {@code Comment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param commentContent The raw comment to be parsed.
     * @return a {@code Comment} object encapsulating the comment content.
     * @throws ParseException if the given {@code comment} is invalid.
     */
    public static Comment parseComment(String commentContent) throws ParseException {
        String trimmedComment = commentContent.trim();
        if (!Comment.isValidComment(trimmedComment)) {
            throw new ParseException(Comment.MESSAGE_CONSTRAINTS);
        }
        return new Comment(trimmedComment);
    }


    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param tag The raw tag string to be parsed.
     * @return the {@code Tag} encapsulating the tag string.
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
     * Parses {@code Collection<String> tags} into a {@code Set<CompanyName>}.
     *
     * @param companyNames The collection of company name strings to be parsed.
     * @return a set of {@code CompanyName} objects encapsulating the company name strings.
     */
    public static Set<CompanyName> parseCompanyNames(Collection<String> companyNames) throws ParseException {
        requireNonNull(companyNames);
        final Set<CompanyName> companyNameSet = new HashSet<>();
        for (String tagName : companyNames) {
            companyNameSet.add(parseCompanyName(tagName));
        }
        return companyNameSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Role>}.
     *
     * @param roles The collection of role strings to be parsed.
     * @return a set of {@code Role} objects encapsulating the role strings.
     */
    public static Set<Role> parseRoles(Collection<String> roles) throws ParseException {
        requireNonNull(roles);
        final Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(parseRole(role));
        }
        return roleSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Status>}.
     *
     * @param statuses The collection of status strings to be parsed.
     * @return a set of {@code Tag} objects encapsulating the status strings.
     */
    public static Set<Status> parseStatuses(Collection<String> statuses) throws ParseException {
        requireNonNull(statuses);
        final Set<Status> statusSet = new HashSet<>();
        for (String status : statuses) {
            statusSet.add(parseStatus(status));
        }
        return statusSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Date>}.
     *
     * @param dates The collection of dates strings to be parsed.
     * @return a set of {@code Date} objects encapsulating the date strings.
     */
    public static Set<Date> parseDates(Collection<String> dates) throws ParseException {
        requireNonNull(dates);
        final Set<Date> dateSet = new HashSet<>();
        for (String date : dates) {
            dateSet.add(parseDate(date));
        }
        return dateSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     *
     * @param tags The collection of tag strings to be parsed.
     * @return a set of {@code Tag} objects encapsulating the tag strings.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
