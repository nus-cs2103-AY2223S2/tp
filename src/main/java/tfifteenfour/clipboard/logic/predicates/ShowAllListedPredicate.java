package tfifteenfour.clipboard.logic.predicates;

import java.util.function.Predicate;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.task.Task;

public class ShowAllListedPredicate {
	private static Predicate<Course> PREDICATE_SHOW_ALL_COURSES = unused -> true;
	private static Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;
	private static Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
	private static Predicate<Session> PREDICATE_SHOW_ALL_SESSION = unused -> true;
	private static Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

	public static void resetCoursesFilter(Model model) {
		model.getRoster().updateFilteredCourses(PREDICATE_SHOW_ALL_COURSES);
	}

	public static void resetGroupsFilter(CurrentSelection currentSelection) {
		currentSelection.getSelectedCourse().updateFilteredGroups(PREDICATE_SHOW_ALL_GROUPS);
	}

	public static void resetStudentsFilter(CurrentSelection currentSelection) {
		currentSelection.getSelectedGroup().updateFilteredStudents(PREDICATE_SHOW_ALL_STUDENTS);
	}

	public static void resetSessionsFilter(CurrentSelection curretSelection) {
		curretSelection.getSelectedGroup().updateFilteredSessions(PREDICATE_SHOW_ALL_SESSION);
	}

	public static void resetTasksFilter(CurrentSelection currentSelection) {
		currentSelection.getSelectedGroup().updateFilteredTasks(PREDICATE_SHOW_ALL_TASKS);
	}

	public static void resetAllFilters(Model model, CurrentSelection currentSelection) {
		resetTasksFilter(currentSelection);
		resetSessionsFilter(currentSelection);
		resetStudentsFilter(currentSelection);
		resetGroupsFilter(currentSelection);
		resetCoursesFilter(model);
	}
}
