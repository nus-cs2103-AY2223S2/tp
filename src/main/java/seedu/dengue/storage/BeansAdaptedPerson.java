package seedu.dengue.storage;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.variant.Variant;

/**
 * Jackson-friendly version of {@link Person}.
 */
class BeansAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String postal;
    private final String date;
    private final String age;
    private final List<CsvAdaptedVariant> variants = new ArrayList<>();

    /**
     * Constructs a {@code BeansAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public BeansAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("postal") String postal,
                              @JsonProperty("date") String date, @JsonProperty("age") String age,
                              @JsonProperty("variants") List<CsvAdaptedVariant> variants) {
        this.name = name;
        this.postal = postal;
        this.date = date;
        this.age = age;
        if (variants != null) {
            this.variants.addAll(variants);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public BeansAdaptedPerson(Person source) {
        name = source.getName().fullName;
        postal = source.getPostal().value;
        date = source.getDate().value.toString();
        age = source.getAge().value;
        variants.addAll(source.getVariants().stream()
                .map(CsvAdaptedVariant::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this JavaBeans-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Variant> personVariants = new ArrayList<>();
        for (CsvAdaptedVariant variant : variants) {
            personVariants.add(variant.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (postal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Postal.class.getSimpleName()));
        }
        if (!Postal.isValidPostal(postal)) {
            throw new IllegalValueException(Postal.MESSAGE_CONSTRAINTS);
        }
        final Postal modelPostal = new Postal(postal);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        final Set<Variant> modelVariants = new HashSet<>(personVariants);
        return new Person(modelName, modelPostal, modelDate, modelAge, modelVariants);
    }
}
