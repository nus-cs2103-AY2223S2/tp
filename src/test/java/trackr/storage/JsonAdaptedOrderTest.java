package trackr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalCustomer.AMY;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES;

import org.junit.jupiter.api.Test;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;

public class JsonAdaptedOrderTest {

    private static final String INVALID_CUSTOMER_NAME = "R@chel";
    private static final String INVALID_CUSTOMER_PHONE = "+651234";
    private static final String INVALID_CUSTOMER_ADDRESS = " ";
    private static final String INVALID_ORDER_DEADLINE = "00/99/9999";
    private static final String INVALID_ORDER_NAME = " ";
    private static final String INVALID_ORDER_QUANTITY = "9999";
    private static final String INVALID_ORDER_STATUS = "T";

    private static final String VALID_CUSTOMER_NAME = AMY.getCustomerName().toString();
    private static final String VALID_CUSTOMER_PHONE = AMY.getCustomerPhone().toString();
    private static final String VALID_CUSTOMER_ADDRESS = AMY.getCustomerAddress().toString();
    private static final String VALID_ORDER_DEADLINE = CHOCOLATE_COOKIES.getOrderDeadline().toString();
    private static final String VALID_ORDER_NAME = CHOCOLATE_COOKIES.getOrderName().toString();
    private static final String VALID_ORDER_QUANTITY = CHOCOLATE_COOKIES.getOrderQuantity().toString();
    private static final String VALID_ORDER_STATUS = CHOCOLATE_COOKIES.getOrderStatus().toString();

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(CHOCOLATE_COOKIES);
        assertEquals(CHOCOLATE_COOKIES, order.toModelType());
    }

    @Test
    public void toModelType_invalidCustomerName_throwsIllegalValueException() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(INVALID_CUSTOMER_NAME,
                VALID_CUSTOMER_PHONE, VALID_CUSTOMER_ADDRESS,
                VALID_ORDER_NAME, VALID_ORDER_DEADLINE, VALID_ORDER_QUANTITY,
                VALID_ORDER_STATUS);
        String expectedMessage = CustomerName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullCustomerName_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(null, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, VALID_ORDER_NAME, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CustomerName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidCustomerPhone_throwsIllegalValueException() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME,
                INVALID_CUSTOMER_PHONE, VALID_CUSTOMER_ADDRESS,
                VALID_ORDER_NAME, VALID_ORDER_DEADLINE, VALID_ORDER_QUANTITY,
                VALID_ORDER_STATUS);
        String expectedMessage = CustomerPhone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullCustomerPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, null,
                VALID_CUSTOMER_ADDRESS, VALID_ORDER_NAME, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CustomerPhone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidCustomerAddress_throwsIllegalValueException() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME,
                VALID_CUSTOMER_PHONE, INVALID_CUSTOMER_ADDRESS,
                VALID_ORDER_NAME, VALID_ORDER_DEADLINE, VALID_ORDER_QUANTITY,
                VALID_ORDER_STATUS);
        String expectedMessage = CustomerAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullCustomerAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                null, VALID_ORDER_NAME, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CustomerAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderName_throwsIllegalValueException() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, INVALID_ORDER_NAME,
                VALID_ORDER_DEADLINE, VALID_ORDER_QUANTITY, VALID_ORDER_STATUS);
        String expectedMessage = OrderName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderName_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, null, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderDeadline_throwsIllegalValueException() {
        JsonAdaptedOrder task = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, VALID_ORDER_NAME, INVALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS);
        String expectedMessage = OrderDeadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullOrderDeadline_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, VALID_ORDER_NAME, null,
                VALID_ORDER_QUANTITY, VALID_ORDER_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderDeadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderQuantity_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE, VALID_CUSTOMER_ADDRESS,
                VALID_ORDER_NAME, "01/01/2024", INVALID_ORDER_QUANTITY, VALID_ORDER_STATUS);
        String expectedMessage = OrderQuantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderQuantity_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, VALID_ORDER_NAME, "01/01/2024", null,
                VALID_ORDER_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderQuantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderStatus_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, VALID_ORDER_NAME, "01/01/2024",
                VALID_ORDER_QUANTITY, INVALID_ORDER_STATUS);
        String expectedMessage = OrderStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderStatus_throwsIllegalValueException() {
        JsonAdaptedOrder task = new JsonAdaptedOrder(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE,
                VALID_CUSTOMER_ADDRESS, VALID_ORDER_NAME, "01/01/2024",
                VALID_ORDER_QUANTITY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

}
