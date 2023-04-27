package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import trackr.logic.parser.CriteriaEnum;
import trackr.testutil.MenuItemBuilder;
import trackr.testutil.OrderBuilder;

public class SortOrdersComparatorTest {

    @Test
    public void compareTimeAdded() {
        SortOrdersComparator comparator = new SortOrdersComparator();
        comparator.setCriteria(CriteriaEnum.TIME_ADDED);

        LocalDateTime currTime = LocalDateTime.now();
        Order order1 = new OrderBuilder(CHOCOLATE_COOKIES_O).withTimeAdded(currTime).build();
        Order order2 = new OrderBuilder(CHOCOLATE_COOKIES_O).withTimeAdded(currTime.minusDays(1)).build();

        assertEquals(0, comparator.compare(order1, order1));
        assertEquals(1, comparator.compare(order1, order2));
        assertEquals(-1, comparator.compare(order2, order1));
    }

    @Test
    public void compareDeadline() {
        SortOrdersComparator comparator = new SortOrdersComparator();
        comparator.setCriteria(CriteriaEnum.DEADLINE);

        Order order1 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderDeadline("01/01/2023").build();
        Order order2 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderDeadline("01/01/2020").build();

        assertEquals(0, comparator.compare(order1, order1));
        assertEquals(1, comparator.compare(order1, order2));
        assertEquals(-1, comparator.compare(order2, order1));
    }

    @Test
    public void compareStatus() {
        SortOrdersComparator comparator = new SortOrdersComparator();
        comparator.setCriteria(CriteriaEnum.STATUS);

        Order order1 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("D").build();
        Order order2 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("I").build();
        Order order3 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("N").build();

        assertEquals(0, comparator.compare(order1, order1));

        assertEquals(1, comparator.compare(order1, order2));
        assertEquals(1, comparator.compare(order1, order3));
        assertEquals(1, comparator.compare(order2, order3));

        assertEquals(-1, comparator.compare(order2, order1));
        assertEquals(-1, comparator.compare(order3, order1));
        assertEquals(-1, comparator.compare(order3, order2));
    }

    @Test
    public void compareName() {
        SortOrdersComparator comparator = new SortOrdersComparator();
        comparator.setCriteria(CriteriaEnum.NAME);

        Order order1 = new OrderBuilder(CHOCOLATE_COOKIES_O)
                               .withOrderItem(new MenuItemBuilder().withItemName("abc").build())
                               .build();
        Order order2 = new OrderBuilder(CHOCOLATE_COOKIES_O)
                               .withOrderItem(new MenuItemBuilder().withItemName("XYZ").build())
                               .build();

        assertEquals(0, comparator.compare(order1, order1));
        assertEquals(-1, comparator.compare(order1, order2));
        assertEquals(1, comparator.compare(order2, order1));
    }

    @Test
    public void compareStatusAndDeadline() {
        SortOrdersComparator comparator = new SortOrdersComparator();
        comparator.setCriteria(CriteriaEnum.STATUS_AND_DEADLINE);

        Order order1 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("D").build();
        Order order2 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("I").build();
        Order order3 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("N").build();

        assertEquals(0, comparator.compare(order1, order1));

        // Comparison of different status, same deadline
        assertEquals(1, comparator.compare(order1, order2));
        assertEquals(1, comparator.compare(order1, order3));
        assertEquals(1, comparator.compare(order2, order3));

        assertEquals(-1, comparator.compare(order2, order1));
        assertEquals(-1, comparator.compare(order3, order1));
        assertEquals(-1, comparator.compare(order3, order2));

        // Comparison of same status, different deadline
        order1 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderDeadline("01/01/2023").build();
        order2 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderDeadline("01/01/2020").build();

        assertEquals(1, comparator.compare(order1, order2));
        assertEquals(-1, comparator.compare(order2, order1));

        // Comparison of different status, different deadline
        order1 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("D").withOrderDeadline("01/01/2023").build();
        order2 = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("I").withOrderDeadline("01/01/2020").build();

        assertEquals(1, comparator.compare(order1, order2));
        assertEquals(-1, comparator.compare(order2, order1));
    }

}
