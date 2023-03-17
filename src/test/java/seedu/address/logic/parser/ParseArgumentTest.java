//package seedu.address.logic.parser;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.address.testutil.Assert.assertThrows;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.lecture.LectureName;
//import seedu.address.model.module.ModuleCode;
//import seedu.address.model.video.VideoName;
//
//
//public class ParseArgumentTest {
//    private static String INVALID_LECTURENAME = "Bl@b";
//    private static String INVALID_MODULECODE = "@#123";
//    private static String EMPTY_LECTURENAME = "";
//    private static String EMPTY_MODULECODE = "";
//    private static String EMPTY_VIDEONAME = "";
//    private static String VALID_LECTURENAME = "Class Diagrams";
//    private static String VALID_MODULECODE = "CS2103T";
//    private static String VALID_VIDEONAME = "Test Video";
//
//    @Test
//    public void parseModule_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> ParseArgument.parseModule((String) null));
//    }
//
//    @Test
//    public void parseModule_emptyValue_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParseArgument.parseModule(EMPTY_MODULECODE));
//    }
//
//    @Test
//    public void parseModule_invalidValue_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParseArgument.parseModule(INVALID_MODULECODE));
//    }
//
//    @Test
//    public void parseModule_validValue_returnsModuleCode() throws Exception {
//        ModuleCode expectedModule = new ModuleCode(VALID_MODULECODE);
//        assertEquals(expectedModule, ParseArgument.parseModule(VALID_MODULECODE));
//    }
//
//    @Test
//    public void parseLecture_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> ParseArgument.parseLecture((String) null));
//    }
//
//    @Test
//    public void parseLecture_emptyValue_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParseArgument.parseLecture(EMPTY_LECTURENAME));
//    }
//
//    @Test
//    public void parseLecture_invalidValue_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParseArgument.parseLecture(INVALID_LECTURENAME));
//    }
//
//    @Test
//    public void parseLecture_validValue_returnsLectureName() throws Exception {
//        LectureName expectedLecture = new LectureName(VALID_LECTURENAME);
//        assertEquals(expectedLecture, ParseArgument.parseLecture(VALID_LECTURENAME));
//    }
//
//    @Test
//    public void parseVideo_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> ParseArgument.parseVideo((String) null));
//    }
//
//    @Test
//    public void parseVideo_emptyValue_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParseArgument.parseVideo(EMPTY_VIDEONAME));
//    }
//
//    @Test
//    public void parseVideo_validValue_returnsVideoName() throws Exception {
//        VideoName expectedVideo = new VideoName(VALID_VIDEONAME);
//        assertEquals(expectedVideo, ParseArgument.parseVideo(VALID_VIDEONAME));
//    }
//}
