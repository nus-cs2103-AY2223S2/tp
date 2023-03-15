package trackr.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import trackr.commons.core.index.Index;
import trackr.commons.util.StringUtil;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.supplier.Address;
import trackr.model.supplier.Email;
import trackr.model.supplier.Name;
import trackr.model.supplier.Phone;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderStatus;

import trackr.model.tag.Tag;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

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

    //========================Parse those related to task==================================
    /**
     * Parses a {@code String taskName} into a {@code TaskName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskName} is invalid.
     */
    public static TaskName parseTaskName(String taskName) throws ParseException {
        requireNonNull(taskName);
        String trimmedTaskName = taskName.trim();
        if (!TaskName.isValidTaskName(trimmedTaskName)) {
            throw new ParseException(TaskName.MESSAGE_CONSTRAINTS);
        }
        return new TaskName(trimmedTaskName);
    }

    /**
     * Parses a {@code String taskDeadline} into a {@code TaskDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskDeadline} is invalid.
     */
    public static TaskDeadline parseTaskDeadline(String taskDeadline) throws ParseException {
        requireNonNull(taskDeadline);
        String trimmedTaskDeadline = taskDeadline.trim();
        if (!TaskDeadline.isValidTaskDeadline(trimmedTaskDeadline)) {
            throw new ParseException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        return new TaskDeadline(trimmedTaskDeadline);
    }

    /**
     * Parses a {@code String taskStatus} into a {@code TaskStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskStatus} is invalid.
     */
    public static TaskStatus parseTaskStatus(Optional<String> taskStatus) throws ParseException {
        requireNonNull(taskStatus);
        if (!taskStatus.isPresent()) {
            return new TaskStatus();
        }

        String trimmedTaskStatus = taskStatus.get().trim();
        if (!TaskStatus.isValidTaskStatus(trimmedTaskStatus)) {
            throw new ParseException(TaskStatus.MESSAGE_CONSTRAINTS);
        }
        return new TaskStatus(trimmedTaskStatus);
    }

    /**
     * Parses a {@code String orderName} into a {@code OrderName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderName} is invalid.
     */
    public static OrderName parseOrderName(String orderName) throws ParseException {
        requireNonNull(orderName);
        String trimmedOrderName = orderName.trim();
        if (!OrderName.isValidOrderName(trimmedOrderName)) {
            throw new ParseException(OrderName.MESSAGE_CONSTRAINTS);
        }
        return new OrderName(trimmedOrderName);
    }

    /**
     * Parses a {@code String orderDeadline} into a {@code OrderDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderDeadline} is invalid.
     */
    public static OrderDeadline parseOrderDeadline(String orderDeadline) throws ParseException {
        requireNonNull(orderDeadline);
        String trimmedOrderDeadline = orderDeadline.trim();
        if (!OrderDeadline.isValidOrderDeadline(trimmedOrderDeadline)) {
            throw new ParseException(OrderDeadline.MESSAGE_CONSTRAINTS);
        }
        return new OrderDeadline(trimmedOrderDeadline);
    }

    /**
     * Parses a {@code String orderStatus} into a {@code OrderStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderStatus} is invalid.
     */
    public static OrderStatus parseOrderStatus(Optional<String> orderStatus) throws ParseException {
        requireNonNull(orderStatus);
        if (!orderStatus.isPresent()) {
            return new OrderStatus();
        }

        String trimmedOrderStatus = orderStatus.get().trim();
        if (!OrderStatus.isValidOrderStatus(trimmedOrderStatus)) {
            throw new ParseException(OrderStatus.MESSAGE_CONSTRAINTS);
        }
        return new OrderStatus(trimmedOrderStatus);
    }

}
