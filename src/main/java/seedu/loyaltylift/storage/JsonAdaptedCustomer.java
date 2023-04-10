package seedu.loyaltylift.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.loyaltylift.commons.exceptions.IllegalValueException;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Marked;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;

/**
 * Jackson-friendly version of {@link Customer}.
 */
class JsonAdaptedCustomer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Customer's %s field is missing!";

    private final String customerType;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final Integer points;
    private final Integer cumulativePoints;
    private final Boolean marked;
    private final String note;

    /**
     * Constructs a {@code JsonAdaptedCustomer} with the given customer details.
     */
    @JsonCreator
    public JsonAdaptedCustomer(@JsonProperty("customerType") String customerType, @JsonProperty("name") String name,
                               @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                               @JsonProperty("address") String address,
                               @JsonProperty("points") Integer points,
                               @JsonProperty("cumulativePoints") Integer cumulativePoints,
                               @JsonProperty("marked") Boolean marked,
                               @JsonProperty("note") String note) {
        this.customerType = customerType;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.points = points;
        this.cumulativePoints = cumulativePoints;
        this.marked = marked;
        this.note = note;
    }

    /**
     * Converts a given {@code Customer} into this class for Jackson use.
     */
    public JsonAdaptedCustomer(Customer source) {
        customerType = source.getCustomerType().toString();
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        points = source.getPoints().value;
        cumulativePoints = source.getPoints().cumulative;
        marked = source.getMarked().value;
        note = source.getNote().value;
    }

    /**
     * Converts this Jackson-friendly adapted customer object into the model's {@code Customer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted customer.
     */
    public Customer toModelType() throws IllegalValueException {
        if (customerType == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, CustomerType.class.getSimpleName()));
        }
        final CustomerType modelCustomerType;
        try {
            modelCustomerType = CustomerType.valueOf(customerType); // should not be stored as friendly user string
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(CustomerType.MESSAGE_FAIL_CONVERSION);
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);


        if (points == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Points.class.getSimpleName()));
        }
        if (!Points.isValidPoints(points)) {
            throw new IllegalValueException(Points.MESSAGE_CONSTRAINTS);
        }
        if (cumulativePoints == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Points.class.getSimpleName()));
        }
        if (!Points.isValidPoints(cumulativePoints)) {
            throw new IllegalValueException(Points.MESSAGE_CONSTRAINTS);
        }
        final Points modelPoints = new Points(points, cumulativePoints);

        if (marked == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Marked.class.getSimpleName()));
        }
        final Marked modelMarked = new Marked(marked);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        final Note modelNote = new Note(note);

        return new Customer(modelCustomerType, modelName, modelPhone, modelEmail, modelAddress,
                modelPoints, modelMarked, modelNote);
    }

}
