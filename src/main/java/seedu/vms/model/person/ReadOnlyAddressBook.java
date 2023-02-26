package seedu.vms.model.person;

import seedu.vms.model.ReadOnlyStorageModel;

/**
 * A {@code ReadOnlyStorageModel} of {@code Person}.
 *
 * <p>Interface does not add any new methods but acts as a marker of a read
 * only person storage model.
 */
public interface ReadOnlyAddressBook extends ReadOnlyStorageModel<Person> {}
