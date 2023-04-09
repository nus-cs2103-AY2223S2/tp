package trackr.model.order;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import trackr.commons.util.StringUtil;
import trackr.model.item.Item;

/**
 * Tests that a {@code Order}'s {@code OrderName} matches any of the keywords given.
 */
public class OrderContainsKeywordsPredicate extends OrderDescriptor implements Predicate<Item> {
    private List<String> orderNameKeywords;

    public OrderContainsKeywordsPredicate() {
        super();
    }

    /**
     * Constructs a new {@code OrderContainsKeywordsPredicate} object
     * with the same keywords of order details as the
     * {@code OrderContainsKeywordsPredicate} object specified.
     * @param toCopy The {@code OrderContainsKeywordsPredicate} object
     *               to copy the order name keywords from.
     */
    public OrderContainsKeywordsPredicate(OrderContainsKeywordsPredicate toCopy) {
        setOrderNameKeywords(toCopy.orderNameKeywords);
        setOrderDeadline(toCopy.getOrderDeadline().isPresent() ? toCopy.getOrderDeadline().get() : null);
        setOrderStatus(toCopy.getOrderStatus().isPresent() ? toCopy.getOrderStatus().get() : null);
        setOrderQuantity(toCopy.getOrderQuantity().isPresent() ? toCopy.getOrderQuantity().get() : null);
        setCustomerAddress(toCopy.getCustomerAddress().isPresent() ? toCopy.getCustomerAddress().get() : null);
        setCustomerName(toCopy.getCustomerName().isPresent() ? toCopy.getCustomerName().get() : null);
        setCustomerPhone(toCopy.getCustomerPhone().isPresent() ? toCopy.getCustomerPhone().get() : null);
    }

    public void setOrderNameKeywords(List<String> orderNameKeywords) {
        this.orderNameKeywords = orderNameKeywords;
    }

    public Optional<List<String>> getOrderNameKeywords() {
        return Optional.ofNullable(orderNameKeywords);
    }

    /**
     * Returns true if any of the fields in the {@code Order} object are present or not.
     *
     * @return true if any of the fields in the {@code Order} object are present or not.
     */
    public boolean isAnyFieldPresent() {
        return isAnyFieldNonNull() || orderNameKeywords != null;
    }

    @Override
    public boolean test(Item item) {
        if (!(item instanceof Order)) {
            return false;
        }

        Order order = (Order) item;

        boolean isOrderNameMatch;
        boolean isOrderDeadlineMatch;
        boolean isOrderQuantityMatch;
        boolean isOrderStatusMatch;
        boolean isCustomerNameMatch;
        boolean isCustomerAddressMatch;
        boolean isCustomerPhoneMatch;

        if (orderNameKeywords != null) {
            return orderNameKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getOrderName().getName(), keyword));
        } else {
            isOrderNameMatch = true;
        }

        if (getOrderDeadline().isPresent()) {
            isOrderDeadlineMatch = getOrderDeadline().get().equals(order.getOrderDeadline());
        } else {
            isOrderDeadlineMatch = true;
        }

        if (getOrderQuantity().isPresent()) {
            isOrderQuantityMatch = getOrderQuantity().get().equals(order.getOrderQuantity());
        } else {
            isOrderQuantityMatch = true;
        }

        if (getOrderStatus().isPresent()) {
            isOrderStatusMatch = getOrderStatus().get().equals(order.getOrderStatus());
        } else {
            isOrderStatusMatch = true;
        }

        if (getCustomerName().isPresent()) {
            isCustomerNameMatch = getCustomerName().stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getCustomer()
                    .getCustomerName().getName(), keyword.getName()));
        } else {
            isCustomerNameMatch = true;
        }

        if (getCustomerPhone().isPresent()) {
            isCustomerPhoneMatch = getCustomerPhone().get().equals(order.getCustomer().getCustomerPhone());
        } else {
            isCustomerPhoneMatch = true;
        }

        if (getCustomerAddress().isPresent()) {
            isCustomerAddressMatch = getCustomerAddress().get().equals(order.getCustomer().getCustomerAddress());
        } else {
            isCustomerAddressMatch = true;
        }

        return isOrderNameMatch && isOrderDeadlineMatch && isOrderQuantityMatch && isOrderStatusMatch
                && isCustomerAddressMatch && isCustomerNameMatch && isCustomerPhoneMatch;

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderContainsKeywordsPredicate)) {
            return false;
        }

        OrderContainsKeywordsPredicate predicate = (OrderContainsKeywordsPredicate) other;

        return getCustomerAddress().equals(predicate.getCustomerAddress())
                && getCustomerName().equals(predicate.getCustomerName())
                && getCustomerPhone().equals(predicate.getCustomerPhone())
                && getOrderNameKeywords().equals(predicate.getOrderNameKeywords())
                && getOrderQuantity().equals(predicate.getOrderQuantity())
                && getOrderStatus().equals(predicate.getOrderStatus())
                && getOrderDeadline().equals(predicate.getOrderDeadline());
    }
}
