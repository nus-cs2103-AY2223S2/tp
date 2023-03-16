package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;


public class ParseArgumentTest {
    private static String INVALID_LECTURE_NAME = "Bl@b";
    private static String INVALID_MODULE_CODE = "@#123";
    private static String EMPTY_LECTURE_NAME = "";
    private static String EMPTY_MODULE_CODE = "";
    private static String EMPTY_VIDEO_NAME = "";
    private static String VALID_LECTURE_NAME = "Class Diagrams";
    private static String VALID_MODULE_CODE = "CS2103T";
    private static String VALID_VIDEO_NAME = "Test Video";

    @Test
    public void parseModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParseArgument.parseModule((String) null));
    }

    @Test
    public void parseModule_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParseArgument.parseModule(EMPTY_MODULE_CODE));
    }

    @Test
    public void parseModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParseArgument.parseModule(INVALID_MODULE_CODE));
    }

    @Test
    public void parseModule_validValue_returnsModuleCode() throws Exception {
        ModuleCode expectedModule = new ModuleCode(VALID_MODULE_CODE);
        assertEquals(expectedModule, ParseArgument.parseModule(VALID_MODULE_CODE));
    }

    @Test
    public void parseLecture_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParseArgument.parseLecture((String) null));
    }

    @Test
    public void parseLecture_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParseArgument.parseLecture(EMPTY_LECTURE_NAME));
    }

    @Test
    public void parseLecture_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParseArgument.parseLecture(INVALID_LECTURE_NAME));
    }

    @Test
    public void parseLecture_validValue_returnsLectureName() throws Exception {
        LectureName expectedLecture = new LectureName(VALID_LECTURE_NAME);
        assertEquals(expectedLecture, ParseArgument.parseLecture(VALID_LECTURE_NAME));
    }

    @Test
    public void parseVideo_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParseArgument.parseVideo((String) null));
    }

    @Test
    public void parseVideo_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParseArgument.parseVideo(EMPTY_VIDEO_NAME));
    }

    @Test
    public void parseVideo_validValue_returnsVideoName() throws Exception {
        VideoName expectedVideo = new VideoName(VALID_VIDEO_NAME);
        assertEquals(expectedVideo, ParseArgument.parseVideo(VALID_VIDEO_NAME));
    }
}