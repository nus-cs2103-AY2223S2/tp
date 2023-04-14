package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.IndexException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Address;
import seedu.address.model.module.Deadline;
import seedu.address.model.module.Name;
import seedu.address.model.module.Remark;
import seedu.address.model.module.Resource;
import seedu.address.model.module.Teacher;
import seedu.address.model.module.TimeSlot;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Invalid index.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            //Index is valid (>0 and also does not overflow)
            return Index.fromOneBased(Integer.parseInt(trimmedIndex));
        } else if (trimmedIndex.matches("^-?\\d+$")) {
            //Index contains:
            // negative sign (optional) which suggests it is not valid since it is not >0
            // digits only which suggests it overflowed, and is not valid either
            throw new IndexException(MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        //Index is not valid (possibly because there are incorrect prefixes)
        throw new ParseException((MESSAGE_INVALID_INDEX));
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
     * Parses a {@code String resource} into a {@code Resource}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static Resource parseResource(String resource) throws ParseException {
        requireNonNull(resource);
        String trimmedResource = resource.trim();
        if (!Resource.isValidResource(trimmedResource)) {
            throw new ParseException(Resource.MESSAGE_CONSTRAINTS);
        }
        return new Resource(trimmedResource);
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
     * Parses a {@code String timeSlot} into an {@code TimeSlot}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeSlot} is invalid.
     */
    public static TimeSlot parseTimeSlot(String timeSlot) throws ParseException {
        requireNonNull(timeSlot);
        String trimmedTimeSlot = timeSlot.trim();
        if (!TimeSlot.isValidTimeSlot(trimmedTimeSlot)) {
            throw new ParseException(TimeSlot.MESSAGE_CONSTRAINTS);
        } else if (!TimeSlot.isStartTimeBeforeEndTime(trimmedTimeSlot)) {
            throw new ParseException((TimeSlot.MESSAGE_STARTTIME_BEFORE_ENDTIME));
        }
        return new TimeSlot(trimmedTimeSlot);
    }

    /**
     * Parses a {@code String deadline} into an {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidFormat(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS_INVALID_DATE_FORMAT);
        } else if (!Deadline.isValidDate(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS_INVALID_DATE);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String teacher} into an {@code Teacher}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static Teacher parseTeacher(String teacher) throws ParseException {
        requireNonNull(teacher);
        String trimmedTeacher = teacher.trim();
        if (!Teacher.isValidTeacher(trimmedTeacher)) {
            throw new ParseException(Teacher.MESSAGE_CONSTRAINTS);
        }
        return new Teacher(trimmedTeacher);
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
}
