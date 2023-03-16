package vimification.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import vimification.model.task.Task;
import vimification.model.task.Todo;
import vimification.model.task.Deadline;
import vimification.model.task.Event;
import vimification.model.task.Status;
import vimification.model.task.DateTime;
import vimification.model.task.Description;

import vimification.model.TaskPlanner;
import vimification.model.ReadOnlyTaskPlanner;

/**
 * Contains utility methods for populating {@code TaskPlanner} with sample data.
 */
public class SampleDataUtil {
    // 57 sample tasks in total
    private static String[] samples = new String[] {
            "Do ES2660 essay.",
            "Project meeting.",
            "Feed my dog, Bobby.",
            "Buy grocery.",
            "Go post office pay the bill.",
            "Help mum run errands.",
            "Meet Prof Ng for career advice.",
            "Abs workout.",
            "FOW proposal review.",
            "Check in with Rag's PD.",
            "Finish up CS2109 mini-project.",
            "Practise OP2 presentation.",
            "Search & apply for internships.",
            "Do research to see which model to get as my new phone.",
            "Go Sim Lim Square to upgrade my laptop.",
            "Play badminton with the boys.",
            "Daily Leetcode practice.",
            "Work on Bridge game side-project.",
            "Learn aadvance hooks in React.",
            "Buy birthday present for Kai Xuan.",
            "Frisbee training with Amos.",
            "Revise for CS4225 midterms.",
            "Learn Django on youtube.",
            "Do CS2103T quiz.",
            "Have High-Tea session with my mum.",
            "Go to the gym.",
            "Go to the library to study.",
            "Touch up my portfolio website.",
            "Piano practice.",
            "Go to the gym.",
            "Go to the park to play with my dog.",
            "Go to the supermarket to buy groceries.",
            "Revise Operating System knowledge.",
            "FOW cohesion day.",
            "Cut my fingernails.",
            "Shave my moustache.",
            "Go to the dentist.",
            "HTHT with Jon",
            "Plan a summer vacation getaway",
            "Tidy up my room",
            "Practise meditation",
            "Read Deep Work by Cal Newport",
            "Watch the latest movie, Suzume no Shizuku",
            "Pick up guitar",
            "Complete a home improvement task",
            "Schedule a date night with your partner",
            "Send an important email to Prof Aaron",
            "Ask Prof Kelvin to write a letter of recommendation",
            "Update my resume/linkedin profile",
            "Organise my closet",
            "Pay rent for this month",
            "Research on class divide in Singapore",
            "Attend CAPT graduation ceremony",
            "Complete lab report for PC1431",
            "Get started on CS2109 ps6",
            "Finish up CS2105 assignment",
            "Revise Data Sturctures and Algorithms knowledge"
    };
    private static int length = samples.length;

    private static String[] tags = new String[] {
            "academic",
            "career",
            "family",
            "house chores",
            "errands friends",
            "other hobbies",
            "sports",
            "group projects",
            "assignments",
            "wellness",
            "learning",
            "other commitments",
            "weekly habits",
            "daily habits",
            "monthly habits"
    };

    static Supplier<Integer> randLength = () -> Math.max(new Random().nextInt(length), 5);
    static Supplier<Integer> randStatus = () -> new Random().nextInt(2);
    static Supplier<Integer> randType = () -> new Random().nextInt(3);

    public static TaskPlanner getSampleTaskPlanner(int n) {
        TaskPlanner taskList = new TaskPlanner();
        for (int i = 0; i < n; i++) {
            taskList.addTask(getTask(i));
        }
        return taskList;
    }

    public static TaskPlanner getSampleTaskPlanner() {
        return getSampleTaskPlanner(randLength.get());
    }

    public static ReadOnlyTaskPlanner getSampleReadOnlyTaskPlanner(int n) {
        return getSampleTaskPlanner(n);
    }

    public static ReadOnlyTaskPlanner getSampleReadOnlyTaskPlanner() {
        return getSampleReadOnlyTaskPlanner(randLength.get());
    }

    private static LocalDateTime generateRandLocalDateTime() {
        // Set the range of future dates to generate
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDateTime = now.plusDays(1); // tomorrow's date
        LocalDateTime endDateTime = now.plusYears(1); // one year from now

        Random random = new Random();
        return startDateTime.plusSeconds(random.nextInt(
                (int) (endDateTime.toEpochSecond(null) - startDateTime.toEpochSecond(null))));
    }

    private static DateTime toDateTime(LocalDateTime ldt) {
        return new DateTime(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    private static DateTime generateRandDateTime() {
        return toDateTime(generateRandLocalDateTime());
    }

    private static DateTime[] generate2RandDataTimes() {
        LocalDateTime ldt1 = generateRandLocalDateTime();
        LocalDateTime ldt2 = generateRandLocalDateTime();
        while (ldt1.isAfter(ldt2)) {
            ldt2 = generateRandLocalDateTime();
        }
        DateTime[] dateTimes = new DateTime[2];
        dateTimes[0] = toDateTime(ldt1);
        dateTimes[1] = toDateTime(ldt2);
        return dateTimes;
    }

    private static Task getTask(int idx) {
        int taskType = randType.get();
        Description description = new Description(samples[idx]);
        Status status = new Status(randStatus.get() == 0);
        switch (taskType) {
        case 0:
            return new Todo(description, status);
        case 1:
            return new Deadline(description, status, generateRandDateTime());
        default:
            DateTime[] dateTimes = generate2RandDataTimes();
            return new Event(description, status, dateTimes[0], dateTimes[1]);
        }
    }

    public static Task generateRandTask() {
        int idx = new Random().nextInt(length);
        return getTask(idx);
    }

    // TODO: as still incomplete
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<String> getStringSet(String... strings) {
        return Arrays.stream(strings)
                .map(String::new)
                .collect(Collectors.toSet());
    }

}
