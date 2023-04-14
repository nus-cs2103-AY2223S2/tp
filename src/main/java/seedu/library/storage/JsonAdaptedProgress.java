package seedu.library.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.model.bookmark.Progress;

/**
 * Jackson-friendly version of {@link Progress}.
 */
public class JsonAdaptedProgress {
    private final String volume;
    private final String chapter;
    private final String page;

    /**
     * Constructs a {@code JsonAdaptedProgress} with the given {@code volume}, {@code chapter} and {@code page}.
     */
    @JsonCreator
    public JsonAdaptedProgress(@JsonProperty("volume") String volume,
                               @JsonProperty("chapter") String chapter,
                               @JsonProperty("page") String page) {
        this.volume = volume;
        this.chapter = chapter;
        this.page = page;
    }

    /**
     * Converts a given {@code Progress} into this class for Jackson use.
     */
    public JsonAdaptedProgress(Progress source) {
        volume = source.volume;
        chapter = source.chapter;
        page = source.page;
    }

    /**
     * Converts this Jackson-friendly adapted progress object into the model's {@code Progress} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted progress.
     */
    public Progress toModelType() throws IllegalValueException {
        String[] splitProgress = {volume, chapter, page};
        if (!Progress.isValidProgress(splitProgress)) {
            throw new IllegalValueException(Progress.MESSAGE_CONSTRAINTS);
        }
        return new Progress(splitProgress);
    }
}
