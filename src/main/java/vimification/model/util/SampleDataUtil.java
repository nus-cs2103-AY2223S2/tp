package vimification.model.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
            "Tidy up my room",
            "FOW proposal review.",
            "Practise meditation",
            "Abs workout.",
            "Do ES2660 essay.",
            "Pick up guitar",
            "Read Deep Work by Cal Newport",
            "Meet Prof Ng for career advice.",
            "Practise OP2 presentation.",
            "Get started on CS2109 ps6",
            "Go to the gym.",
            "Revise Operating System knowledge.",
            "Go to the park to play with my dog.",
            "Shave my moustache.",
            "Research on class divide in Singapore",
            "FOW cohesion day.",
            "Piano practice.",
            "Check in with Rag's PD.",
            "Go to the library to study.",
            "Go to the supermarket to buy groceries.",
            "Learn advance hooks in React.",
            "HTHT with Jon",
            "Project meeting.",
            "Play badminton with the boys.",
            "Daily Leetcode practice.",
            "Do CS2103T quiz.",
            "Search & apply for internships.",
            "Cut my fingernails.",
            "Go to the dentist.",
            "Organise my closet",
            "Go Sim Lim Square to upgrade my laptop.",
            "Schedule a date night with your partner",
            "Buy birthday present for Kai Xuan.",
            "Learn Django on youtube.",
            "Help mum run errands.",
            "Send an important email to Prof Aaron",
            "Attend CAPT graduation ceremony",
            "Frisbee training with Amos.",
            "Revise for CS4225 midterms.",
            "Go post office pay the bill.",
            "Finish up CS2105 assignment",
            "Revise Data Sturctures and Algorithms knowledge",
            "Complete a home improvement task",
            "Do research to see which model to get as my new phone.",
            "Update my resume/linkedin profile",
            "Plan a summer vacation getaway",
            "Pay rent for this month",
            "Touch up my portfolio website.",
            "Feed my dog, Bobby.",
            "Complete lab report for PC1431",
            "Work on Bridge game side-project.",
            "Ask Prof Kelvin to write a letter of recommendation",
            "Watch the latest movie, Suzume no Shizuku",
            "Finish up CS2109 mini-project.",
            "Buy grocery.",
            "Have High-Tea session with my mum."
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
                (int) (endDateTime.toEpochSecond(ZoneOffset.UTC) -
                        startDateTime.toEpochSecond(ZoneOffset.UTC))));
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
