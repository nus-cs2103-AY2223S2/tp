package seedu.techtrack.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.techtrack.commons.core.index.Index;
import seedu.techtrack.commons.util.StringUtil;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;
import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Deadline;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.JobDescription;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Contact;
import seedu.techtrack.model.role.Salary;
import seedu.techtrack.model.role.Website;
import seedu.techtrack.model.util.tag.Tag;

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
     * Parses a {@code String phone} into a {@code Contact}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Contact parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Contact.isValidContact(trimmedPhone)) {
            throw new ParseException(Contact.MESSAGE_CONSTRAINTS);
        }
        return new Contact(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Company parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Company.isValidCompany(trimmedAddress)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedAddress);
    }

    /**
     * Parses a {@code String jd} into an {@code JobDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code JobDescription} is invalid.
     */
    public static JobDescription parseJobDescription(String jd) throws ParseException {
        requireNonNull(jd);
        String trimmedJobDescription = jd.trim();
        if (!JobDescription.isValidJobDescription(trimmedJobDescription)) {
            throw new ParseException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        return new JobDescription(trimmedJobDescription);
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
     * Parses a {@code String Website} into a {@code Website}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Website parseWebsite(String website) throws ParseException {
        requireNonNull(website);
        String trimmedWebsite = website.trim();
        if (!Website.isValidWebsite(trimmedWebsite)) {
            throw new ParseException(Website.MESSAGE_CONSTRAINTS);
        }
        return new Website(trimmedWebsite);
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
     * Parses a {@code String Salary} into an {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }

    /**
     * Parses a {@code String Date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Deadline parseDateline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.doesNotExist(trimmedDeadline)) {
            throw new ParseException(Deadline.getMessageConstraint());
        } else if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.getMessageConstraint());
        } else if (!Deadline.isNotPassed(trimmedDeadline)) {
            throw new ParseException(Deadline.getMessageConstraint());
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String Experience} into an {@code experience}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code experience} is invalid.
     */
    public static Experience parseExperience(String experience) throws ParseException {
        requireNonNull(experience);
        String trimmedExperience = experience.trim();
        if (!Experience.isValidExperience(trimmedExperience)) {
            throw new ParseException(Experience.getMessageConstraint());
        }
        return new Experience(trimmedExperience);
    }

    /**
     * Parses {@code String order} into an {@code order}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code order} is invalid.
     */
    public static OrderParser parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim();
        if (!OrderParser.isValidOrder(trimmedOrder)) {
            throw new ParseException(OrderParser.MESSAGE_CONSTRAINTS);
        }
        return new OrderParser(trimmedOrder);
    }
}
