package seedu.address.model.module.exceptions;

import seedu.address.model.exceptions.DuplicateDataException;

/**
 * Signals that the operation will result in duplicate Modules (Modules are considered duplicates if they have the same
 * code).
 */
public class DuplicateModuleException extends DuplicateDataException {
    public DuplicateModuleException() {
        super("modules");
    }
}
