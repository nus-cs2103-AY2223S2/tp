package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * An abstract class specifically for delete commands that delete multiple of specified objects at one time
 */
public interface MultipleEventsParser {

    /**
     * Parses an array list of objects into a comma delimiting string containing
     */
    public static String convertArrayListToString(ArrayList<?> arr) {

        // Solution Arraylist of Objects to String provided by Vitalii Fedorenko
        // Solution obtained from Stack Overflow:
        // https://stackoverflow.com/questions/599161/best-way-to-convert-an-arraylist-to-a-string
        return arr.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    /**
     * Parses string into Array of {@code ModuleCode}
     * module codes in string are separated by "," delimiter
     *
     * @param preamble string input of {@code ModuleCode}s
     * @return array of {@code ModuleCode}
     * @throws ParseException if any module code in string is of invalid format
     */
    public static ModuleCode[] parseModuleCodes(String preamble) throws ParseException {
        String[] moduleCodeStrings = preamble.split(",");
        int numOfModuleCodes = moduleCodeStrings.length;
        ModuleCode[] moduleCodes = new ModuleCode[numOfModuleCodes];

        for (int i = 0; i < numOfModuleCodes; i++) {
            moduleCodes[i] = ParserUtil.parseModuleCode(moduleCodeStrings[i]);
        }
        return moduleCodes;
    }

    /**
     * Parses string into Array of {@code LectureName}
     * lecture names in string are separated by "," delimiter
     *
     * @param preamble string input of {@code LectureName}s
     * @return array of {@code LectureName}
     * @throws ParseException if any lecture name in string contains any invalid characters
     */
    public static LectureName[] parseLectureNames(String preamble) throws ParseException {
        String[] lectureNameStrings = preamble.split(",");
        int numOfLectureNames = lectureNameStrings.length;
        LectureName[] lectureNames = new LectureName[numOfLectureNames];

        for (int i = 0; i < numOfLectureNames; i++) {
            lectureNames[i] = ParserUtil.parseLectureName(lectureNameStrings[i]);
        }
        return lectureNames;
    }
}
