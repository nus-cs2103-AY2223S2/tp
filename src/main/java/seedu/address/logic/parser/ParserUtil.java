package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";


    /**
     * Parses a {@code String} of comma-separated tags into a {@code Set} of {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Set<Tag> parseMultiTags(String tags) throws ParseException {
        requireNonNull(tags);
        if (tags.trim().equals("")) {
            return new HashSet<>();
        }

        String[] arrayOfTags = tags.split(",", -1);

        List<String> listOfInvalidTagName = Arrays.stream(arrayOfTags)
                .map(tag -> tag.trim())
                .filter(trimmedTag -> !Tag.isValidTagName(trimmedTag))
                .collect(Collectors.toList());

        if (listOfInvalidTagName.size() > 0) {
            throw new ParseException(String.format(Tag.MESSAGE_CONSTRAINTS,
                    String.join(", ", listOfInvalidTagName)));
        }

        List<Tag> listOfTags = Arrays.stream(arrayOfTags)
                .map(tag -> tag.trim())
                .map(trimmedTag -> new Tag(trimmedTag))
                .collect(Collectors.toList());

        return new HashSet<>(listOfTags);
    }

    /**
     * Parses a {@code List<String>} of tag descriptions into a {@code Set} of {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Set<Tag> parseMultiTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);

        List<String> listOfInvalidTagName = tags.stream()
                .map(tag -> tag.trim())
                .filter(trimmedTag -> !Tag.isValidTagName(trimmedTag))
                .collect(Collectors.toList());

        if (listOfInvalidTagName.size() > 0) {
            throw new ParseException(String.format(Tag.MESSAGE_CONSTRAINTS,
                    String.join(", ", listOfInvalidTagName)));
        }

        List<Tag> listOfTags = tags.stream()
                .map(tag -> tag.trim())
                .map(trimmedTag -> new Tag(trimmedTag))
                .collect(Collectors.toList());

        return new HashSet<>(listOfTags);
    }


    /**
     * Parses a {@code String moduleCode} into a {@code ModuleCode}.<p>
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses a {@code List<String>} of module codes into a {@code Set} of {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */

    public static Set<ModuleCode> parseMultiModuleCode(String moduleCodes) throws ParseException {
        requireNonNull(moduleCodes);

        if (moduleCodes.trim().equals("")) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }

        String[] arrayOfModuleCode = moduleCodes.split(",");

        List<String> listOfInvalidModuleCode = Arrays.stream(arrayOfModuleCode)
                .map(moduleCode -> moduleCode.trim())
                .filter(trimmedModuleCode -> !ModuleCode.isValidCode(trimmedModuleCode))
                .collect(Collectors.toList());

        if (listOfInvalidModuleCode.size() > 0) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }

        List<ModuleCode> listOfModuleCode = Arrays.stream(arrayOfModuleCode)
                .map(moduleCode -> moduleCode.trim())
                .map(trimmedModuleCode -> new ModuleCode(trimmedModuleCode))
                .collect(Collectors.toList());

        return new HashSet<>(listOfModuleCode);
    }

    /**
     * Parses a {@code String moduleName} into a {@code ModuleName}.<p>
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static ModuleName parseModuleName(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedModuleName = moduleName.trim();
        if (!ModuleName.isValidName(trimmedModuleName)) {
            throw new ParseException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        return new ModuleName(trimmedModuleName);
    }

    /**
     * Parses a {@code String lectureName} into a {@code LectureName}.<p>
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lectureName} is invalid.
     */
    public static LectureName parseLectureName(String lectureName) throws ParseException {
        requireNonNull(lectureName);
        String trimmedLectureName = lectureName.trim();
        if (!LectureName.isValidName(trimmedLectureName)) {
            throw new ParseException(LectureName.MESSAGE_CONSTRAINTS);
        }
        return new LectureName(trimmedLectureName);
    }

    /**
     * Parses a {@code String videoName} into a {@code VideoName}.<p>
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code videoName} is invalid.
     */
    public static VideoName parseVideoName(String videoName) throws ParseException {
        requireNonNull(videoName);
        String trimmedVideoName = videoName.trim();
        if (!VideoName.isValidName(trimmedVideoName)) {
            throw new ParseException(VideoName.MESSAGE_CONSTRAINTS);
        }
        return new VideoName(trimmedVideoName);
    }

    /**
     * Parses a {@code String videoTimestamp} into a {@code VideoTimestamp}.<p>
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code videoTimestamp} is invalid.
     */
    public static VideoTimestamp parseVideoTimestamp(String videoTimestamp) throws ParseException {
        requireNonNull(videoTimestamp);
        String trimmedVideoTimestamp = videoTimestamp.trim();
        try {
            VideoTimestamp.validateTimestamp(trimmedVideoTimestamp);
        } catch (IllegalArgumentException iae) {
            throw new ParseException(iae.getMessage());
        }

        return new VideoTimestamp(trimmedVideoTimestamp);
    }
}
