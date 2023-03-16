package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CsvUtil;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Csv-friendly version of {@link Person}.
 */
class CsvAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String income;
    private final List<CsvAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code CsvAdaptedPerson} with the given person details.
     */
    public CsvAdaptedPerson(String str) {
        String[] tokens = str.split(",");
        this.name = tokens[0];
        this.phone = tokens[1];
        this.email = tokens[2];
        this.address = tokens[3];
        this.income = tokens[4];
        for (int i = 5; i < tokens.length; ++i) {
            this.tagged.add(new CsvAdaptedTag(tokens[i]));
        }
    }

    /**
     * Converts a given {@code Person} into this class for csv use.
     */
    public CsvAdaptedPerson(Person source) {
        name = CsvUtil.toCsvField(source.getName().fullName);
        phone = CsvUtil.toCsvField(source.getPhone().value);
        email = CsvUtil.toCsvField(source.getEmail().value);
        address = CsvUtil.toCsvField(source.getAddress().value);
        income = CsvUtil.toCsvField(source.getIncome().income.toString());
        tagged.addAll(source.getTags().stream()
                .map(CsvAdaptedTag::new)
                .collect(Collectors.toList()));
    }


    /**
     * Converts this Csv-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (CsvAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
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

        if (income == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Income.class.getSimpleName()));
        }
        if (!Income.isValidIncome(income)) {
            throw new IllegalValueException(Income.MESSAGE_CONSTRAINTS);
        }
        final Income modelIncome = new Income(income);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelIncome, modelTags);
    }

    @Override
    public String toString() {
        return String.join(",", name, phone, email, address, income,
                String.join(",", tagged.stream()
                        .<String>map(tag -> tag.toString()).collect(Collectors.toList())));
    }

}
