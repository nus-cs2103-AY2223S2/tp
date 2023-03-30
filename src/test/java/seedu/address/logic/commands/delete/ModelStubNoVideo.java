package seedu.address.logic.commands.delete;

import java.util.List;
import java.util.Set;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.ModelStub;

/**
 * Model Stub with Module and Lecture but no Video
 */
public class ModelStubNoVideo extends ModelStub {
    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return true;
    }

    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        return true;
    }

    @Override
    public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lectureName) {
        return new Lecture(lectureName, Set.of(), List.of());
    }

    @Override
    public boolean hasVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        return false;
    }
}
