package seedu.dengue.model.person;

import static seedu.dengue.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvCustomBindByName;

import seedu.dengue.model.person.csvutil.AgeConverter;
import seedu.dengue.model.person.csvutil.DateConverter;
import seedu.dengue.model.person.csvutil.NameConverter;
import seedu.dengue.model.person.csvutil.PostalConverter;
import seedu.dengue.model.person.csvutil.VariantsConverter;
import seedu.dengue.model.variant.Variant;

/**
 * Represents a Person in the Dengue Hotspot Tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    @CsvCustomBindByName(column = "Patient Name", converter = NameConverter.class)
    private Name name;
    @CsvCustomBindByName(column = "Postal Code", converter = PostalConverter.class)
    private Postal postal;
    @CsvCustomBindByName(column = "Date", converter = DateConverter.class)
    private Date date;
    @CsvCustomBindByName(column = "Age", converter = AgeConverter.class)
    private Age age;
    @CsvCustomBindByName(column = "Variants", converter = VariantsConverter.class)
    private Set<Variant> variants = new HashSet<>();

    public Person() {}
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Postal postal, Date date, Age age, Set<Variant> variants) {
        requireAllNonNull(name, postal, date, age, variants);
        this.name = name;
        this.postal = postal;
        this.date = date;
        this.age = age;
        this.variants.addAll(variants);
    }

    public Name getName() {
        return name;
    }

    public Postal getPostal() {
        return postal;
    }

    public Date getDate() {
        return date;
    }

    public Age getAge() {
        return age;
    }


    public Person getCopy() {
        return new Person(this.name, this.postal, this.date, this.age, this.variants);
    }

    /**
     * Returns an immutable variant set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Variant> getVariants() {
        return Collections.unmodifiableSet(variants);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        boolean isSame;
        if (otherPerson == this) {
            return true;
        } else if (otherPerson == null) {
            return false;
        } else {
            boolean hasSameName = otherPerson.getName().fullName.toLowerCase()
                    .equals(getName().fullName.toLowerCase());
            boolean hasSameAge = otherPerson.getAge().equals(getAge());
            boolean hasSamePostal = otherPerson.getPostal().equals(getPostal());
            isSame = hasSameAge && hasSameName && hasSamePostal;
            return isSame;
        }


    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPostal().equals(getPostal())
                && otherPerson.getDate().equals(getDate())
                && otherPerson.getAge().equals(getAge())
                && otherPerson.getVariants().equals(getVariants());
    }

    /**
     * Returns an array of Strings representing the fields of this Person object formatted as a CSV string.
     * The string array is generated in the following order: name, age, date, postal, and variants.
     * @return A String[] representing the fields of this Person formatted as a CSV string
     */
    public String[] toCsvString() {
        ArrayList<String> result = new ArrayList<String>();
        result.add(this.name.toString());
        result.add(this.age.toString());
        result.add(this.date.toString());
        result.add(this.postal.toString());
        Set<Variant> variantSet = getVariants();
        StringBuilder builder = new StringBuilder("[");
        if (!variantSet.isEmpty()) {
            builder.append(variantSet.stream()
                    .map(Variant::toString)
                    .collect(Collectors.joining(", ")));
        }
        builder.append("]");
        result.add(builder.toString());
        String[] csvString = new String[5];
        return result.toArray(csvString);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, postal, date, age, variants);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Postal: ")
                .append(getPostal())
                .append("; Date: ")
                .append(getDate())
                .append("; Age: ")
                .append(getAge());

        Set<Variant> variantSet = getVariants();
        if (!variantSet.isEmpty()) {
            builder.append("; Variants: ");
            variantSet.forEach(builder::append);
        }
        return builder.toString();
    }

    public String toStringSimple() {
        return getName().toString() + " : " + getAge().toString();
    }
}
