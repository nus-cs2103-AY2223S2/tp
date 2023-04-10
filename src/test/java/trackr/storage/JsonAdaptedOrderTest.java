package trackr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalCustomer.AMY;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.menu.MenuItem;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;


public class JsonAdaptedOrderTest {
    //@@author chongweiguan-reused
    private static final String INVALID_CUSTOMER_NAME = "R@chel";
    private static final String INVALID_CUSTOMER_PHONE = "+651234";
    private static final String INVALID_CUSTOMER_ADDRESS = " ";
    private static final String INVALID_ORDER_DEADLINE = "00/99/9999";
    private static final String INVALID_ORDER_QUANTITY = "9999";
    private static final String INVALID_ORDER_STATUS = "T";
    //@@author
    private static final String INVALID_TIME_ADDED = "99/99/9999";

    //@@author chongweiguan-reused
    private static final String VALID_CUSTOMER_NAME = AMY.getCustomerName().toString();
    private static final String VALID_CUSTOMER_PHONE = AMY.getCustomerPhone().toString();
    private static final String VALID_CUSTOMER_ADDRESS = AMY.getCustomerAddress().toString();
    //@@author
    private static final MenuItem VALID_ORDER_ITEM = CHOCOLATE_COOKIES_O.getOrderItem();
    private static final String VALID_ORDER_DEADLINE = "01/01/2024";
    private static final String VALID_ORDER_QUANTITY = CHOCOLATE_COOKIES_O.getOrderQuantity().getValue();
    private static final String VALID_ORDER_STATUS = CHOCOLATE_COOKIES_O.getOrderStatus().toJsonString();
    private static final String VALID_TIME_ADDED = LocalDateTime.now().toString();

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(CHOCOLATE_COOKIES_O);
        assertEquals(CHOCOLATE_COOKIES_O, order.toModelType());
    }

    //@@author chongweiguan-reused
    @Test
    public void toModelType_invalidCustomerName_throwsIllegalValueException() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(INVALID_CUSTOMER_NAME,
                VALID_CUSTOMER_PHONE, VALID_CUSTOMER_ADDRESS,
                new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE, VALID_ORDER_QUANTITY,
                VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = PersonName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullCustomerName_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(null, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PersonName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidCustomerPhone_throwsIllegalValueException() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME,
                INVALID_CUSTOMER_PHONE, VALID_CUSTOMER_ADDRESS,
                new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE, VALID_ORDER_QUANTITY,
                VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = PersonPhone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullCustomerPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, null,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PersonPhone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidCustomerAddress_throwsIllegalValueException() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME,
                VALID_CUSTOMER_PHONE, INVALID_CUSTOMER_ADDRESS,
                new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE, VALID_ORDER_QUANTITY,
                VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = PersonAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullCustomerAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                null, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PersonAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderItem_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, null, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderDeadline_throwsIllegalValueException() {
        JsonAdaptedOrder task = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), INVALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = OrderDeadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullOrderDeadline_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), null,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderDeadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderQuantity_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE, VALID_CUSTOMER_ADDRESS,
                        new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE,
                        INVALID_ORDER_QUANTITY, VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = OrderQuantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderQuantity_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE, null,
                VALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderQuantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderStatus_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, INVALID_ORDER_STATUS, VALID_TIME_ADDED);
        String expectedMessage = OrderStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
    //@@author

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with modifications
    @Test
    public void toModelType_nullOrderStatus_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, null, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidTimeAdded_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS, INVALID_TIME_ADDED);
        String expectedMessage = JsonAdaptedOrder.MESSAGE_PARSE_TIME_ADDED_ERROR;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullTimeAdded_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, new JsonAdaptedMenuItem(VALID_ORDER_ITEM), VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS, null);
        String expectedMessage = JsonAdaptedOrder.MESSAGE_PARSE_TIME_ADDED_ERROR;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
    //@@author
}
