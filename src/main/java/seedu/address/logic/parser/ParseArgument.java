package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.VideoName;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static VideoName parseVideo(String videoName) throws ParseException {
        requireNonNull(videoName);
        String trimmedVideoName = videoName.trim();
        if (!VideoName.isValidName(trimmedVideoName)) {
            throw new ParseException(VideoName.MESSAGE_CONSTRAINTS);
        }
        return new VideoName(trimmedVideoName);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */

    public static Tag parseSingleTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String} of comma-separated tags into a {@code Set} of {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */

    public static Set<Tag> parseMultiTags(String tags) throws ParseException {
        requireNonNull(tags);
        String[] arrayOfTags = tags.split(",");
        List<Tag> listOfTags = Arrays.stream(arrayOfTags)
                .map(tag -> tag.trim())
                .map(trimmedtag -> new Tag(trimmedtag))
                .collect(Collectors.toList());

        for (Tag tag : listOfTags) {
            if (!Tag.isValidTagName(tag.tagName)) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
        }
        return new HashSet<>(listOfTags);
    }

}