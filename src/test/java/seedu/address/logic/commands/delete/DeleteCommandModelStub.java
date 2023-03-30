package seedu.address.logic.commands.delete;

import java.util.ArrayList;

import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
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
    private ArrayList<ModuleCode> moduleCodes = new ArrayList<>();
    private ArrayList<LectureName> lectureNames = new ArrayList<>();
    private ArrayList<VideoName> videoNames = new ArrayList<>();

    /**
     * Constructs Model Stub for testing Delete Commands
     */
    public DeleteCommandModelStub() {
        moduleCodes.add(TypicalModules.getCs2040s().getCode());
        moduleCodes.add(TypicalModules.getCs2107().getCode());

        lectureNames.add(TypicalLectures.getCs2040sWeek1().getName());
        lectureNames.add(TypicalLectures.getCs2040sWeek2().getName());

        videoNames.add(TypicalVideos.ANALYSIS_VIDEO.getName());
        videoNames.add(TypicalVideos.CONTENT_VIDEO.getName());
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return this.moduleCodes.contains(moduleCode);
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode moduleCode) {
        return moduleCode.equals(new ModuleCode("CS2040S"))
                ? TypicalModules.getCs2040s()
                : TypicalModules.getCs2107();
    }

    @Override
    public void deleteModule(ReadOnlyModule module) {
        this.moduleCodes.remove(module.getCode());
    }

    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        return this.moduleCodes.contains(moduleCode) && this.lectureNames.contains(lectureName);
    }

    @Override
    public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lectureName) {
        return lectureName.equals(new LectureName("Week 1"))
                ? TypicalLectures.getCs2040sWeek1()
                : TypicalLectures.getCs2040sWeek2();
    }

    @Override
    public void deleteLecture(ReadOnlyModule module, ReadOnlyLecture target) {
        this.lectureNames.remove(target.getName());
    }

    @Override
    public boolean hasVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        return this.moduleCodes.contains(moduleCode)
                && this.lectureNames.contains(lectureName)
                && this.videoNames.contains(videoName);
    }

    @Override
    public Video getVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        return videoName.equals(new VideoName("Vid 2"))
                ? TypicalVideos.ANALYSIS_VIDEO
                : TypicalVideos.CONTENT_VIDEO;
    }

    @Override
    public void deleteVideo(ReadOnlyLecture lecture, Video target) {
        this.videoNames.remove(target.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DeleteCommandModelStub) {
            DeleteCommandModelStub model = (DeleteCommandModelStub) other;
            return this.moduleCodes.equals(model.moduleCodes)
                    && this.lectureNames.equals(model.lectureNames)
                    && this.videoNames.equals(model.videoNames);
        } else {
            return false;
        }
    }
}
