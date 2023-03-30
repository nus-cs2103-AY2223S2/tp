package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult.VideoEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.mark.MarkAsWatchedCommand;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.Video;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

/**
 * Contains integration tests (integration with the Model) and unit tests for {@code MarkAsWatchedCommand}
 */
public class MarkAsWatchedCommandTest {

    private ModuleCode moduleCode = TypicalModules.getCs2040s().getCode();
    private Lecture lecture = TypicalLectures.getCs2040sWeek1();

    @Test
    public void execute_markVideo_success() throws CommandException {

        Video video = TypicalVideos.INTRO_VIDEO;

        MarkCommandModelStub model = new MarkCommandModelStub();
        MarkAsWatchedCommand mawd = new MarkAsWatchedCommand(moduleCode, lecture.getName(), video.getName());

        CommandResult actual = mawd.execute(model);

        MarkCommandModelStub expectedModel = new MarkCommandModelStub();

        Video edited = new Video(video.getName(), true, video.getTimestamp(), video.getTags());
        expectedModel.setVideo(lecture, video, edited);

        // tests command response output
        assertEquals(new CommandResult(String.format(MarkAsWatchedCommand.MESSAGE_MARK_VIDEO_SUCCESS,
                        video.getName(),
                        "mark",
                        "1 ",
                        "",
                        lecture.getName(),
                        moduleCode),
                new VideoEditInfo(moduleCode, lecture.getName(), video, edited)),
                actual);
    }
}
