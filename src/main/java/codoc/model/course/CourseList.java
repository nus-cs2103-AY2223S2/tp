package codoc.model.course;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the list of courses that CoDoc supports
 */
public class CourseList {
    public static final ArrayList<String> COURSE_LIST = new ArrayList<>(
            Arrays.asList(
                    "Computer Science",
                    "Business Analytics",
                    "Information Systems",
                    "Information Security",
                    "Computer Engineering"
            )
    );

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < COURSE_LIST.size(); i++) {
            result = result + String.format("\n%d. %s", i + 1, COURSE_LIST.get(i));
        }
        return result;
    }
}
