package seedu.internship.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internship.commons.core.index.Index;
import seedu.internship.commons.util.StringUtil;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.internship.Comment;
import seedu.internship.model.internship.CompanyName;
import seedu.internship.model.internship.Date;
import seedu.internship.model.internship.Role;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is smaller than 1.";
    public static final String MESSAGE_INVALID_POSITIVE_SIGNED_INDEX = "Index has positive sign.";
    public static final String MESSAGE_INVALID_INDEX_FORMAT = "Index is not an integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        try {
            StringUtil.nonZeroUnsignedIntegerCheck(trimmedIndex);
        } catch (ParseException pe) {
            throw pe;
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }
    /**
     * Parses {@code oneBasedIndexes} into a {@code List<Index>} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     * @throws ParseException if any specified index is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseIndexes(String oneBasedIndexes) throws ParseException {
        List<String> tokens = Arrays.asList(oneBasedIndexes.split("\\s+"))
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        List<Index> result = new ArrayList<>();
        for (String index: tokens) {
            result.add(parseIndex(index));
        }

        return result;
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

    //@@author kohkaixun

    /**
     * Parses {@code List<String> unparsedNames} into a {@code List<String>} of parsed company names.
     * @param unparsedNames The list of company name strings to be parsed.
     * @return The list of company name strings that has the correct format.
     * @throws ParseException if any of the given company names in the list cannot be parsed.
     */
    public static List<String> parseCompanyNamesToString(List<String> unparsedNames) throws ParseException {
        List<String> parsedNames = ParserUtil.parseCompanyNames(unparsedNames).stream()
                .map(name -> name.fullCompanyName)
                .collect(Collectors.toList());
        return parsedNames;
    }

    /**
     * Parses {@code List<String> unparsedRoles} into a {@code List<String>} of parsed role names.
     * @param unparsedRoles The list of role strings to be parsed.
     * @return The list of role strings that has the correct format.
     * @throws ParseException if any of the given roles in the list cannot be parsed.
     */
    public static List<String> parseRolesToString(List<String> unparsedRoles) throws ParseException {
        List<String> parsedRoles = ParserUtil.parseRoles(unparsedRoles).stream()
                .map(role -> role.fullRole)
                .collect(Collectors.toList());
        return parsedRoles;
    }

    /**
     * Parses {@code List<String> unparsedStatuses} into a {@code List<String>} of parsed statuses.
     * @param unparsedStatuses The list of status strings to be parsed.
     * @return The list of status strings that has the correct format.
     * @throws ParseException if any of the given statuses in the list cannot be parsed.
     */
    public static List<String> parseStatusesToString(List<String> unparsedStatuses) throws ParseException {
        List<String> parsedStatuses = ParserUtil.parseStatuses(unparsedStatuses).stream()
                .map(status -> status.fullStatus)
                .collect(Collectors.toList());
        return parsedStatuses;
    }

    /**
     * Parses {@code List<String> unparsedDates} into a {@code List<String>} of parsed dates.
     * @param unparsedDates The list of date strings to be parsed.
     * @return The list of date strings that has the correct format.
     * @throws ParseException if any of the given dates in the list cannot be parsed.
     */
    public static List<String> parseDatesToString(List<String> unparsedDates) throws ParseException {
        List<String> parsedDates = ParserUtil.parseDates(unparsedDates).stream()
                .map(date -> date.fullDate)
                .collect(Collectors.toList());
        return parsedDates;
    }

    /**
     * Parses {@code List<String> unparsedTags} into a {@code List<String>} of parsed tags.
     * @param unparsedTags The list of tag strings to be parsed.
     * @return The list of tag strings that has the correct format.
     * @throws ParseException if any of the given tags in the list cannot be parsed.
     */
    public static List<String> parseTagsToString(List<String> unparsedTags) throws ParseException {
        List<String> parsedTags = ParserUtil.parseTags(unparsedTags).stream()
                .map(tag -> tag.tagName)
                .collect(Collectors.toList());
        return parsedTags;
    }
    //@@author
}
