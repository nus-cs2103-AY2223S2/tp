package trackr.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.person.Customer;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Jackson-friendly version of {@link Order}.
 */
public class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";
    public static final String MESSAGE_PARSE_TIME_ADDED_ERROR =
            "Unexpected error encountered when parsing Task's `timeAdded` "
                    + "field that was read from storage file";

    private final String customerName;
    private final String customerPhone;
    private final String customerAddress;
    private final String orderName;
    private final String orderDeadline;
    private final String orderQuantity;
    private final String orderStatus;
    private final String timeAdded;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("customerName") String customerName,
                           @JsonProperty("customerPhone") String customerPhone,
                           @JsonProperty("customerAddress") String customerAddress,
                           @JsonProperty("orderName") String orderName,
                           @JsonProperty("orderDeadline") String orderDeadline,
                           @JsonProperty("orderQuantity") String orderQuantity,
                           @JsonProperty("orderStatus") String orderStatus,
                            @JsonProperty("timeAdded") String timeAdded) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.orderName = orderName;
        this.orderDeadline = orderDeadline;
        this.orderQuantity = orderQuantity;
        this.orderStatus = orderStatus;
        this.timeAdded = timeAdded;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        customerName = source.getCustomer().getCustomerName().getName();
        customerPhone = source.getCustomer().getCustomerPhone().personPhone;
        customerAddress = source.getCustomer().getCustomerAddress().personAddress;
        orderName = source.getOrderName().getName();
        orderDeadline = source.getOrderDeadline().toJsonString();
        orderQuantity = source.getOrderQuantity().value;
        orderStatus = source.getOrderStatus().toJsonString();
        timeAdded = source.getTimeAdded().toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (customerName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PersonName.class.getSimpleName()));
        }
        if (!PersonName.isValidName(customerName)) {
            throw new IllegalValueException(PersonName.MESSAGE_CONSTRAINTS);
        }
        final PersonName modelName = new PersonName(customerName);

        if (customerPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PersonPhone.class.getSimpleName()));
        }
        if (!PersonPhone.isValidPersonPhone(customerPhone)) {
            throw new IllegalValueException(PersonPhone.MESSAGE_CONSTRAINTS);
        }
        final PersonPhone modelPhone = new PersonPhone(customerPhone);

        if (customerAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PersonAddress.class.getSimpleName()));
        }
        if (!PersonAddress.isValidPersonAddress(customerAddress)) {
            throw new IllegalValueException(PersonAddress.MESSAGE_CONSTRAINTS);
        }
        final PersonAddress modelAddress = new PersonAddress(customerAddress);

        if (orderName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderName.class.getSimpleName()));
        }
        if (!OrderName.isValidName(orderName)) {
            throw new IllegalValueException(OrderName.MESSAGE_CONSTRAINTS);
        }
        final OrderName modelOrderName = new OrderName(orderName);

        if (orderDeadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderDeadline.class.getSimpleName()));
        }
        if (!OrderDeadline.isValidDeadline(orderDeadline)) {
            throw new IllegalValueException(OrderDeadline.MESSAGE_CONSTRAINTS);
        }
        final OrderDeadline modelOrderDeadline = new OrderDeadline(orderDeadline);

        if (orderQuantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderQuantity.class.getSimpleName()));
        }
        if (!OrderQuantity.isValidQuantity(orderQuantity)) {
            throw new IllegalValueException(OrderQuantity.MESSAGE_CONSTRAINTS);
        }
        final OrderQuantity modelOrderQuantity = new OrderQuantity(orderQuantity);

        if (orderStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderStatus.class.getSimpleName()));
        }
        if (!OrderStatus.isValidStatus(orderStatus, OrderStatus.STATUSES)) {
            throw new IllegalValueException(OrderStatus.MESSAGE_CONSTRAINTS);
        }
        final OrderStatus modelOrderStatus = new OrderStatus(orderStatus);

        final LocalDateTime modelTimeAdded;
        try {
            modelTimeAdded = LocalDateTime.parse(timeAdded);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new IllegalValueException(MESSAGE_PARSE_TIME_ADDED_ERROR);
        }

        Customer customer = new Customer(modelName, modelPhone, modelAddress);
        return new Order(modelOrderName, modelOrderDeadline,
                modelOrderStatus, modelOrderQuantity, customer, modelTimeAdded);
    }
}
