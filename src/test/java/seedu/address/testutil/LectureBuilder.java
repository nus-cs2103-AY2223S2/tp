package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.video.Video;

/**
 * A utility class to help with building {@code Lecture} objects.
 */
public class LectureBuilder {

    public static final String DEFAULT_NAME = "Lecture 1";

    private LectureName name;
    private Set<Tag> tags;
    private List<Video> videos;

    /**
     * Creates a {@code LectureBuilder} with default details.
     */
    public LectureBuilder() {
        name = new LectureName(DEFAULT_NAME);
        tags = new HashSet<>();
        videos = new ArrayList<>();
    }

    /**
     * Initializes the {@code LectureBuilder} with the data of {@code lectureToCopy}.
     *
     * @param lectureToCopy The lecture containing the data to copy.
     */
    public LectureBuilder(Lecture lectureToCopy) {
        name = lectureToCopy.getName();
        tags = new HashSet<>(lectureToCopy.getTags());
        videos = new ArrayList<>(lectureToCopy.getVideoList());
    }

    /**
     * Sets the {@code name} of the {@code Lecture} that we are building.
     *
     * @param name The name to set to.
     * @return This {@code LectureBuilder}.
     */
    public LectureBuilder withName(String name) {
        this.name = new LectureName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Lecture} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code LectureBuilder}.
     */
    public LectureBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code videos} into a {@code List<Video>} and set it to the {@code Lecture} that we are building.
     *
     * @param videos The videos to set to.
     * @return This {@code LectureBuilder}.
     */
    public LectureBuilder withVideos(Video ... videos) {
        this.videos = Arrays.asList(videos);
        return this;
    }

    /**
     * Returns a {@code Lecture} object with values set to those of this object.
     *
     * @return A {@code Lecture} object with values set to those of this object.
     */
    public Lecture build() {
        return new Lecture(name, tags, videos);
    }

}
