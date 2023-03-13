package seedu.address.logic.commands.util;

import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
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
    public static Elderly createEditedElderly(Elderly elderlyToEdit, EditElderlyDescriptor editElderlyDescriptor) {
        assert elderlyToEdit != null;

        Name updatedName = editElderlyDescriptor.getName().orElse(elderlyToEdit.getName());
        Phone updatedPhone = editElderlyDescriptor.getPhone().orElse(elderlyToEdit.getPhone());
        Email updatedEmail = editElderlyDescriptor.getEmail().orElse(elderlyToEdit.getEmail());
        Address updatedAddress = editElderlyDescriptor.getAddress().orElse(elderlyToEdit.getAddress());
        Nric updatedNric = editElderlyDescriptor.getNric().orElse(elderlyToEdit.getNric());
        Age updatedAge = editElderlyDescriptor.getAge().orElse(elderlyToEdit.getAge());
        RiskLevel updagetRiskLevel = editElderlyDescriptor.getRiskLevel()
                .orElse(elderlyToEdit.getRiskLevel());
        Set<Tag> updatedTags = editElderlyDescriptor.getTags().orElse(elderlyToEdit.getTags());

        return new Elderly(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedNric, updatedAge, updagetRiskLevel, updatedTags);
    }


    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return super.isAnyFieldEdited() && CollectionUtil.isAnyNonNull(riskLevel);
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
                && getRiskLevel().equals(e.getRiskLevel())
                && getTags().equals(e.getTags());
    }
}
