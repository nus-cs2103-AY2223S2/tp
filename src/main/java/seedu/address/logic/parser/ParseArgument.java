package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;

import static java.util.Objects.requireNonNull;

/**
 * A dummy class containing utility methods used for parsing strings into ModuleCode, LectureName, and Video.
 */
public class ParseArgument {

    /**
     * Parses a {@code String moduleCode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ModuleCode parseModule(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCode);
    }

    /**
     * Parses a {@code String lectureName} into a {@code LectureName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */

    public static LectureName parseLecture(String lectureName) throws ParseException {
        requireNonNull(lectureName);
        String trimmedLectureName = lectureName.trim();
        if (!LectureName.isValidName(lectureName)) {
            throw new ParseException(LectureName.MESSAGE_CONSTRAINTS);
        }
        return new LectureName(trimmedLectureName);
    }

    /**
     * Parses a {@code String lectureName} into a {@code Video}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */

    public static VideoName parseVideo(String videoName) {
        requireNonNull(videoName);
        String trimmedVideoName = videoName.trim();
        return new VideoName(trimmedVideoName);
    }

}