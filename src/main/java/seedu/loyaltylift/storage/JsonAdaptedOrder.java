package seedu.loyaltylift.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.loyaltylift.commons.exceptions.IllegalValueException;
import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.exceptions.CustomerNotFoundException;
import seedu.loyaltylift.model.order.CreatedDate;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;
import seedu.loyaltylift.model.order.Status;
import seedu.loyaltylift.model.order.StatusUpdate;

/**
 * Jackson-friendly version of {@link Order}.
 */
public class JsonAdaptedOrder {

    public static final String CUSTOMER_ID_MESSAGE_FIELD = "Customer ID";
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";
    public static final String NOT_FOUND_FIELD_MESSAGE_FORMAT = "Order's %s cannot be found!";

    private final String customerId;
    private final String name;
    private final Integer quantity;
    private final List<JsonAdaptedStatusUpdate> statusUpdates = new ArrayList<>();
    private final String address;
    private final String createdDate;
    private final String note;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("customerId") String customerId,
                            @JsonProperty("name") String name,
                            @JsonProperty("phone") Integer quantity,
                            @JsonProperty("statusUpdates") List<JsonAdaptedStatusUpdate> statusUpdates,
                            @JsonProperty("address") String address,
                            @JsonProperty("createdDate") String createdDate,
                            @JsonProperty("note") String note) {
        this.customerId = customerId;
        this.name = name;
        this.quantity = quantity;
        this.address = address;
        this.createdDate = createdDate;
        this.note = note;
        if (statusUpdates != null) {
            this.statusUpdates.addAll(statusUpdates);
        }
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        customerId = source.getCustomer().getUid();
        name = source.getName().fullName;
        quantity = source.getQuantity().value;
        statusUpdates.addAll(source.getStatus().getStatusUpdates().stream()
                .map(JsonAdaptedStatusUpdate::new)
                .collect(Collectors.toList()));
        address = source.getAddress().value;
        createdDate = source.getCreatedDate().toString();
        note = source.getNote().value;
    }

    /**
     * Converts this Jackson-friendly adapted customer object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType(AddressBook addressBook) throws IllegalValueException {
        if (customerId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, CUSTOMER_ID_MESSAGE_FIELD));
        }
        Customer customer;
//        Customer customer = addressBook.getCustomer(customerId);
        try {
            customer = addressBook.getCustomer(customerId);
        } catch (CustomerNotFoundException e) {
            throw new IllegalValueException(String.format(NOT_FOUND_FIELD_MESSAGE_FORMAT, CUSTOMER_ID_MESSAGE_FIELD));
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (quantity == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        final List<StatusUpdate> modelStatusUpdates = new ArrayList<>();
        for (JsonAdaptedStatusUpdate statusUpdate : statusUpdates) {
            modelStatusUpdates.add(statusUpdate.toModelType());
        }
        if (!Status.isValidStatus(modelStatusUpdates)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(modelStatusUpdates);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (createdDate == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, CreatedDate.class.getSimpleName()));
        }

        LocalDate dateObject;
        try {
            dateObject = LocalDate.parse(createdDate, CreatedDate.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(CreatedDate.MESSAGE_CONSTRAINTS);
        }

        if (!CreatedDate.isValidCreatedDate(dateObject)) {
            throw new IllegalValueException(CreatedDate.MESSAGE_CONSTRAINTS);
        }
        final CreatedDate modelCreatedDate = new CreatedDate(dateObject);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        final Note modelNote = new Note(note);

        return new Order(customer, modelName, modelQuantity, modelAddress, modelStatus, modelCreatedDate, modelNote);
    }
}
