package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.Tracker;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;
import seedu.address.model.video.Video;
import seedu.address.model.video.VideoName;
import seedu.address.model.video.VideoTimestamp;

/**
 * Contains utility methods for populating {@code Tracker} with sample data.
 */
public class SampleDataUtil {
    public static ReadOnlyTracker getSampleTracker() {
        Tracker tracker = new Tracker();
        tracker.setModules(Arrays.asList(getSampleModules()));

        return tracker;
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModuleCode("CS2040S"), new ModuleName("Data Structures and Algorithms"),
                    getTagSet("Heavy", "Math", "Analysis"), Arrays.asList(getSampleLecturesCs2040s())),
            new Module(new ModuleCode("ST2334"), new ModuleName("Probability and Statistics"),
                    getTagSet("Math", "Probability"), Arrays.asList(getSampleLecturesSt2334())),
        };
    }

    public static Lecture[] getSampleLecturesCs2040s() {
        return new Lecture[] {
            new Lecture(new LectureName("Week 1"), getTagSet("Intro"),
                    Arrays.asList(getSampleVideos1())),
            new Lecture(new LectureName("Week 2"), getTagSet("Sorting"),
                    Arrays.asList(getSampleVideos2())),
            new Lecture(new LectureName("Week 3"), getTagSet("Arrays", "LinkedList"),
                    Arrays.asList(getSampleVideos1())),
            new Lecture(new LectureName("Week 4"), getTagSet("Stacks", "Queues"),
                    Arrays.asList(getSampleVideos2())),
            new Lecture(new LectureName("Week 5"), getTagSet("Hashing"),
                    Arrays.asList(getSampleVideos1())),
            new Lecture(new LectureName("Week 6"), getTagSet("BloomFilter"),
                    Arrays.asList(getSampleVideos3())),
        };
    }

    public static Lecture[] getSampleLecturesSt2334() {
        return new Lecture[] {
            new Lecture(new LectureName("Topic 1"), getTagSet("Intro"),
                    Arrays.asList(getSampleVideos2())),
            new Lecture(new LectureName("Topic 2"), getTagSet("Probability"),
                    Arrays.asList(getSampleVideos2())),
            new Lecture(new LectureName("Topic 3"), getTagSet("RandomVariables"),
                    Arrays.asList(getSampleVideos2())),
            new Lecture(new LectureName("Topic 4"), getTagSet("JointDistributions"),
                    Arrays.asList(getSampleVideos3())),
            new Lecture(new LectureName("Topic 5"), getTagSet("Sampling"),
                    Arrays.asList(getSampleVideos3())),
        };
    }

    public static Video[] getSampleVideos1() {
        return new Video[] {
            new Video(new VideoName("Vid 1"), true, new VideoTimestamp(), getTagSet("Algo")),
            new Video(new VideoName("Vid 2"), true, new VideoTimestamp(), getTagSet("Analysis")),
        };
    }

    public static Video[] getSampleVideos2() {
        return new Video[] {
            new Video(new VideoName("Vid"), true, new VideoTimestamp(), new HashSet<>()),
        };
    }

    public static Video[] getSampleVideos3() {
        return new Video[] {
            new Video(new VideoName("Vid"), false, new VideoTimestamp("00:24:20"), new HashSet<>()),
        };
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
