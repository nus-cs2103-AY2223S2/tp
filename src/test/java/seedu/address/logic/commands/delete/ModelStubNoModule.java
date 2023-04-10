package seedu.address.logic.commands.delete;

import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ModelStub;

/**
 * Model Stub with no Module
 */
public class ModelStubNoModule extends ModelStub {
    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return false;
    }
}
