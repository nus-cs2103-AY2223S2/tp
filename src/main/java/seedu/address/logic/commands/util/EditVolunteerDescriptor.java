package seedu.address.logic.commands.util;

import java.util.Set;

import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
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
     * edited with {@code editPersonDescriptor}.
     */
    public static Volunteer createEditedVolunteer(Volunteer volunteerToEdit,
                                                   EditPersonDescriptor editPersonDescriptor) {
        assert volunteerToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(volunteerToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(volunteerToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(volunteerToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(volunteerToEdit.getAddress());
        Nric updatedNric = editPersonDescriptor.getNric().orElse(volunteerToEdit.getNric());
        Age updatedAge = editPersonDescriptor.getAge().orElse(volunteerToEdit.getAge());
        Region updateRegion = editPersonDescriptor.getRegion().orElse(volunteerToEdit.getRegion());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(volunteerToEdit.getTags());
        Set<AvailableDate> updatedDates = editPersonDescriptor.getAvailableDates()
                .orElse(volunteerToEdit.getAvailableDates());

        return new Volunteer(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedAge, updateRegion, updatedTags, updatedDates);
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
                && getRegion().equals(e.getRegion())
                && getTags().equals(e.getTags());
    }
}
