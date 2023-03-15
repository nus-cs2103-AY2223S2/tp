package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;

/**
 * Jackson-friendly version of {@link Video}.
 */
public class JsonAdaptedVideo {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Video's %s field is missing!";

    private final String name;
    private final boolean hasWatched;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedVideo} with the given video details.
     *
     * @param name The name of the video.
     * @param hasWatched The watch status of the video.
     * @param tagged The tags applied to the video.
     */
    @JsonCreator
    public JsonAdaptedVideo(@JsonProperty("name") String name,
            @JsonProperty("hasWatched") boolean hasWatched,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.hasWatched = hasWatched;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Video} into a {@code JsonAdaptedVideo} for Jackson use.
     *
     * @param source The video to be converted.
     */
    public JsonAdaptedVideo(Video source) {
        name = source.getName().name;
        hasWatched = source.hasWatched();
        tagged.addAll(source
                .getTags()
                .stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted video object into the model's {@code Video} object.
     *
     * @return The resulting {@code Video} object.
     * @throws IllegalValueException Indicates that some data constraints were violated in the adapted video.
     */
    public Video toModelType() throws IllegalValueException {
        final Set<Tag> videoTags = new HashSet<>();
        for (JsonAdaptedTag adaptedTag : tagged) {
            videoTags.add(adaptedTag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (!VideoName.isValidName(name)) {
            throw new IllegalValueException(VideoName.MESSAGE_CONSTRAINTS);
        }
        final VideoName videoName = new VideoName(name);

        return new Video(videoName, hasWatched, videoTags);
    }
}
