package trackr.model.order;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import trackr.logic.parser.CriteriaEnum;

/**
 * Comparator used to sort orders.
 */
public class SortOrdersComparator implements Comparator<Order> {
    /**
     * The criteria to sort the orders by.
     */
    private CriteriaEnum criteria;

    /**
     * Constructs SortOrdersComparator.
     */
    public SortOrdersComparator() {
        super();
    }

    public void setCriteria(CriteriaEnum criteria) {
        this.criteria = criteria;
    }

    /**
     * Compares two given orders.
     *
     * @param order1 the first order to be compared.
     * @param order2 the second order to be compared.
     * @return -1, 0 or 1 based on the sorting criteria.
     */
    @Override
    public int compare(Order order1, Order order2) {
        requireAllNonNull(order1, order2);

        switch(criteria) {
        case TIME_ADDED:
            return order1.compareTimeAdded(order2);
        case DEADLINE:
            return order1.compareDeadline(order2);
        case STATUS:
            return order1.compareStatus(order2);
        case NAME:
            return order1.compareName(order2);
        case STATUS_AND_DEADLINE:
        default:
            return order1.compareStatusAndDeadline(order2);
        }
    }
}
