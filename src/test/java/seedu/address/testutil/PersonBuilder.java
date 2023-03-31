package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Module;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EDUCATION = "P6";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Education education;
    private Remark remark;
    private Set<Tag> tags;
    private Set<Module> modules;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        education = new Education(DEFAULT_EDUCATION);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
        modules = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getOptionalPhone().orElse(null);
        email = personToCopy.getOptionalEmail().orElse(null);
        address = personToCopy.getOptionalAddress().orElse(null);
        education = personToCopy.getOptionalEducation().orElse(null);
        remark = personToCopy.getOptionalRemark().orElse(null);
        tags = new HashSet<>(personToCopy.getTags());
        modules = new HashSet<>(personToCopy.getModules());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code modules} into a {@code Set<Module>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withModules(String ... modules) {
        this.modules = SampleDataUtil.getModuleSet(modules);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = address == null ? null : new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = phone == null ? null : new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = email == null ? null : new Email(email);
        return this;
    }

    /**
     * Sets the {@code Education} of the {@code Person} that we are building.
     */
    public PersonBuilder withEducation(String education) {
        this.education = education == null ? null : new Education(education);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = remark == null ? null : new Remark(remark);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, education, remark, modules, tags);
    }

}
