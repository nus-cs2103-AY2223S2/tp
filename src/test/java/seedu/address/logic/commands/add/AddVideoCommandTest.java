package seedu.address.logic.commands.add;

import static seedu.address.commons.core.Messages.MESSAGE_LECTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.model.video.Video;
import seedu.address.testutil.LectureBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ObjectUtil;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

/**
 * Contains integration tests (interaction with the {@code Model}) and unit tests for {@code AddVideoCommand}.
 */
public class AddVideoCommandTest {

    private final Module module = TypicalModules.getCs2040s();
    private final Lecture lecture = TypicalLectures.getCs2040sWeek1();
    private final Video video = TypicalVideos.CONTENT_VIDEO;

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddVideoCommand(null, lecture.getName(), video));
    }

    @Test
    public void constructor_nullLectureName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddVideoCommand(module.getCode(), null, video));
    }

    @Test
    public void constructor_nullVideo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddVideoCommand(module.getCode(), lecture.getName(), null));
    }

    @Test
    public void execute_videoAcceptedByModel_success() throws CommandException {
        /* Setup */
        AddVideoCommand command = new AddVideoCommand(module.getCode(), lecture.getName(), video);
        model.addModule(module);

        /* Create expected results */
        // Create expected command result
        String expectedMessage = String.format(AddVideoCommand.MESSAGE_SUCCESS,
                module.getCode(), lecture.getName(), video);
        VideoEditInfo expectedEditInfo = new VideoEditInfo(module.getCode(), lecture.getName(), null, video);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedEditInfo);

        // Create expected model
        Model expectedModel = new ModelManager();
        Module editedModule = new ModuleBuilder(module).build();
        Lecture editedLecture = new LectureBuilder(lecture).build();
        editedLecture.addVideo(video);
        editedModule.setLecture(lecture, editedLecture);
        expectedModel.addModule(editedModule);

        /* Execute test */
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddVideoCommand command = new AddVideoCommand(module.getCode(), lecture.getName(), video);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        AddVideoCommand command = new AddVideoCommand(module.getCode(), lecture.getName(), video);
        assertThrows(CommandException.class,
                String.format(MESSAGE_MODULE_DOES_NOT_EXIST, module.getCode()), () -> command.execute(model));
    }

    @Test
    public void execute_lectureDoesNotExist_failure() {
        AddVideoCommand command = new AddVideoCommand(module.getCode(), lecture.getName(), video);
        module.removeLecture(lecture);
        model.addModule(module);

        assertThrows(CommandException.class,
                String.format(MESSAGE_LECTURE_DOES_NOT_EXIST, lecture.getName(), module.getCode()), () ->
                command.execute(model));
    }

    @Test
    public void execute_duplicateVideo_failure() {
        /* Setup */
        // Setup command
        AddVideoCommand command = new AddVideoCommand(module.getCode(), lecture.getName(), video);

        // Setup model
        Lecture editedLecture = new LectureBuilder(lecture).build();
        editedLecture.addVideo(video);
        module.setLecture(lecture, editedLecture);
        model.addModule(module);

        /* Create expected results */
        String expectedMessage = String.format(AddVideoCommand.MESSAGE_DUPLICATE_VIDEO, lecture.getName(),
                module.getCode());

        /* Execute tests */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        AddVideoCommand command = new AddVideoCommand(module.getCode(), lecture.getName(), video);

        AddVideoCommand commandWithSameValues = new AddVideoCommand(module.getCode(), lecture.getName(), video);

        AddVideoCommand commandWithDiffModuleCode = new AddVideoCommand(TypicalModules.getSt2334().getCode(),
                lecture.getName(), video);
        AddVideoCommand commandWithDiffLectureName = new AddVideoCommand(module.getCode(),
                TypicalLectures.getCs2040sWeek2().getName(), video);
        AddVideoCommand commandWithDiffVideo = new AddVideoCommand(module.getCode(), lecture.getName(),
                TypicalVideos.REVISION_VIDEO);

        ObjectUtil.testEquals(command, commandWithSameValues, 1,
                commandWithDiffModuleCode, commandWithDiffLectureName, commandWithDiffVideo);
    }

}
