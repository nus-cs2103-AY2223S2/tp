package seedu.loyaltylift.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.loyaltylift.commons.exceptions.IllegalValueException;
import seedu.loyaltylift.model.order.Address;
import seedu.loyaltylift.model.order.CreatedDate;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;
import seedu.loyaltylift.model.order.Name;
import seedu.loyaltylift.model.order.Status;
import seedu.loyaltylift.model.order.StatusValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String name;
    private final Integer quantity;
    private final String status;
    private final String address;
    private final String createdDate;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("name") String name, @JsonProperty("phone") Integer quantity,
                            @JsonProperty("email") String status, @JsonProperty("address") String address,
                            @JsonProperty("createdDate") String createdDate) {
        this.name = name;
        this.quantity = quantity;
        this.status = status;
        this.address = address;
        this.createdDate = createdDate;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        name = source.getName().name;
        quantity = source.getQuantity().value;
        status = source.getStatus().value.toString();
        address = source.getAddress().value;
        createdDate = source.getCreatedDate().value.toString();
    }

    /**
     * Converts this Jackson-friendly adapted customer object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(StatusValue.valueOf(status))) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelEmail = new Status(StatusValue.valueOf(status));

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (createdDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, CreatedDate.class.getSimpleName()));
        }

        Date dateObject;
        try {
            dateObject = CreatedDate.DATE_FORMAT.parse(createdDate);
        } catch (ParseException e) {
            throw new IllegalValueException(CreatedDate.MESSAGE_CONSTRAINTS);
        }

        if (!CreatedDate.isValidCreatedDate(dateObject)) {
            throw new IllegalValueException(CreatedDate.MESSAGE_CONSTRAINTS);
        }
        final CreatedDate modelCreatedDate = new CreatedDate(dateObject);

        return new Order(modelName, modelQuantity, modelEmail, modelAddress, modelCreatedDate);
    }
}
