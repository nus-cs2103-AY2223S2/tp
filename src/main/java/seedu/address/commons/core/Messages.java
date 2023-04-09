package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%d students listed!\n%s";
    public static final String MESSAGE_HOMEWORK_ADDED_SUCCESS = "New homework added:\n%s\n"
            + "To the following students:\n%s";
    public static final String MESSAGE_HOMEWORK_LISTED_OVERVIEW = "%d homework from %d student listed:\n%s";
    public static final String MESSAGE_ALL_HOMEWORK_LISTED_OVERVIEW = "%d homework listed:\n%s";
    public static final String MESSAGE_NO_HOMEWORK_FOUND = "No homework found";
    public static final String MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX = "The homework index provided is invalid";
    public static final String MESSAGE_HOMEWORK_DELETED_SUCCESS = "Homework : %s. %s\n"
            + "Deleted from the %s\n";
    public static final String MESSAGE_LESSON_ADDED_SUCCESS = "New lesson added: \n%s \n"
            + "To the following students: \n%s";
    public static final String MESSAGE_HOMEWORK_ALREADY_MARKED_AS_DONE =
            "Homework %s\nof student %s is already marked as done\n";
    public static final String MESSAGE_HOMEWORK_MARKED_AS_DONE = "Homework %s of\nstudent %s is marked as done\n";
    public static final String MESSAGE_HOMEWORK_MARKED_AS_UNDONE = "Homework %s of\nstudent %s is marked as undone\n";
    public static final String MESSAGE_HOMEWORK_ALREADY_MARKED_AS_UNDONE =
            "Homework %s of\bstudent %s is already marked as undone\n";
    public static final String MESSAGE_INVALID_STUDENT_NAME = "No student found!\n";
    public static final String MESSAGE_NO_LESSON_FOUND = "No lesson is found!";
    public static final String MESSAGE_ALL_LESSONS_LISTED_OVERVIEW = "%d lessons from all students listed:\n%s";
    public static final String MESSAGE_LESSONS_LISTED_OVERVIEW = "%d lessons from %d students listed: \n%s";
    public static final String MESSAGE_LESSON_DELETED_SUCCESS = "Lesson: %s, %s\n"
            + "Deleted from the %s\n";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = "The lesson index provided is invalid";
    public static final String MESSAGE_INVALID_EXAM_DISPLAYED_INDEX = "The exam index provided is invalid";
    public static final String MESSAGE_EXAM_DELETED_SUCCESS = "Exam: %s, %s\n"
            + "Deleted from the %s\n";
    public static final String MESSAGE_EXAM_ADDED_SUCCESS = "New exam added: \n%s \n"
            + "To the following students: \n%s";
    public static final String MESSAGE_EXAMS_LISTED_OVERVIEW = "%d exams from %d students listed: \n%s";
    public static final String MESSAGE_ALL_EXAMS_LISTED_OVERVIEW = "%d exams from all students listed:\n%s";
    public static final String MESSAGE_NO_EXAM_FOUND = "No exam is found!";
    public static final String MESSAGE_EXAM_NOT_COMPLETED = "Exam is not yet completed, a grade cannot be assigned!";
    public static final String MESSAGE_EXAM_UPDATED_SUCCESS = "Exam %s of student %s is updated to:\n"
            + "Exam name: %s\n"
            + "Start Time: %s\n"
            + "End Time: %s\n"
            + "Weightage: %s" + "%%" + "\n"
            + "Grade: %s\n";
    public static final String MESSAGE_HOMEWORK_UPDATED_SUCCESS = "Homework %s of student %s is updated to:\n"
            + "Homework name: %s\n"
            + "Deadline: %s\n";
    public static final String MESSAGE_LESSON_UPDATED_SUCCESS = "Lesson %s of student %s is updated to:\n"
            + "Lesson name: %s\n"
            + "Start Time: %s\n"
            + "End Time: %s\n";
    public static final String MESSAGE_HAS_DUPLICATE_NAMES = "Duplicate names detected for **%s**."
            + "\nPlease enter full name(s)";
    public static final String MESSAGE_RESULT_IN_DUPLICATE = "The result of the command will result in duplicate "
            + "%s.\nPlease check the name(s) entered";
    public static final String MESSAGE_NO_SUCH_STUDENT = "No student found: **%s**.\nPlease check the name entered";
    public static final String MESSAGE_INVALID_LESSON_TIME = "Start time cannot be after end time";
    public static final String MESSAGE_INVALID_LESSON_DURATION =
            "The lesson duration is too short(< 30 min)/long(> 3 hours)";
    public static final String MESSAGE_INVALID_EXAM_TIME = "Exam start time cannot be after exam end time";
    public static final String MESSAGE_INVALID_EXAM_DURATION =
            "The exam duration is too short(< 30 min)/long(> 3 hours)";
    public static final String MESSAGE_INVALID_DONE_INPUT = "Invalid input for done/ field. Accepted inputs:"
            + "done, not done";
    public static final String MESSAGE_DEADLINE_IN_PAST = "Deadline cannot be in the past!";
    public static final String MESSAGE_ONLY_ONE_STUDENT = "Only one student name is allowed!";
    public static final String MESSAGE_EMPTY_STUDENT = "Student name cannot be empty!";
    public static final String MESSAGE_ONLY_ONE_HOMEWORK = "Only one homework name is allowed!";
    public static final String MESSAGE_EMPTY_HOMEWORK = "Homework name cannot be empty!";
    public static final String MESSAGE_ONLY_ONE_INDEX = "Only one index is allowed!";
    public static final String MESSAGE_EMPTY_INDEX = "Index cannot be empty!";
    public static final String MESSAGE_ONLY_ONE_DEADLINE = "Only one deadline is allowed!";
    public static final String MESSAGE_EMPTY_DEADLINE = "Deadline cannot be empty!";
    public static final Object MESSAGE_ONLY_ONE_STATUS = "Only one status is allowed!";
    public static final Object MESSAGE_EMPTY_STATUS = "Status cannot be empty!";
    public static final String MESSAGE_CONTAIN_STUDENT_NAME = "There is at least one existing student "
            + "whose name contains \"%s\".";
    public static final String MESSAGE_EXTENDED_STUDENT_NAME = "There is at least one existing student "
            + "whose name is contained in \"%s\".";
    public static final String MESSAGE_CONFLICTING_LESSON_TIME = "You already have a lesson during this time!";
    public static final String MESSAGE_CONFLICTING_EXAM_TIME = "This student has an exam during this time!";
}
