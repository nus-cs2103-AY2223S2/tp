package seedu.address.logic.commands.util;

import java.util.Set;

import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the volunteer with. Each non-empty field value will replace the
 * corresponding field value of the volunteer.
 */
public class EditVolunteerDescriptor extends EditPersonDescriptor {
    public EditVolunteerDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditVolunteerDescriptor(EditVolunteerDescriptor toCopy) {
        super(toCopy);
    }

    /**
     * Creates and returns a {@code Volunteer} with the details of {@code volunteerToEdit}
     * edited with {@code editVolunteerDescriptor}.
     */
    public static Volunteer createEditedVolunteer(Volunteer volunteerToEdit,
                                                   EditVolunteerDescriptor editVolunteerDescriptor) {
        assert volunteerToEdit != null;

        Name updatedName = editVolunteerDescriptor.getName().orElse(volunteerToEdit.getName());
        Phone updatedPhone = editVolunteerDescriptor.getPhone().orElse(volunteerToEdit.getPhone());
        Email updatedEmail = editVolunteerDescriptor.getEmail().orElse(volunteerToEdit.getEmail());
        Address updatedAddress = editVolunteerDescriptor.getAddress().orElse(volunteerToEdit.getAddress());
        Nric updatedNric = editVolunteerDescriptor.getNric().orElse(volunteerToEdit.getNric());
        Age updatedAge = editVolunteerDescriptor.getAge().orElse(volunteerToEdit.getAge());
        Set<Tag> updatedTags = editVolunteerDescriptor.getTags().orElse(volunteerToEdit.getTags());

        return new Volunteer(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedAge, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVolunteerDescriptor)) {
            return false;
        }

        // state check
        EditVolunteerDescriptor e = (EditVolunteerDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getNric().equals(e.getNric())
                && getAge().equals(e.getAge())
                && getTags().equals(e.getTags());
    }
}
