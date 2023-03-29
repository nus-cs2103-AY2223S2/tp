package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_VIDEO_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_VIDEO_DESC_V1;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_VIDEO_DESC_V2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.EditVideoDescriptorBuilder;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalVideos;
import seedu.address.testutil.VideoBuilder;


public class EditVideoCommandTest {

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditVideoCommand(
                        null, new LectureName(VALID_LECTURE_NAME_L1),
                        new VideoName(VALID_VIDEO_NAME_V2), EDIT_VIDEO_DESC_V2));
    }

    @Test
    public void constructor_nullLectureName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditVideoCommand(new ModuleCode(VALID_MODULE_CODE_2103), null,
                new VideoName(VALID_VIDEO_NAME_V2), EDIT_VIDEO_DESC_V2));
    }

    @Test
    public void constructor_nullVideoName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditVideoCommand(new ModuleCode(VALID_MODULE_CODE_2103), new LectureName(VALID_LECTURE_NAME_L1),
                null, EDIT_VIDEO_DESC_V2));
    }

    @Test
    public void constructor_nullEditVideoDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditVideoCommand(new ModuleCode(VALID_MODULE_CODE_2103), new LectureName(VALID_LECTURE_NAME_L1),
                new VideoName(VALID_VIDEO_NAME_V2), null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditVideoCommand command = new EditVideoCommand(
                new ModuleCode(VALID_MODULE_CODE_2103), new LectureName(VALID_LECTURE_NAME_L1),
                new VideoName(VALID_VIDEO_NAME_V1), EDIT_VIDEO_DESC_V1);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_editAcceptedByModel_success() {
        Module originalModule = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Module editedModule = new ModuleBuilder(originalModule).build();
        Lecture originalLecture = new LectureBuilder().withName(VALID_LECTURE_NAME_L1).build();
        Lecture editedLecture = new LectureBuilder(originalLecture).build();
        Video originalVideo = new VideoBuilder(TypicalVideos.CONTENT_VIDEO).build();
        Video editedVideo = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO).build();

        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder(editedVideo).build();

        EditVideoCommand command = new EditVideoCommand(
                originalModule.getCode(), originalLecture.getName(), originalVideo.getName(), descriptor);

        originalLecture.addVideo(originalVideo);
        originalModule.addLecture(originalLecture);
        model.addModule(originalModule);

        String expectedMessage = String.format(
                EditVideoCommand.MESSAGE_SUCCESS, originalLecture.getName(), originalModule.getCode(), editedVideo);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                new VideoEditInfo(originalModule.getCode(), originalLecture.getName(), originalVideo, editedVideo));

        Model expectedModel = new ModelManager();
        editedLecture.addVideo(editedVideo);
        editedModule.addLecture(editedLecture);
        expectedModel.addModule(editedModule);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoDescriptor descriptor = EDIT_VIDEO_DESC_V1;

        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        String expectedMessage = String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_lectureDoesNotExist_failure() {
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoDescriptor descriptor = EDIT_VIDEO_DESC_V1;

        EditVideoCommand command = new EditVideoCommand(module.getCode(), lectureName, videoName, descriptor);

        model.addModule(module);

        String expectedMessage = String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, module.getCode());
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_videoDoesNotExist_failure() {
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Lecture lecture = new LectureBuilder().withName(VALID_LECTURE_NAME_L1).build();
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoDescriptor descriptor = EDIT_VIDEO_DESC_V1;

        EditVideoCommand command = new EditVideoCommand(module.getCode(), lecture.getName(), videoName, descriptor);

        module.addLecture(lecture);
        model.addModule(module);

        String expectedMessage = String.format(
                MESSAGE_VIDEO_DOES_NOT_EXIST, videoName, lecture.getName(), module.getCode());
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateVideo_failure() {
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Lecture lecture = new LectureBuilder().withName(VALID_LECTURE_NAME_L1).build();
        Video originalVideo = new VideoBuilder(TypicalVideos.CONTENT_VIDEO).build();
        Video editedVideo = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO).build();
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder(editedVideo).build();

        lecture.addVideo(originalVideo);
        lecture.addVideo(editedVideo);
        module.addLecture(lecture);
        model.addModule(module);

        EditVideoCommand command = new EditVideoCommand(
                module.getCode(), lecture.getName(), originalVideo.getName(), descriptor);

        String expectedMessage = String.format(
                EditVideoCommand.MESSAGE_DUPLICATE_VIDEO, lecture.getName(), module.getCode());
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
        VideoName videoName = new VideoName(VALID_VIDEO_NAME_V1);
        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, EDIT_VIDEO_DESC_V2);

        // same values -> returns true
        EditVideoCommand commandWithSameValues = new EditVideoCommand(
                moduleCode, lectureName, videoName, EDIT_VIDEO_DESC_V2);
        assertTrue(command.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(command.equals(command));

        // null -> returns false
        assertFalse(command.equals(null));

        // different types -> returns false
        assertFalse(command.equals(1));

        // different module code -> returns false
        assertFalse(command.equals(
                new EditVideoCommand(
                        new ModuleCode(VALID_MODULE_CODE_2040), lectureName, videoName, EDIT_VIDEO_DESC_V2)));

        // different lecture name -> returns false
        assertFalse(command.equals(
                new EditVideoCommand(
                        moduleCode, new LectureName(VALID_LECTURE_NAME_L2), videoName, EDIT_VIDEO_DESC_V2)));

        // different video name -> returns false
        assertFalse(command.equals(
                new EditVideoCommand(
                        moduleCode, lectureName, new VideoName(VALID_VIDEO_NAME_V2), EDIT_VIDEO_DESC_V2)));

        // different descriptor -> returns false
        assertFalse(command.equals(
                new EditVideoCommand(
                        moduleCode, lectureName, videoName, EDIT_VIDEO_DESC_V1)));
    }
}
