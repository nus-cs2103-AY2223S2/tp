package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_VIDEO_DOES_NOT_EXIST;
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
import seedu.address.logic.commands.CommandTestUtil;
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
import seedu.address.testutil.ObjectUtil;
import seedu.address.testutil.TypicalVideos;
import seedu.address.testutil.VideoBuilder;

/**
 * Contains integration tests (interactions with the Model) and unit tests for {@code EditVideoCommand}.
 */
public class EditVideoCommandTest {

    private final ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
    private final LectureName lectureName = new LectureName(VALID_LECTURE_NAME_L1);
    private final VideoName videoName = new VideoName(VALID_VIDEO_NAME_V2);
    private final EditVideoDescriptor descriptor = CommandTestUtil.getEditVideoDescriptorV1();

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditVideoCommand(null, lectureName, videoName, descriptor));
    }

    @Test
    public void constructor_nullLectureName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditVideoCommand(moduleCode, null, videoName, descriptor));
    }

    @Test
    public void constructor_nullVideoName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditVideoCommand(moduleCode, lectureName, null, descriptor));
    }

    @Test
    public void constructor_nullEditVideoDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditVideoCommand(moduleCode, lectureName, videoName, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_editAcceptedByModel_success() {
        /* Setup */
        // Setup modules, lectures, and videos
        Module originalModule = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Module editedModule = new ModuleBuilder(originalModule).build();
        Lecture originalLecture = new LectureBuilder().withName(VALID_LECTURE_NAME_L1).build();
        Lecture editedLecture = new LectureBuilder(originalLecture).build();
        Video originalVideo = new VideoBuilder(TypicalVideos.CONTENT_VIDEO).build();
        Video editedVideo = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO).build();

        originalLecture.addVideo(originalVideo);
        originalModule.addLecture(originalLecture);

        // Setup command
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder(editedVideo).build();
        EditVideoCommand command = new EditVideoCommand(
                originalModule.getCode(), originalLecture.getName(), originalVideo.getName(), descriptor);

        // Setup model
        model.addModule(originalModule);

        /* Create expected result */
        // Create expected command result
        String expectedMessage = String.format(
                EditVideoCommand.MESSAGE_SUCCESS, originalLecture.getName(), originalModule.getCode(), editedVideo);
        VideoEditInfo expectedEditInfo = new VideoEditInfo(
                originalModule.getCode(), originalLecture.getName(), originalVideo, editedVideo);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedEditInfo);

        // Create expected model
        Model expectedModel = new ModelManager();
        editedLecture.addVideo(editedVideo);
        editedModule.addLecture(editedLecture);
        expectedModel.addModule(editedModule);

        /* Execute test */
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        /* Setup */
        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        /* Create expected result */
        String expectedMessage = String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode);

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_lectureDoesNotExist_failure() {
        /* Setup */
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        EditVideoCommand command = new EditVideoCommand(module.getCode(), lectureName, videoName, descriptor);

        model.addModule(module);

        /* Create expected result */
        String expectedMessage = String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, module.getCode());

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_videoDoesNotExist_failure() {
        /* Setup */
        // Setup module and lecture
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Lecture lecture = new LectureBuilder().withName(VALID_LECTURE_NAME_L1).build();

        module.addLecture(lecture);

        // Setup command
        EditVideoCommand command = new EditVideoCommand(module.getCode(), lecture.getName(), videoName, descriptor);

        // Setup model
        model.addModule(module);

        /* Create expected result */
        String expectedMessage = String.format(
                MESSAGE_VIDEO_DOES_NOT_EXIST, videoName, lecture.getName(), module.getCode());

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateVideo_failure() {
        /* Setup */
        // Setup module, lecture, and videos
        Module module = new ModuleBuilder().withCode(VALID_MODULE_CODE_2103).build();
        Lecture lecture = new LectureBuilder().withName(VALID_LECTURE_NAME_L1).build();
        Video originalVideo = new VideoBuilder(TypicalVideos.CONTENT_VIDEO).build();
        Video editedVideo = new VideoBuilder(TypicalVideos.ANALYSIS_VIDEO).build();

        lecture.addVideo(originalVideo);
        lecture.addVideo(editedVideo);
        module.addLecture(lecture);

        // Setup command
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder(editedVideo).build();
        EditVideoCommand command = new EditVideoCommand(
                module.getCode(), lecture.getName(), originalVideo.getName(), descriptor);

        // Setup model
        model.addModule(module);

        /* Create expected result */
        String expectedMessage = String.format(
                EditVideoCommand.MESSAGE_DUPLICATE_VIDEO, lecture.getName(), module.getCode());

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        EditVideoCommand command = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        EditVideoCommand commandWithSameValues = new EditVideoCommand(moduleCode, lectureName, videoName, descriptor);

        EditVideoCommand commandWithDiffModuleCode = new EditVideoCommand(
                new ModuleCode(VALID_MODULE_CODE_2040), lectureName, videoName, descriptor);
        EditVideoCommand commandWithDiffLectureName = new EditVideoCommand(
                moduleCode, new LectureName(VALID_LECTURE_NAME_L2), videoName, descriptor);
        EditVideoCommand commandWithDiffVideoName = new EditVideoCommand(
                moduleCode, lectureName, new VideoName(VALID_VIDEO_NAME_V1), descriptor);
        EditVideoCommand commandWithDiffDescriptor = new EditVideoCommand(
                moduleCode, lectureName, videoName, CommandTestUtil.getEditVideoDescriptorV2());

        ObjectUtil.testEquals(command, commandWithSameValues, 1,
                commandWithDiffModuleCode, commandWithDiffLectureName, commandWithDiffVideoName,
                commandWithDiffDescriptor);
    }
}
