package tfifteenfour.clipboard.ui;

/**
 * Messages to be displayed in HelpWindow.
 */
public class HelpWindowMessages {
    static final String GENERAL_HELP_MESSAGE = "\n\nHere are some general commands you can use.\n"
            + "1. home: goes back to the home page, which is the Course Page\n"
            + "2. back: goes back to the previous page you were at\n"
            + "3. undo: deletes the change that your last command did\n"
            + "4. help: opens this help window with commands specific to the page you are on\n"
            + "5. clear: clears entries of the page you are at\n"
            + "6. exit: exits the program\n"
            + "7. UP/DOWN arrow keys: scrolls through your command history\n";

    static final String COURSE_HELP_MESSAGE = "Here is what you can do under the [Course] page.\n"
            + "1. add course <COURSE NAME>: adds a new course to the page\n"
            + "\tTry this: add course CS2103T\n"
            + "2. delete course <INDEX>: deletes the selected course\n"
            + "\tTry this: delete course 1\n"
            + "3. edit course <INDEX> <NEW COURSE NAME>: edits an existing course name\n"
            + "\tTry this: edit course 1 CS2101\n"
            + "4. select <INDEX>: choose a course to move to the group page\n"
            + "\tTry this: select 1";

    static final String GROUP_HELP_MESSAGE = "Here is what you can do under the [Group] page.\n"
            + "1. add group <GROUP NAME>: adds a new group to the page\n"
            + "\tTry this: add group T15\n"
            + "2. delete group <INDEX>: deletes the selected group\n"
            + "\tTry this: delete group 1\n"
            + "3. edit group <INDEX> <NEW GROUP NAME>: edits an existing group name\n"
            + "\tTry this: edit group 1 T20\n"
            + "4. select <INDEX>: choose a group to move to the student page\n"
            + "\tTry this: select 1\n"
            + "5. session <INDEX>: choose a group to move to the session page\n"
            + "\tTry this: session 1\n"
            + "6. task <INDEX>: choose a group to move to the task page\n"
            + "\tTry this: task 1";

    static final String STUDENT_HELP_MESSAGE = "Here is what you can do under the [Student] page.\n"
            + "1. add student n/<NAME> p/<PHONE_NUMBER> e/<EMAIL> sid/<STUDENT_ID>: adds a new student to the page\n"
            + "\tTry this: add student n/John Doe p/98765432 e/johnd@example.com sid/A1234567X\n"
            + "2. delete student <INDEX>: deletes the selected student\n"
            + "\tTry this: delete student 1\n"
            + "3. edit student <INDEX> [n/<NAME>] [p/<PHONE_NUMBER>] [e/<EMAIL>] [sid/<STUDENT_NUMBER>]:"
            + " edits an existing student with the given field\n"
            + "\tTry this: edit student 1 n/John Doe\n"
            + "4. copy <INDEX>: copies the email of the student\n"
            + "\tTry this: copy 1\n"
            + "5. find <KEYWORD>: finds students whose names contain any of the given keywords\n"
            + "\tTry this: find John\n"
            + "6. select <INDEX>: select a particular student and display his / her particulars\n"
            + "\tTry this: select 1";

    static final String SESSION_HELP_MESSAGE = "Here is what you can do under the [Session] page.\n"
            + "1. add session <SESSION>: adds a new session to the page\n"
            + "\tTry this: add session Tutorial1\n"
            + "2. delete session <INDEX>: deletes the selected session\n"
            + "\tTry this: delete student 1\n"
            + "3. edit session <INDEX> <NEW SESSION NAME>: edits an existing session name\n"
            + "\tTry this: edit session 1 Tutorial1\n"
            + "4. select <INDEX>: selects an existing session to start taking attendance.\n"
            + "\tTry this: select 1";

    static final String TASK_HELP_MESSAGE = "Here is what you can do under the [Task] page.\n"
            + "1. add task <TASK NAME>: adds a new task to the page\n"
            + "\tTry this: add task CA1\n"
            + "2. delete task <INDEX>: deletes the selected task\n"
            + "\tTry this: delete task 1\n"
            + "3. edit task <INDEX> <NEW TASK NAME>: edits an existing task name\n"
            + "\tTry this: edit task 1 CA5\n"
            + "4. select <INDEX>: selects an existing task to start assigning grades.\n"
            + "\tTry this: select 1";

    static final String ATTENDANCE_HELP_MESSAGE = "Here is what you can do under the [Attendance] page.\n"
            + "1. mark <INDEX> OR mark <INDEX>, <INDEX>, ...: "
            + "marks selected students’ attendance as present for current session.\n"
            + "\tTry this: mark 1 OR mark 2,3,4\n"
            + "2. unmark <INDEX> OR unmark <INDEX>, <INDEX>, ...: "
            + "unmarks selected students’ attendance as absent for current session.\n"
            + "\tTry this: unmark 1 OR unmark 2,3,4\n"
            + "3. attendance: displays the attendance of the current session\n"
            + "\tTry this: attendance";

    static final String GRADES_HELP_MESSAGE = "Here is what you can do under the [Grades] page.\n"
            + "1. assign <INDEX> <GRADE>: assigns a grade to a student\n"
            + "\tTry this: assign 1 80";
}
