package seedu.fitbook.testutil.client;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.fitbook.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbook.model.client.Address;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Calorie;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Email;
import seedu.fitbook.model.client.Gender;
import seedu.fitbook.model.client.Name;
import seedu.fitbook.model.client.Phone;
import seedu.fitbook.model.client.Weight;
import seedu.fitbook.model.tag.Tag;


/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {

    private EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientDescriptor descriptor) {
        this.descriptor = new EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing {@code client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
        descriptor.setCalorie(client.getCalorie());
        descriptor.setWeight(client.getWeight());
        descriptor.setGender(client.getGender());
        descriptor.setAppointments(client.getAppointments());
        descriptor.setTags(client.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code weight} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withWeight(String weight) {
        descriptor.setWeight(new Weight(weight));
        return this;
    }

    /**
     * Sets the {@code gender} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditClientDescriptor}
     * that we are building.
     */
    public EditClientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Calorie} of the {@code EditClientDescriptorBuilder} that we are building.
     */
    public EditClientDescriptorBuilder withCalorie(String calorie) {
        descriptor.setCalorie(new Calorie(calorie));
        return this;
    }

    /**
     * Parses the {@code appointments} into a {@code Set<Appointment>} and set it to the {@code EditClientDescriptor}
     * that we are building.
     */
    public EditClientDescriptorBuilder withAppointments(String... appointments) {
        Set<Appointment> appointmentSet = Stream.of(appointments).map(Appointment::new).collect(Collectors.toSet());
        descriptor.setAppointments(appointmentSet);
        return this;
    }

    public EditClientDescriptor build() {
        return descriptor;
    }
}
