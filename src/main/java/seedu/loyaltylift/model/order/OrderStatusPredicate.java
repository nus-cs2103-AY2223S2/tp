package seedu.loyaltylift.model.order;

import java.util.function.Predicate;

/**
 * Tests that an {@code Order}'s latest {@code Status} matches the given status.
 */
public class OrderStatusPredicate implements Predicate<Order> {
    private final StatusValue status;

    public OrderStatusPredicate(StatusValue status) {
        this.status = status;
    }

    @Override
    public boolean test(Order order) {
        return order.getStatus().getLatestStatus().getStatusValue().equals(status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderStatusPredicate // instanceof handles nulls
                && status.equals(((OrderStatusPredicate) other).status)); // state check
    }

}
