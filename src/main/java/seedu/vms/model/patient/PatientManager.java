package seedu.vms.model.patient;

import java.util.HashSet;
import java.util.Set;

import seedu.vms.commons.core.ValueChange;
import seedu.vms.model.GroupName;
import seedu.vms.model.StorageModel;
import seedu.vms.model.vaccination.VaxType;

/**
 * Wraps all data at the patient-manager level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class PatientManager extends StorageModel<Patient> implements ReadOnlyPatientManager {
    public PatientManager() {}

    /**
     * Creates an PatientManager using the Patients in the {@code toBeCopied}
     */
    public PatientManager(ReadOnlyPatientManager toBeCopied) {
        super(toBeCopied);
    }

    /**
     * Handles vaccination changes in PatientManager.
     */
    public void handleVaccinationChange(ValueChange<VaxType> change) {
        if (!change.getOldValue().equals(change.getNewValue())
                && change.getOldValue().isPresent()) {
            if (change.getNewValue().isPresent()) { // update
                GroupName vaxGroupNameOld = change.getOldValue().get().getGroupName();
                String vaxNameOld = change.getOldValue().get().getName();
                String vaxNameNew = change.getNewValue().get().getName();
                getMapView().entrySet().stream()
                        .filter(patient -> patient.getValue().getValue().getVaccine().contains(vaxGroupNameOld))
                        .forEach(patient -> set(patient.getKey(),
                                patient.getValue().getValue()
                                        .setVaccination(updateGroupName(patient.getValue().getValue().getVaccine(),
                                                vaxNameOld, vaxNameNew))));
            }
        }
    }

    private static Set<GroupName> updateGroupName(Set<GroupName> groupNames, String oldName, String newName) {
        Set<GroupName> updatedGroupNames = new HashSet<>();

        for (GroupName groupName : groupNames) {
            if (groupName.getName().equals(oldName)) {
                GroupName updatedGroupName = new GroupName(newName);
                updatedGroupNames.add(updatedGroupName);
            } else {
                updatedGroupNames.add(groupName);
            }
        }

        return updatedGroupNames;
    }
}
