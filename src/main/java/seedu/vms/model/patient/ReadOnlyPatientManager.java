package seedu.vms.model.patient;

import seedu.vms.model.ReadOnlyStorageModel;

/**
 * A {@code ReadOnlyStorageModel} of {@code Patient}.
 *
 * <p>Interface does not add any new methods but acts as a marker of a read
 * only patient storage model.
 */
public interface ReadOnlyPatientManager extends ReadOnlyStorageModel<Patient> {}
