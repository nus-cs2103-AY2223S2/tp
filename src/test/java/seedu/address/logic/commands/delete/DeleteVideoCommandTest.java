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
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteVideoCommand}
 */
public class DeleteVideoCommandTest {

    @Test
    public void execute_deleteVideo_success() throws CommandException {
        ReadOnlyModule module = TypicalModules.getCs2040s();
        ReadOnlyLecture lecture = TypicalLectures.getCs2040sWeek1();
        Video video = TypicalVideos.ANALYSIS_VIDEO;

        DeleteCommandModelStub dcms = new DeleteCommandModelStub();

        DeleteVideoCommand delete = new DeleteVideoCommand(
                module.getCode(), lecture.getName(), video.getName());

        CommandResult actual = delete.execute(dcms);

        DeleteCommandModelStub expectedModel = new DeleteCommandModelStub();

        expectedModel.deleteVideo(lecture, video);

        // tests string output
        assertEquals(new CommandResult(String.format(DeleteVideoCommand.MESSAGE_DELETE_VIDEO_SUCCESS,
                        video.getName(), lecture.getName(), module.getCode()),
                new VideoEditInfo(module.getCode(), lecture.getName(), video, null)),
                actual);

        // tests model
        assertEquals(expectedModel, dcms);
    }

    @Test
    public void execute_toDeleteDoesNotExist_throwsCommandException() {
        // module does not exist
        assertThrows(CommandException.class, () -> new DeleteVideoCommand(
                        TypicalModules.getCs2040s().getCode(),
                        TypicalLectures.getCs2040sWeek1().getName(),
                        TypicalVideos.ANALYSIS_VIDEO.getName()
                ).execute(new ModelStubNoModule()));

        // lecture does not exist
        assertThrows(CommandException.class, () -> new DeleteVideoCommand(
                        TypicalModules.getCs2040s().getCode(),
                        TypicalLectures.getCs2040sWeek1().getName(),
                        TypicalVideos.ANALYSIS_VIDEO.getName()
                ).execute(new ModelStubNoLecture()));

        // video does not exist
        assertThrows(CommandException.class, () -> new DeleteVideoCommand(
                        TypicalModules.getCs2040s().getCode(),
                        TypicalLectures.getCs2040sWeek1().getName(),
                        TypicalVideos.ANALYSIS_VIDEO.getName()
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

        DeleteVideoCommand deleteCs2040sWeek1VidA = new DeleteVideoCommand(modCs2040s, lecCs2040sWeek1, vidAnalysis);
        DeleteVideoCommand deleteCs2040sWeek1VidC = new DeleteVideoCommand(modCs2040s, lecCs2040sWeek1, vidContent);
        DeleteVideoCommand deleteCs2040sWeek2VidA = new DeleteVideoCommand(modCs2040s, lecCs2040sWeek2, vidAnalysis);
        DeleteVideoCommand deleteSt2334Topic1VidA = new DeleteVideoCommand(modSt2334, lecSt2334Topic1, vidAnalysis);

        // same object -> returns true
        assertTrue(deleteCs2040sWeek1VidA.equals(deleteCs2040sWeek1VidA));

        // same value -> returns true
        DeleteVideoCommand deleteCs2040sWeek1VidCDup = new DeleteVideoCommand(modCs2040s, lecCs2040sWeek1, vidContent);
        assertTrue(deleteCs2040sWeek1VidC.equals(deleteCs2040sWeek1VidCDup));

        // different values -> returns false
        assertFalse(deleteCs2040sWeek1VidA.equals(deleteCs2040sWeek1VidC)); // different video
        assertFalse(deleteCs2040sWeek1VidA.equals(deleteCs2040sWeek2VidA)); // different lecture
        assertFalse(deleteCs2040sWeek1VidA.equals(deleteSt2334Topic1VidA)); // different mod

        // different typies -> returns false
        assertFalse(deleteCs2040sWeek1VidC.equals("command"));

        // null -> returns false
        assertFalse(deleteSt2334Topic1VidA.equals(null));
    }
}
