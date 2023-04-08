package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.ultron.logic.commands.EditCommand.EditOpeningDescriptor;
import seedu.ultron.model.opening.Company;
import seedu.ultron.model.opening.Email;
import seedu.ultron.model.opening.Keydate;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.model.opening.Position;
import seedu.ultron.model.opening.Status;

/**
 * A utility class to help with building EditOpeningDescriptor objects.
 */
public class EditOpeningDescriptorBuilder {

    private EditOpeningDescriptor descriptor;

    public EditOpeningDescriptorBuilder() {
        descriptor = new EditOpeningDescriptor();
    }

    public EditOpeningDescriptorBuilder(EditOpeningDescriptor descriptor) {
        this.descriptor = new EditOpeningDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOpeningDescriptor} with fields containing {@code opening}'s details
     */
    public EditOpeningDescriptorBuilder(Opening opening) {
        descriptor = new EditOpeningDescriptor();
        descriptor.setPosition(opening.getPosition());
        descriptor.setCompany(opening.getCompany());
        descriptor.setEmail(opening.getEmail());
        descriptor.setStatus(opening.getStatus());
        descriptor.setKeydates(opening.getKeydates());
    }

    /**
     * Sets the {@code Position} of the {@code EditOpeningDescriptor} that we are building.
     */
    public EditOpeningDescriptorBuilder withPosition(String position) {
        descriptor.setPosition(new Position(position));
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code EditOpeningDescriptor} that we are building.
     */
    public EditOpeningDescriptorBuilder withCompany(String company) {
        descriptor.setCompany(new Company(company));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditOpeningDescriptor} that we are building.
     */
    public EditOpeningDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditOpeningDescriptor} that we are building.
     */
    public EditOpeningDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Parses the {@code dates} into a {@code Set<Date>} and set it to the {@code EditOpeningDescriptor}
     * that we are building.
     */
    public EditOpeningDescriptorBuilder withDates(ArrayList<String>... dates) {
        List<Keydate> dateSet = Stream.of(dates).map(date -> new Keydate(date.get(0), date.get(1)))
                .collect(Collectors.toList());
        descriptor.setKeydates(dateSet);
        return this;
    }

    public EditOpeningDescriptor build() {
        return descriptor;
    }
}
