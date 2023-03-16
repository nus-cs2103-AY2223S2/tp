package trackr.model.order;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import trackr.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code OrderName} matches any of the keywords given.
 */
public class OrderContainsKeywordsPredicate extends OrderDescriptor implements Predicate<Order> {
    private List<String> orderNameKeywords;

    public OrderContainsKeywordsPredicate() {
        super();
    }

    /**
     * Copy constructor
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

    public boolean isAnyFieldPresent() {
        return isAnyFieldNonNull() || orderNameKeywords != null;
    }

    @Override
    public boolean test(Order order) {
        boolean isOrderNameMatch;
        boolean isOrderDeadlineMatch;
        boolean isOrderQuantityMatch;
        boolean isOrderStatusMatch;
        boolean isCustomerNameMatch;
        boolean isCustomerAddressMatch;
        boolean isCustomerPhoneMatch;

        if (orderNameKeywords != null) {
            return orderNameKeywords.stream()
              .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getOrderName().value, keyword));
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
            isCustomerNameMatch = getCustomerName().get().equals(order.getCustomer().getCustomerName());
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
