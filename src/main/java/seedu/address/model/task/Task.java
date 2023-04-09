package seedu.address.model.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

/**
 * This class is the parent class of tasks that make up the tasklist.
 */

public class Task {

    protected boolean isDone;
    private final TaskDescription description;
    private Comment taskComment;
    private Index personAssignedIndex;
    private String personAssignedName;
    private String personAssignedRole;
    private Score score;
    private String date;
    private String taskType;
    private Boolean isOverdue;

    /**
     * The constructor of the Task that takes in description of the task.
     */
    public Task(TaskDescription description, String date, String taskType) {
        this.description = description;
        this.isDone = false;
        this.personAssignedIndex = null;
        this.personAssignedName = null;
        this.personAssignedRole = null;
        this.score = null;
        this.taskComment = null;
        this.date = date;
        this.taskType = taskType;
        this.isOverdue = null;
    }

    /**
     * Supplies description of the current task when requested.
     *
     * @return TaskDescription description of the task
     */
    public TaskDescription getDescription() {
        return this.description;
    }

    /**
     * Supplies type of the current task when requested.
     * @return String type of the task
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Supplies description of the completeness of current task when requested.
     *
     * @return String description of the completeness of current task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns whether task is done or not.
     *
     * @return Boolean value of whether task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns whether task is done or not
     *
     * @return Boolean value of whether task is overdue.
     * @throws ParseException
     */
    public boolean isOverdue() throws IndexOutOfBoundsException, ParseException {
        String date = getDate();
        if (date.length() == 0) {
            return false;
        }
        String endDate = date.substring(date.length() - 10);
        Date currentDate = new Date();
        Date deadlineDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        return currentDate.after(deadlineDate);
    }

    /**
     * Returns whether task is due today or not
     *
     * @return Boolean value of whether task is due today
     * @throws ParseException
     */
    public boolean isDueToday() throws IndexOutOfBoundsException, ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String date = getDate();
        String endDate = date.substring(date.length() - 10);
        Date currentDate = new Date();
        Date deadlineDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        return fmt.format(currentDate).equals(fmt.format(deadlineDate));
    }

    /**
     * Returns whether task is assigned.
     *
     * @return Boolean value of whether task is assigned to a person.
     */
    public boolean isAssigned() {
        return personAssignedIndex != null;
    }

    /**
     * Marks the current task as done.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the score of the current task.
     *
     * @param score Score to be assigned to the current task
     */
    public void setScore(Score score) {
        this.score = score;
    }

    /**
     * Gets the score of the current task.
     * @return Score value assigned to the current task
     */
    public Score getScore() {
        return this.score;
    }

    /**
     * Leaves a comment to the current task.
     *
     * @param comment Comment by user to be assigned to the current task
     */
    public void setTaskComment(Comment comment) {
        this.taskComment = comment;
    }

    /**
     * Returns the comment of the current task.
     *
     * @return Comment value assigned to the current task
     */
    public Comment getTaskComment() {
        return this.taskComment;
    }

    /**
     * Supplies date of the current task when requested.
     * @return String date of the task
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Assigns a person to the current task.
     *
     * @param personIndex Index of the person to be assigned to the current task
     * @param personToAssign Person to be assigned to the current task
     */
    public void assignPerson(Index personIndex, Person personToAssign) {
        this.personAssignedIndex = personIndex;
        this.personAssignedName = personToAssign.getName().toString();
        this.personAssignedRole = personToAssign.getRole();
    }
    /**
     * Assigns a person to the current task.
     *
     * @param personIndex Index of the person to be assigned to the current task
     * @param personName Name of the person to be assigned to the current task
     * @param personRole Role of the person to be assigned to the current task
     */
    public void assignPerson(Index personIndex, String personName, String personRole) {
        this.personAssignedIndex = personIndex;
        this.personAssignedName = personName;
        this.personAssignedRole = personRole;
    }

    /**
     * Supplies the index of the person assigned to the current task when requested.
     *
     * @return Index index of the person assigned to the current task
     */
    public Index getPersonAssignedIndex() {
        return personAssignedIndex;
    }

    /**
     * Supplies the name of the person assigned to the current task when requested.
     *
     * @return String name of the person assigned to the current task
     */
    public String getPersonAssignedName() {
        return personAssignedName;
    }

    /**
     * Supplies the role of the person assigned to the current task when requested.
     * @return String role of the person assigned to the current task
     */
    public String getPersonAssignedRole() {
        return personAssignedRole;
    }

    /**
     * Changes status of current task as done by assigning isDone as true.
     *
     * @param score Score to be assigned to the current task
     * @return String message to be displayed to user
     */
    public String mark(Task taskToMark, Task markedTask, Score score) {
        this.isDone = true;
        this.setScore(score);
        return "This task has been marked as completed:\n" + this + "\n";
    }

    /**
     * Changes status of current task as not done by assigning isDone as false.
     *
     * @return String message to be displayed to user
     */
    public String unmark() {
        this.isDone = false;
        this.score = null;
        return "This task has been marked as uncompleted:\n" + this + "\n";
    }

    public void deletePersonFromTask(int personIndex) {
        if (this.personAssignedIndex == null) {
            return;
        }

        if (this.personAssignedIndex.getZeroBased() == personIndex) {
            this.personAssignedIndex = null;
            this.personAssignedName = null;
            this.personAssignedRole = null;
            return;
        }

        if (this.personAssignedIndex.getZeroBased() > personIndex) {
            this.personAssignedIndex = Index.fromZeroBased(this.personAssignedIndex.getZeroBased() - 1);
        }

    }

    /**
     * Returns a string representation of the task.
     * @return String representation of the task
     */
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String str = "";
        str = String.format("[" + statusIcon + "] " + this.description);
        return str;
    }

    /**
     * Returns true if both tasks have the same description.
     * This defines a weaker notion of equality between two tasks.
     * @param otherTask
     * @return Boolean value of whether the two tasks have the same description
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    /**
     * Returns true if the input task type is valid.
     * Task type is valid if it's equal to "T", "D" or "E".
     * @param taskType Task type input by user.
     * @return Boolean value of whether task type matches "T", "D" or "E".
     */
    public static boolean isValidTaskType(String taskType) {
        if ((taskType.equals("T")) || (taskType.equals("D")) || (taskType.equals("E"))) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task checkedObj = (Task) obj;
        boolean isSameDescription = this.description.equals(checkedObj.description);

        if (isSameDescription) {
            return true;
        }

        return false;
    }


}
