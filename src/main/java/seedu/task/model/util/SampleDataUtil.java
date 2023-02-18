package seedu.task.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.TaskBook;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Description;
import seedu.task.model.task.Name;
import seedu.task.model.task.Task;


/**
 * Contains utility methods for populating {@code TaskBook} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Alex Yeoh"), new Description("Alex's description"),
                getTagSet("friends")),
            new Task(new Name("Bernice Yu"), new Description("Bernice's description"),
                getTagSet("colleagues", "friends")),
            new Task(new Name("Charlotte Oliveiro"), new Description("Charlotte's description"),
                getTagSet("neighbours")),
            new Task(new Name("David Li"), new Description("David's description"),
                getTagSet("family")),
            new Task(new Name("Irfan Ibrahim"), new Description("Irfan's description"),
                getTagSet("classmates")),
            new Task(new Name("Roy Balakrishnan"), new Description("Roy's description"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyTaskBook getSampleTaskBook() {
        TaskBook sampleAb = new TaskBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
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
