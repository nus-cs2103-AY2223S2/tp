package seedu.sprint.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.sprint.commons.core.index.Index;
import seedu.sprint.commons.util.StringUtil;
import seedu.sprint.logic.commands.SortCommand;
import seedu.sprint.logic.parser.SortCommandParser.SortingOrder;
import seedu.sprint.logic.parser.SortCommandParser.SortingSequence;
import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.application.CompanyEmail;
import seedu.sprint.model.application.CompanyName;
import seedu.sprint.model.application.Role;
import seedu.sprint.model.application.Status;
import seedu.sprint.model.tag.Tag;
import seedu.sprint.model.task.Deadline;
import seedu.sprint.model.task.Description;

/**
 * Contains utility methods used for parsing strings in the various Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
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
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedName = role.trim();
        if (!Role.isValidRole(trimmedName)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedName);
    }

    /**
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedCompanyName = companyName.trim();
        if (!CompanyName.isValidName(trimmedCompanyName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedCompanyName);
    }

    /**
     * Parses a {@code String companyEmail} into a {@code CompanyEmail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyEmail} is invalid.
     */
    public static CompanyEmail parseCompanyEmail(String companyEmail) throws ParseException {
        requireNonNull(companyEmail);
        String trimmedCompanyEmail = companyEmail.trim();
        if (!CompanyEmail.isValidEmail(trimmedCompanyEmail)) {
            throw new ParseException(CompanyEmail.MESSAGE_CONSTRAINTS);
        }
        return new CompanyEmail(trimmedCompanyEmail);
    }

    /**
     * Parses a {@code String status} into an {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
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
     * Parses a {@code String sortingOrder} into an {@code SortingOrder}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortingOrder} is invalid.
     */
    public static SortingOrder parseSortingOrder(String sortingOrder) throws ParseException {
        requireNonNull(sortingOrder);
        String trimmedSortingOrder = sortingOrder.trim();
        if (!SortingOrder.isValidSortingOrder(trimmedSortingOrder)) {
            throw new ParseException(SortCommand.MESSAGE_CONSTRAINTS);
        }

        return SortingOrder.valueOf(sortingOrder.toUpperCase());
    }

    /**
     * Parses a {@code String sortingSequence} into an {@code SortingSequence}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortingSequence} is invalid.
     */
    public static SortingSequence parseSortingSequence(String sortingSequence) throws ParseException {
        requireNonNull(sortingSequence);
        String trimmedSortingSequence = sortingSequence.trim();
        if (SortingSequence.isValidSortingSequence(trimmedSortingSequence)) {
            if (trimmedSortingSequence.equalsIgnoreCase("a")) {
                return SortingSequence.ASCENDING;
            } else if (trimmedSortingSequence.equalsIgnoreCase("d")) {
                return SortingSequence.DESCENDING;
            }
        }
        throw new ParseException(SortCommand.MESSAGE_CONSTRAINTS);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDate(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }
}
