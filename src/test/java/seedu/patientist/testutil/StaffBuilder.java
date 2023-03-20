package seedu.patientist.testutil;

import java.util.HashSet;

import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.util.SampleDataUtil;

/**
 * A utility class to help with building Staff objects.
 */
public class StaffBuilder extends PersonBuilder {
    /**
     * Creates a Staff from default details, with defaults specified here and in PersonBuilder
     */
    public StaffBuilder() {
        super();
    }

    /**
     * Initializes the StaffBuilder with the data of {@code staffToCopy}.
     */
    public StaffBuilder(Staff staffToCopy) {
        name = staffToCopy.getName();
        phone = staffToCopy.getPhone();
        email = staffToCopy.getEmail();
        address = staffToCopy.getAddress();
        tags = new HashSet<>(staffToCopy.getTags());
    }
    /**
     * Sets the {@code Name} of the {@code Staff} that we are building.
     */
    @Override
    public StaffBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Staff} that we are building.
     */
    @Override
    public StaffBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Staff} that we are building.
     */
    @Override
    public StaffBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Staff} that we are building.
     */
    @Override
    public StaffBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Staff} that we are building.
     */
    @Override
    public StaffBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Staff} that we are building.
     */
    @Override
    public StaffBuilder withIdNumber(String idNumber) {
        this.idNumber = new IdNumber(idNumber);
        return this;
    }

    @Override
    public Staff build() {
        return new Staff(name, phone, email, idNumber, address, tags);
    }

}
