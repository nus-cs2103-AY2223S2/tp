package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalTags;
import seedu.address.testutil.TypicalVideos;

public class UntagCommandTest {

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_1, TypicalTags.CS2040S_2));
        UntagCommand command = new UntagCommand(tagSet, moduleCode);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void equals() {
        ModuleCode firstModuleCode = TypicalModules.getCs2040s().getCode();
        Set<Tag> firstTagSet = new HashSet<>(List.of(TypicalTags.CS2040S_1, TypicalTags.CS2040S_2,
                TypicalTags.CS2040S_3));

        ModuleCode secondModuleCode = TypicalModules.getCs2107().getCode();
        LectureName lectureName = TypicalLectures.getCs2107Lecture1().getName();
        Set<Tag> secondTagSet = new HashSet<>(List.of(TypicalTags.CS2107_LECTURE_1));

        UntagCommand untagModule = new UntagCommand(firstTagSet, firstModuleCode);
        UntagCommand untagModuleCopy = new UntagCommand(firstTagSet, firstModuleCode);
        UntagCommand untagLecture = new UntagCommand(secondTagSet, secondModuleCode, lectureName);

        assertTrue(untagModule.equals(untagModule));
        assertTrue(untagModule.equals(untagModuleCopy));
        assertFalse(untagModule.equals(1));
        assertFalse(untagModule.equals(untagLecture));
    }

    @Test
    public void execute_emptyTags_throwCommandException() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.CS2107);
        Set<Tag> tagSet = new HashSet<>();
        VideoName videoName = TypicalVideos.INTRO_VIDEO.getName();
        LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        UntagCommand untagCommandModule = new UntagCommand(tagSet, moduleCode);
        UntagCommand untagCommandLecture = new UntagCommand(tagSet, moduleCode, lectureName);
        UntagCommand untagCommandVideo = new UntagCommand(tagSet, moduleCode, lectureName, videoName);
        assertThrows(CommandException.class, () -> untagCommandModule.execute(moduleStub));
        assertThrows(CommandException.class, () -> untagCommandLecture.execute(moduleStub));
        assertThrows(CommandException.class, () -> untagCommandVideo.execute(moduleStub));
    }

    @Test
    public void execute_untagModule_moduleNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2107());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_1, TypicalTags.CS2040S_2,
                TypicalTags.CS2040S_3));
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagModule_tagNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2040s());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2107_1, TypicalTags.CS2107_2,
                TypicalTags.CS2040S_3));
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagLecture_moduleNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2107());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_WEEK_1_TAG));
        LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagLecture_lectureNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2040s());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_WEEK_1_TAG));
        LectureName lectureName = TypicalLectures.getCs2107Lecture1().getName();
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagLecture_tagNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2040s());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_WEEK_1_TAG, TypicalTags.CS2107_LECTURE_1,
                TypicalTags.CS2040S_3));
        LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagVideo_moduleNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2107());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_WEEK_1_TAG));
        LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        VideoName videoName = new VideoName("Probability");
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName, videoName);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagVideo_lectureNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2040s());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_WEEK_1_TAG));
        LectureName lectureName = TypicalLectures.getCs2107Lecture1().getName();
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        VideoName videoName = new VideoName("Probability");
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName, videoName);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagVideo_videoNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2040s());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_WEEK_1_TAG));
        LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        VideoName videoName = new VideoName("Probability");
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName, videoName);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagVideo_tagNotFound() {
        ModelStubWithModule moduleStub = new ModelStubWithModule(TypicalModules.getCs2040s());
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.VIDEO_TAG_1, TypicalTags.VIDEO_TAG_2));
        VideoName videoName = TypicalVideos.INTRO_VIDEO.getName();
        LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName, videoName);
        assertThrows(CommandException.class, () -> untagCommand.execute(moduleStub));
    }

    @Test
    public void execute_untagModule_success() throws CommandException {
        Module testModule = TypicalModules.getCs2040s();

        ModelStubWithModule moduleStub = new ModelStubWithModule(testModule);
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_1, TypicalTags.CS2040S_2));
        ModuleCode moduleCode = testModule.getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode);

        CommandResult commandResult = untagCommand.execute(moduleStub);
        assertEquals(String.format(UntagCommand.MESSAGE_SUCCESS, moduleCode),
                commandResult.getFeedbackToUser());
        Set<Tag> updatedTagSet = moduleStub.getModule(moduleCode).getTags();
        assertFalse(updatedTagSet.contains(TypicalTags.CS2040S_1));
        assertFalse(updatedTagSet.contains(TypicalTags.CS2040S_2));
    }

    @Test
    public void execute_untagLecture_success() throws CommandException {
        Module testModule = TypicalModules.getCs2040s();
        Lecture testLecture = TypicalLectures.getCs2040sWeek1();

        ModelStubWithModule moduleStub = new ModelStubWithModule(testModule);
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.CS2040S_WEEK_1_TAG));
        LectureName lectureName = testLecture.getName();
        ModuleCode moduleCode = testModule.getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName);

        CommandResult commandResult = untagCommand.execute(moduleStub);
        assertEquals(String.format(UntagCommand.MESSAGE_SUCCESS, lectureName),
                commandResult.getFeedbackToUser());
        Set<Tag> updatedTagSet = moduleStub.getModule(moduleCode).getLecture(lectureName).getTags();
        assertFalse(updatedTagSet.contains(TypicalTags.CS2040S_1));
        assertFalse(updatedTagSet.contains(TypicalTags.CS2040S_2));
    }

    @Test
    public void execute_untagVideo_success() throws CommandException {
        Module testModule = TypicalModules.getCs2040s();
        Lecture testLecture = TypicalLectures.getCs2040sWeek1();
        Video testVideo = TypicalVideos.INTRO_VIDEO;

        ModelStubAcceptingUntaggedVideo moduleStub = new ModelStubAcceptingUntaggedVideo(testVideo);
        Set<Tag> tagSet = new HashSet<>(List.of(TypicalTags.INTRO_VIDEO));
        VideoName videoName = testVideo.getName();
        LectureName lectureName = testLecture.getName();
        ModuleCode moduleCode = testModule.getCode();
        UntagCommand untagCommand = new UntagCommand(tagSet, moduleCode, lectureName, videoName);

        CommandResult commandResult = untagCommand.execute(moduleStub);
        assertEquals(String.format(UntagCommand.MESSAGE_SUCCESS, videoName),
                commandResult.getFeedbackToUser());

        Set<Tag> updatedTagSet = moduleStub.getVideo(videoName).getTags();
        assertFalse(updatedTagSet.contains(TypicalTags.CS2040S_1));
        assertFalse(updatedTagSet.contains(TypicalTags.CS2040S_2));
    }

    /**
     * A {@code Model} stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {
        private Module module;

        public ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(ModuleCode module) {
            requireNonNull(module);
            return this.module.getCode().equals(module);
        }

        @Override
        public boolean hasLecture(ModuleCode module, LectureName lecture) {
            requireAllNonNull(module, lecture);
            return this.module.getLecture(lecture) != null;
        }

        @Override
        public boolean hasVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
            requireAllNonNull(moduleCode, lectureName, videoName);
            return module.getLecture(lectureName).hasVideo(videoName);
        }

        public ReadOnlyModule getModule(ModuleCode moduleCode) {
            return module;
        }

        @Override
        public void setModule(ReadOnlyModule target, Module editedModule) {
            this.module = editedModule;
        }

        @Override
        public void setLecture(ReadOnlyModule module, ReadOnlyLecture target, Lecture editedLecture) {
            this.module.setLecture(target, editedLecture);
        }
    }

    private class ModelStubAcceptingUntaggedVideo extends ModelStub {
        private Module module = TypicalModules.getCs2040s();
        private Video video;

        public ModelStubAcceptingUntaggedVideo(Video video) {
            this.video = video;
        }

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return true;
        }

        @Override
        public boolean hasLecture(ModuleCode moduleCode, LectureName lectureName) {
            return true;
        }

        @Override
        public boolean hasVideo(ModuleCode moduleCode, LectureName lectureName, VideoName videoName) {
            return true;
        }

        @Override
        public void setVideo(ReadOnlyLecture targetLecture, Video taggingVideo, Video taggedVideo) {
            this.video = taggedVideo;
        }

        @Override
        public ReadOnlyModule getModule(ModuleCode moduleCode) {
            return this.module;
        }

        public Video getVideo(VideoName untaggedVideo) {
            return this.video;
        }
    }

}
