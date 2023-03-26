package seedu.address.logic.commands.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;
/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class EditDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Nric nric;
    private BirthDate birthDate;
    private Region region;
    private RiskLevel riskLevel;
    private Set<MedicalQualificationTag> medicalTags;
    private Set<Tag> tags;
    private Set<AvailableDate> availableDates;

    /**
     * Default empty constructor.
     */
    public EditDescriptor() {}

    /**
     * Copy constructor.
     *
     * @param toCopy {@code EditPersonDescriptor} for copying.
     */
    public EditDescriptor(EditDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
        setNric(toCopy.nric);
        setBirthDate(toCopy.birthDate);
        setRegion(toCopy.region);
        setRiskLevel(toCopy.riskLevel);
        setMedicalTags(toCopy.medicalTags);
        setTags(toCopy.tags);
        setAvailableDates(toCopy.availableDates);
    }

    /**
     * Returns true if at least one field is edited.
     *
     * @return True if at least one field is edited and false otherwise.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone,
                email, address, nric, birthDate, region,
                tags, riskLevel, medicalTags, availableDates);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setNric(Nric nric) {
        this.nric = nric;
    }

    public Optional<Nric> getNric() {
        return Optional.ofNullable(nric);
    }

    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public Optional<BirthDate> getBirthDate() {
        return Optional.ofNullable(birthDate);
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Optional<Region> getRegion() {
        return Optional.ofNullable(region);
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Optional<RiskLevel> getRiskLevel() {
        return Optional.ofNullable(riskLevel);
    }

    public void setMedicalTags(Set<MedicalQualificationTag> tags) {
        this.medicalTags = (tags != null) ? new HashSet<>(tags) : null;
    }

    public Optional<Set<MedicalQualificationTag>> getMedicalTags() {
        return (medicalTags != null)
                ? Optional.of(Collections.unmodifiableSet(medicalTags))
                : Optional.empty();
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     *
     * @param tags Tags to set.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     *
     * @return {@code Optional} of the set of tags.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null)
                ? Optional.of(Collections.unmodifiableSet(tags))
                : Optional.empty();
    }

    /**
     * Sets {@code availableDates} to this object's {@code availableDates}.
     *
     * @param availableDates Dates to set.
     */
    public void setAvailableDates(Set<AvailableDate> availableDates) {
        this.availableDates = (availableDates != null) ? new HashSet<>(availableDates) : null;
    }

    /**
     * Returns an unmodifiable date set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code availableDates} is null.
     *
     * @return {@code Optional} of the set of dates.
     */
    public Optional<Set<AvailableDate>> getAvailableDates() {
        return (availableDates != null)
                ? Optional.of(Collections.unmodifiableSet(availableDates))
                : Optional.empty();
    }

    /**
     * Creates and returns a {@code Elderly} with the details of {@code elderlyToEdit}
     * edited with {@code editDescriptor}.
     *
     * @param elderlyToEdit Elderly to edit.
     * @param editDescriptor Edit details.
     * @return Edited elderly.
     */
    public static Elderly createEditedElderly(Elderly elderlyToEdit, EditDescriptor editDescriptor) {
        assert elderlyToEdit != null;

        Name updatedName = editDescriptor.getName().orElse(elderlyToEdit.getName());
        Phone updatedPhone = editDescriptor.getPhone().orElse(elderlyToEdit.getPhone());
        Email updatedEmail = editDescriptor.getEmail().orElse(elderlyToEdit.getEmail());
        Address updatedAddress = editDescriptor.getAddress().orElse(elderlyToEdit.getAddress());
        Nric updatedNric = editDescriptor.getNric().orElse(elderlyToEdit.getNric());
        BirthDate updatedBirthDate = editDescriptor.getBirthDate().orElse(elderlyToEdit.getBirthDate());
        Region updateRegion = editDescriptor.getRegion().orElse(elderlyToEdit.getRegion());
        RiskLevel updatedRiskLevel = editDescriptor.getRiskLevel().orElse(elderlyToEdit.getRiskLevel());
        Set<Tag> updatedTags = editDescriptor.getTags().orElse(elderlyToEdit.getTags());
        Set<AvailableDate> updatedDates = editDescriptor.getAvailableDates()
                .orElse(elderlyToEdit.getAvailableDates());

        return new Elderly(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedBirthDate, updateRegion, updatedRiskLevel, updatedTags, updatedDates);
    }

    /**
     * Creates and returns a {@code Volunteer} with the details of {@code volunteerToEdit}
     * edited with {@code editDescriptor}.
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
        BirthDate updatedBirthDate = editDescriptor.getBirthDate().orElse(volunteerToEdit.getBirthDate());
        Region updateRegion = editDescriptor.getRegion().orElse(volunteerToEdit.getRegion());
        Set<MedicalQualificationTag> updatedMedicalTags = editDescriptor.getMedicalTags()
                .orElse(volunteerToEdit.getMedicalTags());
        Set<Tag> updatedTags = editDescriptor.getTags().orElse(volunteerToEdit.getTags());
        Set<AvailableDate> updatedDates = editDescriptor.getAvailableDates()
                .orElse(volunteerToEdit.getAvailableDates());

        return new Volunteer(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedBirthDate, updateRegion, updatedTags, updatedMedicalTags, updatedDates);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDescriptor)) {
            return false;
        }

        // state check
        EditDescriptor e = (EditDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getNric().equals(e.getNric())
                && getBirthDate().equals(e.getBirthDate())
                && getRegion().equals(e.getRegion())
                && getRiskLevel().equals(e.getRiskLevel())
                && getMedicalTags().equals(e.getMedicalTags())
                && getTags().equals(e.getTags())
                && getAvailableDates().equals(e.getAvailableDates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, nric,
                birthDate, region, riskLevel, medicalTags, availableDates, tags);
    }
}
