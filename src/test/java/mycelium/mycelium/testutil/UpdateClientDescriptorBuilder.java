package mycelium.mycelium.testutil;

import java.util.Optional;

import mycelium.mycelium.logic.commands.UpdateClientCommand;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * A utility class to help with building {@code UpdateClientDescriptor} objects.
 */
public class UpdateClientDescriptorBuilder {
    private UpdateClientCommand.UpdateClientDescriptor descriptor;

    public UpdateClientDescriptorBuilder() {
        descriptor = new UpdateClientCommand.UpdateClientDescriptor();
    }

    /**
     * Creates a new {@code UpdateClientDescriptorBuilder} with the fields of the given {@code descriptor} copied.
     *
     * @param name the name of the client.
     * @return the updated descriptor.
     */
    public UpdateClientDescriptorBuilder withName(String name) {
        descriptor.setName(Optional.ofNullable(name).map(Name::new));
        return this;
    }

    /**
     * Creates a new {@code UpdateClientDescriptorBuilder} with the fields of the given {@code descriptor} copied.
     *
     * @param email the email of the client.
     * @return the updated descriptor.
     */
    public UpdateClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(Optional.ofNullable(email).map(Email::new));
        return this;
    }

    /**
     * Creates a new {@code UpdateClientDescriptorBuilder} with the fields of the given {@code descriptor} copied.
     *
     * @param yearOfBirth the year of birth of the client.
     * @return the updated descriptor.
     */
    public UpdateClientDescriptorBuilder withYearOfBirth(String yearOfBirth) {
        descriptor.setYearOfBirth(Optional.ofNullable(yearOfBirth).map(YearOfBirth::new));
        return this;
    }

    /**
     * Creates a new {@code UpdateClientDescriptorBuilder} with the fields of the given {@code descriptor} copied.
     *
     * @param source the source of the client.
     * @return the updated descriptor.
     */
    public UpdateClientDescriptorBuilder withSource(String source) {
        descriptor.setSource(Optional.ofNullable(source).map(NonEmptyString::new));
        return this;
    }

    /**
     * Creates a new {@code UpdateClientDescriptorBuilder} with the fields of the given {@code descriptor} copied.
     *
     * @param mobileNumber the mobile number of the client.
     * @return the updated descriptor.
     */
    public UpdateClientDescriptorBuilder withMobileNumber(String mobileNumber) {
        descriptor.setMobileNumber(Optional.ofNullable(mobileNumber).map(Phone::new));
        return this;
    }

    /**
     * Creates a new {@code UpdateClientDescriptorBuilder} with the fields of the given {@code descriptor} copied.
     *
     * @return the updated descriptor.
     */
    public UpdateClientCommand.UpdateClientDescriptor build() {
        return descriptor;
    }
}
