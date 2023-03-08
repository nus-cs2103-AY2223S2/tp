package seedu.vms.model.vaccination;

import seedu.vms.commons.exceptions.IllegalValueException;


/**
 * Similar to {@link java.util.function.Function} however functional method may
 * throw an exception.
 */
@FunctionalInterface
public interface VaxTypeAction {
    /**
     * Performs an action of the given {@code VaxTypeManager}.
     *
     * @param manager - the manager to work on.
     * @throws IllegalValueException illegal values are present.
     */
    public VaxType apply(VaxTypeManager manager) throws IllegalValueException;
}
