package seedu.task.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.task.model.Planner;
import seedu.task.model.ReadOnlyPlanner;
import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.TaskBook;
import seedu.task.model.calendar.DailyPlan;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Description;
import seedu.task.model.task.Effort;
import seedu.task.model.task.Name;
import seedu.task.model.task.SimpleTask;
import seedu.task.model.task.Task;


/**
 * Contains utility methods for populating {@code TaskBook} and {@code Planner} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new SimpleTask(new Name("Task A"), new Description("A's description"),
                getTagSet("CS2102"), new Effort(5)),
            new SimpleTask(new Name("Task B"), new Description("B's description"),
                getTagSet("CS2102", "Project"), new Effort(6)),
            new SimpleTask(new Name("Task C"), new Description("C's description"),
                getTagSet("CS2106", "Project"), new Effort(2)),
            new SimpleTask(new Name("Task D"), new Description("D's description"),
                getTagSet("CS2106"), new Effort(0)),
            new SimpleTask(new Name("Task I"), new Description("I's description"),
                getTagSet("CS2103T"), new Effort(10)),
            new SimpleTask(new Name("Task R"), new Description("R's description"),
                getTagSet("CS2101"), new Effort(2))
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

    public static DailyPlan[] getSampleDailyPlan() {
        LocalDate startDate = LocalDate.parse("2023-04-26");
        DailyPlan[] dailyPlans = new DailyPlan[30];
        for (int i = 0; i < 30; i++) {
            dailyPlans[i] = new DailyPlan(5, startDate.plusDays(i));
        }
        return dailyPlans;
    }

    public static ReadOnlyPlanner getSamplePlanner() {
        Planner sample = new Planner();
        for (DailyPlan samplePlan : getSampleDailyPlan()) {
            sample.addDailyPlan(samplePlan);
        }
        return sample;
    }
}
