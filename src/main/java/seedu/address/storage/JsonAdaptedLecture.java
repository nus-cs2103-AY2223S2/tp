package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.UniqueVideoList;
import seedu.address.model.video.Video;

/**
 * Jackson-friendly version of {@link Lecture}.
 */
public class JsonAdaptedLecture {

    public static final String MESSAGE_DUPLICATE_VIDEO = "Video list contains duplicate video(s).";
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lecture's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedVideo> videos = new ArrayList<>();

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedLecture} with the given lecture details.
     *
     * @param name The name of the lecture.
     * @param videos The videos of the lecture.
     * @param tagged The tags applied to the lecture.
     */
    @JsonCreator
    public JsonAdaptedLecture(@JsonProperty("name") String name,
            @JsonProperty("videos") List<JsonAdaptedVideo> videos,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;

        if (videos != null) {
            this.videos.addAll(videos);
        }

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Lecture} into a {@code JsonAdaptedLecture} for Jackson use.
     *
     * @param source The lecture to be converted.
     */
    public JsonAdaptedLecture(ReadOnlyLecture source) {
        name = source.getName().name;
        videos.addAll(source
                .getVideoList()
                .stream()
                .map(JsonAdaptedVideo::new)
                .collect(Collectors.toList()));
        tagged.addAll(source
                .getTags()
                .stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts Jackson-friendly adapted lecture object into the model's {@code Lecture} object.
     *
     * @return The resulting {@code Lecture} object.
     * @throws IllegalValueException Indicates that some data constraints were violated in the adapted lecture.
     */
    public Lecture toModelType() throws IllegalValueException {
        final Set<Tag> lectureTags = new HashSet<>();
        for (JsonAdaptedTag adaptedTag : tagged) {
            lectureTags.add(adaptedTag.toModelType());
        }

        final UniqueVideoList lectureVideos = new UniqueVideoList();
        for (JsonAdaptedVideo adaptedVideo : videos) {
            Video video = adaptedVideo.toModelType();

            if (lectureVideos.contains(video)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VIDEO);
            }

            lectureVideos.add(video);
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (!LectureName.isValidName(name)) {
            throw new IllegalValueException(LectureName.MESSAGE_CONSTRAINTS);
        }
        final LectureName lectureName = new LectureName(name);

        return new Lecture(lectureName, lectureTags, lectureVideos.asUnmodifiableObservableList());
    }
}
