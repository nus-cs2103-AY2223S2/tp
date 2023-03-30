package seedu.address.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

/**
 * Contains integration tests (integration with the Model) and unit tests for {@code DeleteMultipleVideosCommand}
 */
public class DeleteMultipleVideosCommandTest {

    @Test
    public void execute_deleteVideo_success() throws CommandException {
        ReadOnlyModule module = TypicalModules.getCs2040s();
        ReadOnlyLecture lecture = TypicalLectures.getCs2040sWeek1();
        Video video = TypicalVideos.ANALYSIS_VIDEO;
        Video video2 = TypicalVideos.CONTENT_VIDEO;

        DeleteCommandModelStub dcms = new DeleteCommandModelStub();

        DeleteMultipleVideosCommand delete = new DeleteMultipleVideosCommand(
                module.getCode(), lecture.getName(), video.getName(), video2.getName());

        CommandResult actual = delete.execute(dcms);

        DeleteCommandModelStub expectedModel = new DeleteCommandModelStub();

        expectedModel.deleteVideo(lecture, video);
        expectedModel.deleteVideo(lecture, video2);

        // tests string output
        assertEquals(new CommandResult(String.format(DeleteMultipleVideosCommand.MESSAGE_SUCCESS,
                        "2", module.getCode(), lecture.getName(), video.getName() + ", " + video2.getName()),
                new VideoEditInfo(module.getCode(), lecture.getName(), video, null),
                new VideoEditInfo(module.getCode(), lecture.getName(), video2, null)),
                actual);

        // tests model
        assertEquals(expectedModel, dcms);
    }

    @Test
    public void execute_toDeleteDoesNotExist_throwsCommandException() {
        // module does not exist
        assertThrows(CommandException.class, () -> new DeleteMultipleVideosCommand(
                        TypicalModules.getCs2040s().getCode(),
                        TypicalLectures.getCs2040sWeek1().getName(),
                        TypicalVideos.ANALYSIS_VIDEO.getName(),
                        TypicalVideos.CONTENT_VIDEO.getName()
                ).execute(new ModelStubNoModule()));

        // lecture does not exist
        assertThrows(CommandException.class, () -> new DeleteMultipleVideosCommand(
                        TypicalModules.getCs2040s().getCode(),
                        TypicalLectures.getCs2040sWeek1().getName(),
                        TypicalVideos.ANALYSIS_VIDEO.getName(),
                        TypicalVideos.CONTENT_VIDEO.getName()
                ).execute(new ModelStubNoLecture()));

        // video does not exist
        assertThrows(CommandException.class, () -> new DeleteMultipleVideosCommand(
                        TypicalModules.getCs2040s().getCode(),
                        TypicalLectures.getCs2040sWeek1().getName(),
                        TypicalVideos.ANALYSIS_VIDEO.getName(),
                        TypicalVideos.CONTENT_VIDEO.getName()
                ).execute(new ModelStubNoVideo()));
    }

    @Test
    public void equals() {
        ModuleCode modCs2040s = TypicalModules.getCs2040s().getCode();
        ModuleCode modSt2334 = TypicalModules.getSt2334().getCode();

        LectureName lecCs2040sWeek1 = TypicalLectures.getCs2040sWeek1().getName();
        LectureName lecCs2040sWeek2 = TypicalLectures.getCs2040sWeek2().getName();
        LectureName lecSt2334Topic1 = TypicalLectures.getSt2334Topic1().getName();

        VideoName vidAnalysis = TypicalVideos.ANALYSIS_VIDEO.getName();
        VideoName vidContent = TypicalVideos.CONTENT_VIDEO.getName();

        DeleteMultipleVideosCommand deleteCs2040sWeek1VidAC =
                new DeleteMultipleVideosCommand(modCs2040s, lecCs2040sWeek1, vidAnalysis, vidContent);
        DeleteMultipleVideosCommand deleteCs2040sWeek1VidC =
                new DeleteMultipleVideosCommand(modCs2040s, lecCs2040sWeek1, vidContent);
        DeleteMultipleVideosCommand deleteCs2040sWeek2VidAC =
                new DeleteMultipleVideosCommand(modCs2040s, lecCs2040sWeek2, vidAnalysis, vidContent);
        DeleteMultipleVideosCommand deleteSt2334Topic1VidAC =
                new DeleteMultipleVideosCommand(modSt2334, lecSt2334Topic1, vidAnalysis, vidContent);

        // same object -> returns true
        assertTrue(deleteCs2040sWeek1VidAC.equals(deleteCs2040sWeek1VidAC));

        // same value -> returns true
        DeleteMultipleVideosCommand deleteCs2040sWeek1VidCDup =
                new DeleteMultipleVideosCommand(modCs2040s, lecCs2040sWeek1, vidContent);
        assertTrue(deleteCs2040sWeek1VidC.equals(deleteCs2040sWeek1VidCDup));

        // different values -> returns false
        assertFalse(deleteCs2040sWeek1VidAC.equals(deleteCs2040sWeek1VidC)); // different video
        assertFalse(deleteCs2040sWeek1VidAC.equals(deleteCs2040sWeek2VidAC)); // different lecture
        assertFalse(deleteCs2040sWeek1VidAC.equals(deleteSt2334Topic1VidAC)); // different mod

        // different typies -> returns false
        assertFalse(deleteCs2040sWeek1VidC.equals("command"));

        // null -> returns false
        assertFalse(deleteSt2334Topic1VidAC.equals(null));
    }
}
