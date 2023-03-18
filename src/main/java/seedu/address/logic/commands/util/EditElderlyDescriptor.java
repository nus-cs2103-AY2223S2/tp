package seedu.address.logic.commands.util;

import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the elderly with. Each non-empty field value will replace the
 * corresponding field value of the elderly.
 */
public class EditElderlyDescriptor extends EditPersonDescriptor {
    private RiskLevel riskLevel;

    public EditElderlyDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditElderlyDescriptor(EditElderlyDescriptor toCopy) {
        super(toCopy);
        setRiskLevel(toCopy.riskLevel);
    }

    /**
     * Creates and returns a {@code Elderly} with the details of {@code elderlyToEdit}
     * edited with {@code editElderlyDescriptor}.
     */
    public static Elderly createEditedElderly(Elderly elderlyToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert elderlyToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(elderlyToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(elderlyToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(elderlyToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(elderlyToEdit.getAddress());
        Nric updatedNric = editPersonDescriptor.getNric().orElse(elderlyToEdit.getNric());
        Age updatedAge = editPersonDescriptor.getAge().orElse(elderlyToEdit.getAge());
        Region updateRegion = editPersonDescriptor.getRegion().orElse(elderlyToEdit.getRegion());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(elderlyToEdit.getTags());
        Set<AvailableDate> updatedDates = editPersonDescriptor.getAvailableDates()
                .orElse(elderlyToEdit.getAvailableDates());
        // Setting elderly-specific attributes
        RiskLevel updatedRiskLevel = elderlyToEdit.getRiskLevel();
        if (editPersonDescriptor instanceof EditElderlyDescriptor) {
            EditElderlyDescriptor editElderlyDescriptor = (EditElderlyDescriptor) editPersonDescriptor;
            updatedRiskLevel = editElderlyDescriptor.getRiskLevel()
                    .orElse(elderlyToEdit.getRiskLevel());
        }
        return new Elderly(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedAge, updateRegion, updatedRiskLevel, updatedTags, updatedDates);
    }


    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(riskLevel);
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Optional<RiskLevel> getRiskLevel() {
        return Optional.ofNullable(riskLevel);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditElderlyDescriptor)) {
            return false;
        }

        // state check
        EditElderlyDescriptor e = (EditElderlyDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getNric().equals(e.getNric())
                && getAge().equals(e.getAge())
                && getRegion().equals(e.getRegion())
                && getRiskLevel().equals(e.getRiskLevel())
                && getTags().equals(e.getTags());
    }
}
