package seedu.address.logic.commands.delete;

import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ModelStub;

/**
 * Model stub with Module but no Lecture
 */
public class ModelStubNoLecture extends ModelStub {
    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        return false;
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return true;
    }
}
