package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Field;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.SuperField;
import seedu.address.model.person.fields.Tags;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Gender gender;
    private final Major major;
    private final Modules modules;
    private final Race race;
    private final Tags tags;
    private final CommunicationChannel comms;
    private final Favorite favorite;
    private final Faculty faculty;

    /**
     * Creates a {@code Person} with all fields nullable except {@code name}.
     */
    public Person(Name name, Phone phone, Email email, Address address, Gender gender,
                  Major major, Modules modules, Race race, Tags tags, CommunicationChannel comms,
                  Favorite favorite, Faculty faculty) {
        requireAllNonNull(name);
        this.name = name;
        this.phone = Optional.ofNullable(phone).orElse(new Phone(""));
        this.email = Optional.ofNullable(email).orElse(new Email(""));
        this.address = Optional.ofNullable(address).orElse(new Address(""));
        this.gender = Optional.ofNullable(gender).orElse(new Gender(""));
        this.major = Optional.ofNullable(major).orElse(new Major(""));
        this.modules = Optional.ofNullable(modules).orElse(new Modules(new HashSet<>()));
        this.race = Optional.ofNullable(race).orElse(new Race(""));
        this.tags = Optional.ofNullable(tags).orElse(new Tags(new HashSet<>()));
        this.comms = Optional.ofNullable(comms).orElse(new CommunicationChannel(""));
        this.favorite = Optional.ofNullable(favorite).orElse(new Favorite(false));
        this.faculty = Optional.ofNullable(faculty).orElse(new Faculty(""));
    }

    /**
     * Creates a {@code Person} with all fields blank except {@code name}.
     */
    public Person(Name name) {
        this(name, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Name getName() {
        return this.name;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Email getEmail() {
        return this.email;
    }

    public Address getAddress() {
        return this.address;
    }

    public Favorite getFavorite() {
        return this.favorite;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Major getMajor() {
        return this.major;
    }

    public Race getRace() {
        return this.race;
    }

    public CommunicationChannel getComms() {
        return this.comms;
    }

    public Modules getModules() {
        return this.modules;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public Tags getTags() {
        return this.tags;
    }

    /**
     * Returns an immutable NusMod set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<NusMod> getSetOfMods() {
        return this.modules.getValues();
    }

    /**
     * Returns an immutable Tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getSetOfTags() {
        return this.tags.getValues();
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    public Person setFavorite(boolean isFavorite) {
        return new Person(name, phone, email, address, gender, major, modules,
                race, tags, comms, new Favorite(isFavorite), faculty);
    }

    /**
     * Returns true if for all the fields specified,
     * there exists a keyword linked to that field, where that person's field contains that keyword,
     * returns false otherwise.
     *
     * For the more mathematically inclined,
     * {∀field ∈ Fields: ∃keyword ∈ field.keywords s.t. Person.field.contains(keyword) == true}.
     * where Fields is the fields specified in the FindCommand, field.keywords is the keywords associated with that
     * field and Person.field is the field we are testing against.
     */
    public boolean contains(HashMap<PredicateKey, Set<String>> keywords) {

        HashMap<PredicateKey, Field> fieldMap = new HashMap<>();
        fieldMap.put(PredicateKey.NAME, this.name);
        fieldMap.put(PredicateKey.ADDRESS, this.address);
        fieldMap.put(PredicateKey.COMMS, this.comms);
        fieldMap.put(PredicateKey.EMAIL, this.email);
        fieldMap.put(PredicateKey.GENDER, this.gender);
        fieldMap.put(PredicateKey.MAJOR, this.major);
        fieldMap.put(PredicateKey.PHONE, this.phone);
        fieldMap.put(PredicateKey.RACE, this.race);
        fieldMap.put(PredicateKey.FACULTY, this.faculty);

        for (PredicateKey key : keywords.keySet()) {
            Field field = fieldMap.get(key);
            if (field == null) {
                continue;
            }
            if (!field.contains(keywords.get(key))) {
                return false;
            }
        }

        HashMap<PredicateKey, SuperField<? extends Field>> superFieldMap = new HashMap<>();
        superFieldMap.put(PredicateKey.TAG, this.tags);
        superFieldMap.put(PredicateKey.MODULES, this.modules);

        for (PredicateKey key : keywords.keySet()) {
            SuperField<? extends Field> field = superFieldMap.get(key);
            if (field == null) {
                continue;
            }
            if (!field.contains(keywords.get(key))) {
                return false;
            }
        }
        return true;
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
        return otherPerson.getName().equals(this.getName())
                && otherPerson.getPhone().equals(this.getPhone())
                && otherPerson.getEmail().equals(this.getEmail())
                && otherPerson.getAddress().equals(this.getAddress())
                && otherPerson.getSetOfTags().equals(this.getSetOfTags())
                && otherPerson.getGender().equals(this.getGender())
                && otherPerson.getMajor().equals(this.getMajor())
                && otherPerson.getRace().equals(this.getRace())
                && otherPerson.getModules().equals(this.getModules())
                && otherPerson.getComms().equals(this.getComms())
                && otherPerson.getFavorite().equals(this.getFavorite())
                && otherPerson.getFaculty().equals(this.getFaculty());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, major,
                modules, race, comms, faculty, tags, favorite);
    }

    @Override
    public String toString() {
        return getName() + "; Phone: " + getPhone() + "; Email: " + getEmail() + "; Address: " + getAddress()
                + "; Gender: " + this.getGender() + "; Major: " + this.getMajor() + "; Race: " + this.getRace()
                + "; Preferred Communication: " + this.getComms() + "; Faculty: " + this.getFaculty() + "; Tags: "
                + this.getTags() + "; Modules: " + this.getModules() + "; Favorite: " + getFavorite();
    }
}
