package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.person.parent.Parent;


/**
 * A utility class to help with building Person objects.
 */
public class ParentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_AGE = "20";
    public static final String DEFAULT_IMAGE = "C:\\Users\\teacher\\OneDrive\\Desktop\\Pictures\\marygoh.jpg";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Age age;
    private Image image;
    private Email email;
    private Phone phone;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code ParentBuilder} with the default details.
     */
    public ParentBuilder() {
        name = new Name(DEFAULT_NAME);
        age = new Age(DEFAULT_AGE);
        image = new Image(DEFAULT_IMAGE);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ParentBuilder with the data of {@code parentToCopy}.
     */
    public ParentBuilder(Parent parentToCopy) {
        name = parentToCopy.getName();
        age = parentToCopy.getAge();
        image = parentToCopy.getImage();
        phone = parentToCopy.getPhone();
        email = parentToCopy.getEmail();
        address = parentToCopy.getAddress();
        tags = new HashSet<>(parentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Parent} that we are building.
     */
    public ParentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     * @param age
     */
    public ParentBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Parses the {@code image} into a {@code image} and set it to the {@code Parent} that we are building.
     */
    public ParentBuilder withImage(String image) {
        this.image = new Image(image);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Parent} that we are building.
     */
    public ParentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Parent} that we are building.
     */
    public ParentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public ParentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public ParentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Parent build() {
        return new Parent(name, age, image, email, phone, address, tags);
    }


}
