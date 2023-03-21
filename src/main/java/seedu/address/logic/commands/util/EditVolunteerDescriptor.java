package seedu.address.logic.commands.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
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
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the volunteer with. Each non-empty field value will replace the
 * corresponding field value of the volunteer.
 */
public class EditVolunteerDescriptor extends EditDescriptor {

    private Set<MedicalQualificationTag> medicalTags;

    /**
     * Constructs a default empty descriptor.
     */
    public EditVolunteerDescriptor() {}

    /**
     * Constructs a descriptor using a copy.
     *
     * @param toCopy {@code EditPersonDescriptor} for copying.
     */
    public EditVolunteerDescriptor(EditVolunteerDescriptor toCopy) {
        super(toCopy);
        setMedicalTags(toCopy.medicalTags);
    }

    /**
     * Creates and returns a {@code Volunteer} with the details of {@code volunteerToEdit}
     * edited with {@code editVolunteerDescriptor}.
     *
     * @param volunteerToEdit Volunteer to edit.
     * @param editDescriptor Edit details.
     * @return Edited volunteer.
     */
    public static Volunteer createEditedVolunteer(Volunteer volunteerToEdit,
            EditDescriptor editDescriptor) {
        assert volunteerToEdit != null;

        Name updatedName = editDescriptor.getName().orElse(volunteerToEdit.getName());
        Phone updatedPhone = editDescriptor.getPhone().orElse(volunteerToEdit.getPhone());
        Email updatedEmail = editDescriptor.getEmail().orElse(volunteerToEdit.getEmail());
        Address updatedAddress = editDescriptor.getAddress().orElse(volunteerToEdit.getAddress());
        Nric updatedNric = editDescriptor.getNric().orElse(volunteerToEdit.getNric());
        Age updatedAge = editDescriptor.getAge().orElse(volunteerToEdit.getAge());
        Region updateRegion = editDescriptor.getRegion().orElse(volunteerToEdit.getRegion());
        Set<Tag> updatedTags = editDescriptor.getTags().orElse(volunteerToEdit.getTags());
        Set<AvailableDate> updatedDates = editDescriptor.getAvailableDates()
                .orElse(volunteerToEdit.getAvailableDates());

        Set<MedicalQualificationTag> updatedMedicalTags = volunteerToEdit.getMedicalTags();
        if (editDescriptor instanceof EditVolunteerDescriptor) {
            EditVolunteerDescriptor editVolunteerDescriptor = (EditVolunteerDescriptor) editDescriptor;
            updatedMedicalTags = editVolunteerDescriptor.getMedicalTags()
                    .orElse(volunteerToEdit.getMedicalTags());
        }

        return new Volunteer(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedAge, updateRegion, updatedTags, updatedMedicalTags, updatedDates);
    }

    public void setMedicalTags(Set<MedicalQualificationTag> tags) {
        this.medicalTags = (tags != null) ? new HashSet<>(tags) : null;
    }

    public Optional<Set<MedicalQualificationTag>> getMedicalTags() {
        return (medicalTags != null) ? Optional.of(
                Collections.unmodifiableSet(medicalTags)
        ) : Optional.empty();
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
                && getTags().equals(e.getTags())
                && getMedicalTags().equals(e.getMedicalTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getEmail(),
                getAddress(), getNric(), getAge(), getRegion(),
                getTags());
    }
}
