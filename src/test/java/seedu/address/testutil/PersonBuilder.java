package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tutee.Address;
import seedu.address.model.tutee.Email;
import seedu.address.model.tutee.Name;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.Phone;
import seedu.address.model.tutee.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Tutee objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code tuteeToCopy}.
     */
    public PersonBuilder(Tutee tuteeToCopy) {
        name = tuteeToCopy.getName();
        phone = tuteeToCopy.getPhone();
        email = tuteeToCopy.getEmail();
        address = tuteeToCopy.getAddress();
        remark = tuteeToCopy.getRemark();
        tags = new HashSet<>(tuteeToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Tutee} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Tutee} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Tutee} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Tutee} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Tutee} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Tutee} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Tutee build() {
        return new Tutee(name, phone, email, address, remark, tags);
    }

}
