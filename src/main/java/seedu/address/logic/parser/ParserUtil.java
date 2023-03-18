package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.todo.ApplicationDeadline;
import seedu.address.model.todo.NoteContent;

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
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedName = companyName.trim();
        if (!CompanyName.isValidName(trimmedName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
    }

    /**
     * Parses a {@code String jobTitle} into a {@code JobTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobTitle} is invalid.
     */
    public static JobTitle parseJobTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();
        if (!JobTitle.isValidJobTitle(trimmedJobTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(trimmedJobTitle);
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

    /**
     * Parses a {@code String status} into a {@code InternshipStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static InternshipStatus parseInternshipStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!InternshipStatus.isValidStatus(trimmedStatus)) {
            throw new ParseException(InternshipStatus.MESSAGE_CONSTRAINTS);
        }
        return InternshipStatus.valueOf(trimmedStatus);
    }

    /**
     * Parses a {@code String deadline} into a {@code ApplicationDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static ApplicationDeadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);

        String trimmedDeadline = deadline.trim();
        LocalDate formattedDate = LocalDate.parse(trimmedDeadline);

        if (!ApplicationDeadline.isValidDate(formattedDate)) {
            throw new ParseException(ApplicationDeadline.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationDeadline(formattedDate);
    }

    /**
     * Parses a {@code String content} into a {@code NoteContent}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code content} is invalid.
     */
    public static NoteContent parseContent(String content) throws ParseException {
        requireNonNull(content);

        String trimmedContent = content.trim();

        if (!NoteContent.isValidContent(content)) {
            throw new ParseException(NoteContent.MESSAGE_CONSTRAINTS);
        }
        return new NoteContent(trimmedContent);
    }
}
