package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

public class MultipleEventsParserTest {

    @Test
    public void convertArrayListToStringTest() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("one");
        arr.add("two");
        assertEquals("one, two", MultipleEventsParser.convertArrayListToString(arr));
    }

    @Test
    public void parseModuleCodes_validArgs_returnsArray() throws ParseException {
        String moduleCodeString = "CS2040S, ST2334";
        ModuleCode[] moduleCodes = MultipleEventsParser.parseModuleCodes(moduleCodeString);
        assertEquals(TypicalModules.getCs2040s().getCode(), moduleCodes[0]);
        assertEquals(TypicalModules.getSt2334().getCode(), moduleCodes[1]);
    }

    @Test
    public void parseModuleCodes_invalidArgs_throwsParseException() {
        // invalid module code does not follow module code format
        String moduleCodeString = "invalid, CS2040S";
        assertThrows(ParseException.class, () ->
                MultipleEventsParser.parseModuleCodes(moduleCodeString));

        // duplicates
        String moduleCodeStringWithDuplicates = "CS2040S, CS2040S";
        assertThrows(ParseException.class, () ->
                MultipleEventsParser.parseModuleCodes(moduleCodeStringWithDuplicates));
    }

    @Test
    public void parseLectureNames_validArgs_returnsArray() throws ParseException {
        String lectureNameString = "Week 1, Week 2";
        LectureName[] lectureNames = MultipleEventsParser.parseLectureNames(lectureNameString);
        assertEquals(TypicalLectures.getCs2040sWeek1().getName(), lectureNames[0]);
        assertEquals(TypicalLectures.getCs2040sWeek2().getName(), lectureNames[1]);
    }

    @Test
    public void parseLectureNames_invalidArgs_throwsParseException() {
        // invalid lecture name with symbols
        String lectureNameString = "!nv@L!d, Week 2";
        assertThrows(ParseException.class, () ->
                MultipleEventsParser.parseLectureNames(lectureNameString));

        // duplicates
        String lectureNameStringWithDuplicates = "Week 2, Week 2";
        assertThrows(ParseException.class, () ->
                MultipleEventsParser.parseLectureNames(lectureNameStringWithDuplicates));
    }

    @Test
    public void parseVideoNames_validArgs_returnsArray() throws ParseException {
        String videoNameString = "Vid 1, Vid 2";
        VideoName[] videoNames = MultipleEventsParser.parseVideoNames(videoNameString);
        assertEquals(TypicalVideos.CONTENT_VIDEO.getName(), videoNames[0]);
        assertEquals(TypicalVideos.ANALYSIS_VIDEO.getName(), videoNames[1]);
    }

    @Test
    public void parseVideoNames_invalidArgs_throwsParseException() {
        // invalid video name with symbols
        String videoNameString = "!nv@L!d, Vid 2";
        assertThrows(ParseException.class, () ->
                MultipleEventsParser.parseVideoNames(videoNameString));

        // duplicates
        String videoNameStringWithDuplicates = "Vid 2, Vid 2";
        assertThrows(ParseException.class, () ->
                MultipleEventsParser.parseVideoNames(videoNameStringWithDuplicates));
    }
}
