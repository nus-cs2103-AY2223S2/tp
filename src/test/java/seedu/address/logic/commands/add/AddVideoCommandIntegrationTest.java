package seedu.address.logic.commands.add;

import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;


/**
 * Contains integration tests {interactions with the Model} for {@code AddVideoCommand}.
 */
public class AddVideoCommandIntegrationTest {

    private final Model model = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());

    @Test
    public void execute_newVideo_success() {
        Module module = TypicalModules.getCs2040s();
        ModuleCode moduleCode = module.getCode();
        Lecture lecture = TypicalLectures.getCs2040sWeek1();
        LectureName lectureName = lecture.getName();
        Video validVideo = TypicalVideos.CONTENT_VIDEO;

        // Prevents the original module from TypicalModules and lecture from TypicalLectures from being modified
        model.deleteModule(module);
        model.addModule(module);
        model.deleteLecture(module, lecture);
        model.addLecture(module, lecture);

        Model expectedModel = new ModelManager(model.getTracker(), model.getUserPrefs());

        // Prevents the module and lecture in model from being modified
        Module moduleCopy = new ModuleBuilder(module).build();
        Lecture lectureCopy = new LectureBuilder(lecture).build();

        expectedModel.deleteModule(module);
        expectedModel.addModule(moduleCopy);
        expectedModel.deleteLecture(moduleCopy, lecture);
        expectedModel.addLecture(moduleCopy, lectureCopy);
        expectedModel.addVideo(lectureCopy, validVideo);

        String expectedMessage = String.format(AddVideoCommand.MESSAGE_SUCCESS, moduleCode, lectureName, validVideo);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                new VideoEditInfo(moduleCode, lectureName, null, validVideo));
        assertCommandSuccess(new AddVideoCommand(moduleCode, lectureName, validVideo), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_moduleDoesNotExist_throwsCommandException() {
        ModuleCode moduleCode = TypicalModules.getCs2107().getCode();
        LectureName lectureName = TypicalLectures.getCs2107Lecture1().getName();
        Video validVideo = TypicalVideos.CONTENT_VIDEO;

        assertCommandFailure(new AddVideoCommand(moduleCode, lectureName, validVideo), model,
                String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
    }

    @Test
    public void execute_lectureDoesNotExist_throwsCommandException() {
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        LectureName lectureName = TypicalLectures.getSt2334Topic1().getName();
        Video validVideo = TypicalVideos.CONTENT_VIDEO;

        assertCommandFailure(new AddVideoCommand(moduleCode, lectureName, validVideo), model,
                String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
    }

    @Test
    public void execute_duplicateVideo_throwsCommandException() {
        ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
        LectureName lectureName = TypicalLectures.getCs2040sWeek1().getName();
        Video videoInList = TypicalVideos.INTRO_VIDEO;

        assertCommandFailure(new AddVideoCommand(moduleCode, lectureName, videoInList), model,
                String.format(AddVideoCommand.MESSAGE_DUPLICATE_VIDEO, lectureName, moduleCode));
    }

}
