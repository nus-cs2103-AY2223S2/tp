package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;

/**
 * An interface containing methods specifically for commands that perform actions on
 * multiple specified objects of the same type at one time
 */
public interface MultipleEventsParser {

    public static final String MESSAGE_DUPLICATES = "No changes made..."
            + "\n" + "Command call contains duplicates!"
            + "\n\n" + "The following duplicate objects were noticed in the command: \n%1$s";

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
     * @param preamble string input of {@code ModuleCode} objects
     * @return array of {@code ModuleCode} objects
     * @throws ParseException if any module code in string is of invalid format
     */
    public static ModuleCode[] parseModuleCodes(String preamble) throws ParseException {

        String[] moduleCodeStrings = preamble.split(",", -1);
        int numOfModuleCodes = moduleCodeStrings.length;

        ModuleCode[] moduleCodes = new ModuleCode[numOfModuleCodes];
        ArrayList<ModuleCode> duplicates = new ArrayList<>();

        for (int i = 0; i < numOfModuleCodes; i++) {
            ModuleCode m = ParserUtil.parseModuleCode(moduleCodeStrings[i]);

            if (containsDuplicates(moduleCodes, m) && !duplicates.contains(m)) {
                duplicates.add(m);
            }

            moduleCodes[i] = m;
        }

        if (duplicates.size() != 0) {
            throw new ParseException(String.format(MESSAGE_DUPLICATES, convertArrayListToString(duplicates)));
        }

        return moduleCodes;
    }

    /**
     * Parses string into Array of {@code LectureName} objects
     * lecture names in string are separated by "," delimiter
     *
     * @param preamble string input of {@code LectureName}s
     * @return array of {@code LectureName} objects
     * @throws ParseException if any lecture name in string contains any invalid characters (excluding ',')
     */
    public static LectureName[] parseLectureNames(String preamble) throws ParseException {
        String[] lectureNameStrings = preamble.split(",", -1);
        int numOfLectureNames = lectureNameStrings.length;

        LectureName[] lectureNames = new LectureName[numOfLectureNames];
        ArrayList<LectureName> duplicates = new ArrayList<>();

        for (int i = 0; i < numOfLectureNames; i++) {
            LectureName l = ParserUtil.parseLectureName(lectureNameStrings[i]);

            if (containsDuplicates(lectureNames, l) && !duplicates.contains(l)) {
                duplicates.add(l);
            }

            lectureNames[i] = l;
        }

        if (duplicates.size() != 0) {
            throw new ParseException(String.format(MESSAGE_DUPLICATES, convertArrayListToString(duplicates)));
        }
        return lectureNames;
    }

    /**
     * Parses string into array of {@code VideoName} objects
     * video names in string are separated by "," delimiter
     *
     * @param preamble string input of {@code VideoName}s
     * @return array of {@code VideoName} objects
     * @throws ParseException if any video name in string contains any invalid characters (excluding ',')
     */
    public static VideoName[] parseVideoNames(String preamble) throws ParseException {
        String[] videoNameStrings = preamble.split(",", -1);
        int numOfVideoNames = videoNameStrings.length;

        VideoName[] videoNames = new VideoName[numOfVideoNames];
        ArrayList<VideoName> duplicates = new ArrayList<>();

        for (int i = 0; i < numOfVideoNames; i++) {
            VideoName v = ParserUtil.parseVideoName(videoNameStrings[i]);

            if (containsDuplicates(videoNames, v) && !duplicates.contains(v)) {
                duplicates.add(v);
            }

            videoNames[i] = v;

        }

        if (duplicates.size() != 0) {
            throw new ParseException(String.format(MESSAGE_DUPLICATES, convertArrayListToString(duplicates)));
        }

        return videoNames;
    }

    /**
     * Checks if the object is in the array
     *
     * @param <S> object type to compare
     * @param arr the array
     * @param obj the object
     * @return true if the object is in the array. Otherwise, false
     */
    private static <S extends Object> boolean containsDuplicates(S[] arr, S obj) {
        for (int i = 0; i < arr.length; i++) {
            if (obj.equals(arr[i])) {
                return true;
            }
        }
        return false;
    }
}
