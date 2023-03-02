package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.fish.Address;
import seedu.address.model.fish.Email;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Fish objects.
 */
public class FishBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code FishBuilder} with the default details.
     */
    public FishBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FishBuilder with the data of {@code fishToCopy}.
     */
    public FishBuilder(Fish fishToCopy) {
        name = fishToCopy.getName();
        phone = fishToCopy.getPhone();
        email = fishToCopy.getEmail();
        address = fishToCopy.getAddress();
        tags = new HashSet<>(fishToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Fish} that we are building.
     */
    public FishBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Fish} that we are building.
     */
    public FishBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Fish} that we are building.
     */
    public FishBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Fish} that we are building.
     */
    public FishBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Fish} that we are building.
     */
    public FishBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Fish build() {
        return new Fish(name, phone, email, address, tags);
    }

}
