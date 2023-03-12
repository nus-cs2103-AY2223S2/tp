package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.job.Address;
import seedu.address.model.job.Email;
import seedu.address.model.job.Name;
import seedu.address.model.job.Phone;
import seedu.address.model.job.Role;
import seedu.address.model.job.Salary;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Role objects.
 */
public class RoleBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_SALARY = "4000";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Salary salary;

    /**
     * Creates a {@code RoleBuilder} with the default details.
     */
    public RoleBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        salary = new Salary(DEFAULT_SALARY);
    }

    /**
     * Initializes the RoleBuilder with the data of {@code roleToCopy}.
     */
    public RoleBuilder(Role roleToCopy) {
        name = roleToCopy.getName();
        phone = roleToCopy.getPhone();
        email = roleToCopy.getEmail();
        address = roleToCopy.getAddress();
        salary = roleToCopy.getSalary();
        tags = new HashSet<>(roleToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Role} that we are building.
     */
    public RoleBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Role} that we are building.
     */
    public RoleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Role} that we are building.
     */
    public RoleBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Role} that we are building.
     */
    public RoleBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Role} that we are building.
     */
    public RoleBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Role} that we are building.
     */
    public RoleBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    public Role build() {
        return new Role(name, phone, email, address, tags, salary);
    }

}
