package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());
    }

    @Test
    public void execute_newVideo_success() {
        Module module = new ModuleBuilder(TypicalModules.CS2040S).build();
        ModuleCode moduleCode = module.getCode();
        Lecture lecture = new LectureBuilder(TypicalLectures.CS2040S_WEEK_1).build();
        LectureName lectureName = lecture.getName();
        Video validVideo = TypicalVideos.CONTENT_VIDEO;

        model.deleteModule(module);
        model.addModule(module);
        model.deleteLecture(module, lecture);
        model.addLecture(module, lecture);

        Model expectedModel = new ModelManager(model.getTracker(), model.getUserPrefs());
        Module moduleCopy = new ModuleBuilder(module).build();
        Lecture lectureCopy = new LectureBuilder(lecture).build();

        expectedModel.deleteModule(module);
        expectedModel.addModule(moduleCopy);
        expectedModel.deleteLecture(moduleCopy, lecture);
        expectedModel.addLecture(moduleCopy, lectureCopy);
        expectedModel.addVideo(lectureCopy, validVideo);

        assertCommandSuccess(new AddVideoCommand(moduleCode, lectureName, validVideo), model,
                String.format(AddVideoCommand.MESSAGE_SUCCESS, moduleCode, lectureName, validVideo), expectedModel);
    }

    @Test
    public void execute_moduleDoesNotExist_throwsCommandException() {
        Module module = TypicalModules.CS2107;
        ModuleCode moduleCode = module.getCode();
        Lecture lecture = new LectureBuilder(TypicalLectures.CS2107_LECTURE_1).build();
        LectureName lectureName = lecture.getName();
        Video validVideo = TypicalVideos.CONTENT_VIDEO;

        assertCommandFailure(new AddVideoCommand(moduleCode, lectureName, validVideo), model,
                String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
    }

    @Test
    public void execute_lectureDoesNotExist_throwsCommandException() {
        Module module = TypicalModules.CS2040S;
        ModuleCode moduleCode = module.getCode();
        Lecture lecture = new LectureBuilder(TypicalLectures.ST2334_TOPIC_1).build();
        LectureName lectureName = lecture.getName();
        Video validVideo = TypicalVideos.CONTENT_VIDEO;

        assertCommandFailure(new AddVideoCommand(moduleCode, lectureName, validVideo), model,
                String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lectureName, moduleCode));
    }

    @Test
    public void execute_duplicateVideo_throwsCommandException() {
        Module module = TypicalModules.CS2040S;
        ModuleCode moduleCode = module.getCode();
        Lecture lecture = new LectureBuilder(TypicalLectures.CS2040S_WEEK_1).build();
        LectureName lectureName = lecture.getName();
        Video videoInList = TypicalVideos.INTRO_VIDEO;

        assertCommandFailure(new AddVideoCommand(moduleCode, lectureName, videoInList), model,
                String.format(AddVideoCommand.MESSAGE_DUPLICATE_VIDEO, lectureName, moduleCode));
    }

}
