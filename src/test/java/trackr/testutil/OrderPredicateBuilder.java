package trackr.testutil;

import java.util.Arrays;
import java.util.List;

import trackr.model.order.OrderContainsKeywordsPredicate;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;

/**
 * OrderPredicateBuilder class
 */
public class OrderPredicateBuilder {

    private OrderContainsKeywordsPredicate orderPredicate;

    public OrderPredicateBuilder() {
        orderPredicate = new OrderContainsKeywordsPredicate();
    }

    public OrderPredicateBuilder(OrderContainsKeywordsPredicate orderPredicate) {
        this.orderPredicate = new OrderContainsKeywordsPredicate(orderPredicate);
    }

    /**
     * Sets the {@code OrderNameKeywords} of the {@code OrderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withOrderNameKeywords(List<String> orderNameKeywords) {
        if (orderNameKeywords != null) {
            orderPredicate.setOrderNameKeywords(orderNameKeywords);
        }
        return this;
    }

    /**
     * Sets the {@code OrderNameKeywords} of the {@code OrderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withOrderNameKeywords(String orderNameKeywords) {
        if (orderNameKeywords != null) {
            orderPredicate.setOrderNameKeywords(Arrays.asList(orderNameKeywords.trim().split("\\s+")));
        }
        return this;
    }

    /**
     * Sets the {@code OrderNameKeywords} of the {@code OrderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withOrderName(String orderName) {
        if (orderName != null) {
            orderPredicate.setOrderName(new OrderName(orderName));
        }
        return this;
    }

    /**
     * Sets the {@code orderDeadline} of the {@code orderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withOrderDeadline(String orderDeadline) {
        if (orderDeadline != null) {
            orderPredicate.setOrderDeadline(new OrderDeadline(orderDeadline));
        }
        return this;
    }

    /**
     * Sets the {@code orderStatus} of the {@code orderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withOrderStatus(String orderStatus) {
        if (orderStatus != null) {
            orderPredicate.setOrderStatus(new OrderStatus(orderStatus));
        }
        return this;
    }

    /**
     * Sets the {@code orderQuantity} of the {@code orderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withOrderQuantity(String orderQuantity) {
        if (orderQuantity != null) {
            orderPredicate.setOrderQuantity(new OrderQuantity(orderQuantity));
        }
        return this;
    }

    /**
     * Sets the {@code orderStatus} of the {@code orderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withCustomerName(String customerName) {
        if (customerName != null) {
            orderPredicate.setCustomerName(new CustomerName(customerName));
        }
        return this;
    }

    /**
     * Sets the {@code orderStatus} of the {@code orderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withCustomerPhone(String customerPhone) {
        if (customerPhone != null) {
            orderPredicate.setCustomerPhone(new CustomerPhone(customerPhone));
        }
        return this;
    }

    /**
     * Sets the {@code orderStatus} of the {@code orderContainsKeywordsPredicate} that we are building.
     */
    public OrderPredicateBuilder withCustomerAddress(String customerAddress) {
        if (customerAddress != null) {
            orderPredicate.setCustomerAddress(new CustomerAddress(customerAddress));
        }
        return this;
    }

    public OrderContainsKeywordsPredicate build() {
        return orderPredicate;
    }
}
