package seedu.address.logic.commands;

import java.util.ArrayList;
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
 * Model Stub for testing Mark Commands
 */
public class MarkCommandModelStub extends ModelStub {

    private ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
    private LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
    private ArrayList<Video> videos;

    /**
     * Creates Model Stub for testing Mark Commands
     */
    public MarkCommandModelStub() {
        this.videos = new ArrayList<>();
        this.videos.add(TypicalVideos.ANALYSIS_VIDEO);
        this.videos.add(TypicalVideos.CONTENT_VIDEO);
        this.videos.add(TypicalVideos.INTRO_VIDEO);
        this.videos.add(TypicalVideos.REVISION_VIDEO);
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        return this.moduleCode.equals(moduleCode);
    }

    @Override
    public ReadOnlyModule getModule(ModuleCode moduleCode) {
        return new Module(moduleCode, new ModuleName(" "), Set.of(), List.of());
    }

    @Override
    public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
        return this.moduleCode.equals(moduleCode) && this.lectureName.equals(lectureName);
    }

    @Override
    public ReadOnlyLecture getLecture(ModuleCode moduleCode, LectureName lectureName) {
        return new Lecture(lectureName, Set.of(), videos);
    }

    @Override
    public boolean hasVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        boolean hasVideo = false;
        for (Video video: this.videos) {
            hasVideo = hasVideo || video.getName().equals(videoName);
        }
        return this.moduleCode.equals(moduleCode)
                && this.lectureName.equals(lectureName)
                && hasVideo;
    }

    @Override
    public Video getVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
        return videoName.equals(TypicalVideos.CONTENT_VIDEO.getName())
                ? TypicalVideos.CONTENT_VIDEO
                : TypicalVideos.INTRO_VIDEO;
    }

    @Override
    public void setVideo(ReadOnlyLecture lecture, Video targetVideo, Video newVideo) {
        this.videos.remove(targetVideo);
        this.videos.add(newVideo);
    }

}
