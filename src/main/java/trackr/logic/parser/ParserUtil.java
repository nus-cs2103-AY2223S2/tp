package trackr.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import trackr.commons.core.index.Index;
import trackr.commons.util.StringUtil;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.TabEnum;
import trackr.model.commons.Name;
import trackr.model.commons.Tag;
import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonEmail;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_TAB = "No such tab.";
    public static final String MESSAGE_INVALID_CRITERIA = "Criteria given must be one of the types: "
            + "`Time_added`, `Deadline`, `Status`, `Name`, `Status_and_Deadline` or blank";
    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
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
    public static PersonName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(PersonName.MESSAGE_CONSTRAINTS);
        }
        return new PersonName(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static PersonPhone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!PersonPhone.isValidPersonPhone(trimmedPhone)) {
            throw new ParseException(PersonPhone.MESSAGE_CONSTRAINTS);
        }
        return new PersonPhone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static PersonAddress parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!PersonAddress.isValidPersonAddress(trimmedAddress)) {
            throw new ParseException(PersonAddress.MESSAGE_CONSTRAINTS);
        }
        return new PersonAddress(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static PersonEmail parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!PersonEmail.isValidPersonEmail(trimmedEmail)) {
            throw new ParseException(PersonEmail.MESSAGE_CONSTRAINTS);
        }
        return new PersonEmail(trimmedEmail);
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
     * Parses a {@code Optional<String> criteria} into a {@code CriteriaEnum}.
     *
     * @param criteria The {@code Optional<String>} representation of sorting criteria.
     * @throws ParseException If given {@code criteria} is empty
     *                        or cannot be parsed into a valid CriteriaEnum.
     */
    public static CriteriaEnum parseSortingCriteria(Optional<String> criteria) throws ParseException {
        requireNonNull(criteria);
        if (!criteria.isPresent()) {
            return CriteriaEnum.STATUS_AND_DEADLINE;
        }

        String trimmedCriteria = criteria.get().trim().toUpperCase();
        try {
            return CriteriaEnum.valueOf(trimmedCriteria);
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_INVALID_CRITERIA);
        }
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
        if (!TaskName.isValidName(trimmedTaskName)) {
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
        if (!TaskDeadline.isValidDeadline(trimmedTaskDeadline)) {
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
        if (!TaskStatus.isValidStatus(trimmedTaskStatus, TaskStatus.STATUSES)) {
            throw new ParseException(TaskStatus.MESSAGE_CONSTRAINTS);
        }
        return new TaskStatus(trimmedTaskStatus);
    }
    //========================Parse those related to menu item==================================

    /**
     * Parses a {@code String itemName} into a {@code ItemName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code itemName} is invalid.
     */
    public static ItemName parseItemName(String itemName) throws ParseException {
        requireNonNull(itemName);
        String trimmedItemName = itemName.trim();
        if (!ItemName.isValidName(trimmedItemName)) {
            throw new ParseException(ItemName.MESSAGE_CONSTRAINTS);
        }
        return new ItemName(trimmedItemName);
    }

    /**
     * Parses a {@code String itemPrice} into a {@code ItemPrice}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code itemPrice} is invalid.
     */
    public static ItemSellingPrice parseItemPrice(String itemPrice) throws ParseException {
        requireNonNull(itemPrice);
        String trimmedItemPrice = itemPrice.trim();
        if (!ItemSellingPrice.isValidPrice(trimmedItemPrice)) {
            throw new ParseException(ItemSellingPrice.MESSAGE_CONSTRAINTS);
        }
        return new ItemSellingPrice(trimmedItemPrice);
    }

    /**
     * Parses a {@code String itemCost} into a {@code ItemCost}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code itemCost} is invalid.
     */
    public static ItemCost parseItemCost(String itemCost) throws ParseException {
        requireNonNull(itemCost);
        String trimmedItemCost = itemCost.trim();
        if (!ItemCost.isValidPrice(trimmedItemCost)) {
            throw new ParseException(ItemCost.MESSAGE_CONSTRAINTS);
        }
        return new ItemCost(trimmedItemCost);
    }

    //========================Parse those related to order==================================

    /**
     * Parses a {@code String orderName} into a {@code OrderName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderName} is invalid.
     */
    public static OrderName parseOrderName(String orderName) throws ParseException {
        requireNonNull(orderName);
        String trimmedTaskName = orderName.trim();
        if (!OrderName.isValidName(trimmedTaskName)) {
            throw new ParseException(OrderName.MESSAGE_CONSTRAINTS);
        }
        return new OrderName(trimmedTaskName);
    }

    /**
     * Parses a {@code String OrderDeadline} into a {@code OrderDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderDeadline} is invalid.
     */
    public static OrderDeadline parseOrderDeadline(String orderDeadline) throws ParseException {
        requireNonNull(orderDeadline);
        String trimmedOrderDeadline = orderDeadline.trim();
        if (!OrderDeadline.isValidDeadline(trimmedOrderDeadline)) {
            throw new ParseException(String.format(OrderDeadline.MESSAGE_CONSTRAINTS, "Order"));
        }
        return new OrderDeadline(trimmedOrderDeadline);
    }

    /**
     * Parses a {@code Optional<String> OrderStatus} into a {@code OrderStatus}.
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
        if (!OrderStatus.isValidStatus(trimmedOrderStatus, OrderStatus.STATUSES)) {
            throw new ParseException(OrderStatus.MESSAGE_CONSTRAINTS);
        }
        return new OrderStatus(trimmedOrderStatus);
    }

    /**
     * Parses a {@code String OrderQuantity} into a {@code OrderQuantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderQuantity} is invalid.
     */
    public static OrderQuantity parseOrderQuantity(String orderQuantity) throws ParseException {
        requireNonNull(orderQuantity);
        String trimmedOrderQuantity = orderQuantity.trim();
        if (!OrderQuantity.isValidQuantity(trimmedOrderQuantity)) {
            throw new ParseException(OrderQuantity.MESSAGE_CONSTRAINTS);
        }
        return new OrderQuantity(trimmedOrderQuantity);
    }

    /**
     * Parses a {@code String CustomerName} into a {@code CustomerName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code customerName} is invalid.
     */
    public static PersonName parseCustomerName(String customerName) throws ParseException {
        requireNonNull(customerName);
        String trimmedCustomerName = customerName.trim();
        if (!PersonName.isValidName(trimmedCustomerName)) {
            throw new ParseException(PersonName.MESSAGE_CONSTRAINTS);
        }
        return new PersonName(trimmedCustomerName);
    }

    /**
     * Parses a {@code String CustomerPhone} into a {@code CustomerPhone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code customerPhone} is invalid.
     */
    public static PersonPhone parseCustomerPhone(String customerPhone) throws ParseException {
        requireNonNull(customerPhone);
        String trimmedCustomerPhone = customerPhone.trim();
        if (!PersonPhone.isValidPersonPhone(trimmedCustomerPhone)) {
            throw new ParseException(PersonPhone.MESSAGE_CONSTRAINTS);
        }
        return new PersonPhone(trimmedCustomerPhone);
    }

    /**
     * Parses a {@code String CustomerAddress} into a {@code CustomerAddress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code customerAddress} is invalid.
     */
    public static PersonAddress parseCustomerAddress(String customerAddress) throws ParseException {
        requireNonNull(customerAddress);
        String trimmedCustomerAddress = customerAddress.trim();
        if (!PersonAddress.isValidPersonAddress(trimmedCustomerAddress)) {
            throw new ParseException(PersonAddress.MESSAGE_CONSTRAINTS);
        }
        return new PersonAddress(trimmedCustomerAddress);
    }

    //========================Parse those related to tab==================================
    /**
     * Parses {@code targetTabName} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified {@code targetTabName} is invalid (not a recognised Tab).
     */
    public static Index parseTab(String targetTabName) throws ParseException {
        String trimmedTab = targetTabName.trim();
        int tabIndex;
        try {
            tabIndex = TabEnum.getTabIndex(trimmedTab);
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_INVALID_TAB);
        }
        return Index.fromZeroBased(tabIndex);
    }

}
