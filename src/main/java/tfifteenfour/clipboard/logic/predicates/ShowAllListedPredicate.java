package tfifteenfour.clipboard.logic.predicates;

import java.util.function.Predicate;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Container for predicates and methods to clear list filters
 */
public class ShowAllListedPredicate {
    private static Predicate<Course> predicateShowAllCourses = unused -> true;
    private static Predicate<Group> predicateShowAllGroups = unused -> true;
    private static Predicate<Student> predicateShowAllStudents = unused -> true;
    private static Predicate<Session> predicateShowAllSessions = unused -> true;
    private static Predicate<Task> predicateShowAllTasks = unused -> true;

    public static void resetCoursesFilter(Model model) {
        model.getRoster().updateFilteredCourses(predicateShowAllCourses);
    }

    public static void resetGroupsFilter(CurrentSelection currentSelection) {
        currentSelection.getSelectedCourse().updateFilteredGroups(predicateShowAllGroups);
    }

    public static void resetStudentsFilter(CurrentSelection currentSelection) {
        currentSelection.getSelectedGroup().updateFilteredStudents(predicateShowAllStudents);
    }

    public static void resetSessionsFilter(CurrentSelection currentSelection) {
        currentSelection.getSelectedGroup().updateFilteredSessions(predicateShowAllSessions);
    }

    public static void resetTasksFilter(CurrentSelection currentSelection) {
        currentSelection.getSelectedGroup().updateFilteredTasks(predicateShowAllTasks);
    }

    /**
     * Clears all filters in all lists
     * @param model model where lists are contained
     * @param currentSelection current selection of lists
     */
    public static void resetAllFilters(Model model, CurrentSelection currentSelection) {
        resetTasksFilter(currentSelection);
        resetSessionsFilter(currentSelection);
        resetStudentsFilter(currentSelection);
        resetGroupsFilter(currentSelection);
        resetCoursesFilter(model);
    }
}
