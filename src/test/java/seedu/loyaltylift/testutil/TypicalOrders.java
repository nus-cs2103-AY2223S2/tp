package seedu.loyaltylift.testutil;

import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_CREATED_DATE_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_CREATED_DATE_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_QUANTITY_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_QUANTITY_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_STATUS_A_PAID_DATE;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_STATUS_A_PENDING_DATE;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_STATUS_B_PENDING_DATE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.loyaltylift.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in
 * tests.
 */
public class TypicalOrders {
    // Manually added - Order's details found in {@code CommandTestUtil}
    public static final Order ORDER_A = new OrderBuilder().withName(VALID_NAME_A).withQuantity(VALID_QUANTITY_A)
            .withInitialStatus(VALID_STATUS_A_PENDING_DATE).withNextStatus(VALID_STATUS_A_PAID_DATE)
            .withAddress(VALID_ADDRESS_A).withCreatedDate(VALID_CREATED_DATE_A).build();
    public static final Order ORDER_B = new OrderBuilder().withName(VALID_NAME_B).withQuantity(VALID_QUANTITY_B)
            .withInitialStatus(VALID_STATUS_B_PENDING_DATE).withAddress(VALID_ADDRESS_B).withCreatedDate(VALID_CREATED_DATE_B).build();

    // Manually added
    public static final Order ORDER_C = new OrderBuilder().withName("Melon Cookie")
            .withAddress("9 Bishan Rd, Singapore 310909").withInitialStatus("2022/12/09")
            .withNextStatus("2023/02/02").withNextStatus("2023/02/15")
            .withQuantity("50").withCreatedDate("2022/12/20").build();
    public static final Order ORDER_D = new OrderBuilder().withName("Strawberry Shortcake")
            .withAddress("9 Bishan Rd, Singapore 310909")
            .withInitialStatus("2022/10/11").withQuantity("3")
            .withCreatedDate("2022/12/12").build();

    public static final String KEYWORD_MATCHING_SHORTCAKE = "Shortcake"; // A keyword that matches Shortcake

    private TypicalOrders() {
    } // prevents instantiation

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_A, ORDER_B, ORDER_C, ORDER_D));
    }
}
