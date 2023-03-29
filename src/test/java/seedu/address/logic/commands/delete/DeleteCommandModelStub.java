package seedu.address.logic.commands.delete;

import java.util.List;
import java.util.Set;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

/**
 * Stub of Model Manager for testing Delete Commands
 */
public class DeleteCommandModelStub extends ModelStub {
    private ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
    private LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
    private VideoName videoName = TypicalVideos.ANALYSIS_VIDEO.getName();

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return moduleCode.equals(this.moduleCode);
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode moduleCode) {
        return new Module(moduleCode, new ModuleName(" "), Set.of(), List.of());
    }

    @Override
    public void deleteModule(ReadOnlyModule module) {
        if (module.getCode().equals(this.moduleCode)) {
            this.moduleCode = null;
        }
    }

    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        return moduleCode.equals(this.moduleCode) && lectureName.equals(this.lectureName);
    }

    @Override
    public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lecturename) {
        return new Lecture(lecturename, Set.of(), List.of());
    }

    @Override
    public void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target) {
        if (module.getCode().equals(this.moduleCode) && target.getName().equals(this.lectureName)) {
            this.lectureName = null;
        }
    }

    @Override
    public boolean hasVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        return moduleCode.equals(this.moduleCode)
                && lectureName.equals(this.lectureName)
                && videoName.equals(this.videoName);
    }

    @Override
    public Video getVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        return new Video(videoName, false, Set.of());
    }

    @Override
    public void deleteVideo(ReadOnlyLecture lecture, Video target) {
        if (lecture.getName().equals(this.lectureName) && target.getName().equals(this.videoName)) {
            this.videoName = null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DeleteCommandModelStub) {
            DeleteCommandModelStub model = (DeleteCommandModelStub) other;
            if (this.lectureName == null) {
                if (this.moduleCode == null) {
                    return model.moduleCode == null && model.lectureName == null;
                }
                return this.moduleCode.equals(model.moduleCode) && model.lectureName == null;
            }
            return this.moduleCode.equals(model.moduleCode) && this.lectureName.equals(model.lectureName);
        } else {
            return false;
        }
    }
}
