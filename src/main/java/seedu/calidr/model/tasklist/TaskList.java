package seedu.calidr.model.tasklist;

import java.util.ArrayList;

import seedu.calidr.exception.CalidrException;
import seedu.calidr.exception.CalidrInvalidArgumentException;
import seedu.calidr.model.task.Task;
import seedu.calidr.model.task.params.Priority;

/**
 * Represents a task list manager that aids in storing and manipulating the
 * list of Tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns all the Tasks in the list of Tasks.
     *
     * @return The String response of the chatbot.
     */
    public String listTasks() {
        if (this.tasks.isEmpty()) {
            return "You do not have any tasks added to the list.\n";
        } else {
            StringBuilder response = new StringBuilder("Listing all tasks...\n");
            for (int i = 0; i < this.tasks.size(); i++) {
                response.append(i + 1).append(") ").append(this.tasks.get(i)).append("\n");
            }
            return response.toString();
        }
    }

    private String markUnmark(int taskNumber, boolean isMarkCommand) throws CalidrException {

        taskNumber--;

        if (taskNumber >= 0 && taskNumber < this.tasks.size()) {
            if (isMarkCommand) {
                this.tasks.get(taskNumber).mark();
                return String.format("I have marked Task %d as done.\n%s\n", //
                        taskNumber, this.tasks.get(taskNumber));
            } else {
                this.tasks.get(taskNumber).unmark();
                return String.format("I have marked Task %d as undone.\n%s\n", //
                        taskNumber, this.tasks.get(taskNumber));
            }
        } else {
            throw new CalidrInvalidArgumentException("Sorry... That is an invalid task number :/");
        }

    }

    /**
     * Marks a particular Task in the list of Tasks, as done.
     *
     * @param taskNumber The number to indicate which Task is to be marked as done.
     * @return The String response of the chatbot.
     * @throws CalidrException When the task number given is not valid or
     *                         when there is an error in writing to the file.
     */
    public String markTask(int taskNumber) throws CalidrException {
        return this.markUnmark(taskNumber, true);
    }

    /**
     * Marks a particular Task in the list of Tasks, as undone.
     *
     * @param taskNumber The number to indicate which Task is to be marked as undone.
     * @return The String response of the chatbot.
     * @throws CalidrException When the task number given is not valid or
     *                         when there is an error in writing to the file.
     */
    public String unmarkTask(int taskNumber) throws CalidrException {
        return this.markUnmark(taskNumber, false);
    }

    /**
     * Adds a given Task to the list of Tasks.
     *
     * @param task     The Task to be added to the list of Tasks.
     * @param taskType The type of the given task.
     * @return The String response of the chatbot.
     * @throws CalidrException When there is an error in writing to the file.
     */
    public String addTask(Task task, String taskType) throws CalidrException {
        assert task != null;
        assert taskType != null;

        this.tasks.add(task);

        return "I have added the " + taskType + " to the list :)\n" + task + "\n";

    }

    /**
     * Deletes a Task from the list of Tasks.
     *
     * @param taskNumber The number to indicate which Task is to be deleted.
     * @return The String response of the chatbot.
     * @throws CalidrException When the task number given is not valid or
     *                         when there is an error in writing to the file.
     */
    public String deleteTask(int taskNumber) throws CalidrException {
        boolean isValidTaskNumber = (taskNumber > 0 && taskNumber <= this.tasks.size());

        if (isValidTaskNumber) {
            Task removedTask = this.tasks.remove(taskNumber - 1);

            return String.format("I have removed Task %d from the list.\n%s" //
                            + "\nYou now have %d task(s) in the list.\n", //
                    taskNumber, removedTask, this.tasks.size());

        } else {
            throw new CalidrInvalidArgumentException("Sorry... That is an invalid task number :/");
        }

    }

    /**
     * Returns all the Tasks in the list of Tasks, that have the given keyword.
     *
     * @param keyword The keyword to search for, in the list of Tasks.
     * @return The String response of the chatbot.
     */
    public String findKeywordInTasks(String keyword) {
        assert keyword != null;

        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        ArrayList<Integer> requiredTaskNumbers = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            String taskDescription = task.getTitle().toLowerCase();

            int findIndex = taskDescription.indexOf(keyword.toLowerCase());

            if (findIndex != -1) {
                tasksWithKeyword.add(task);
                requiredTaskNumbers.add(i + 1);
            }

        }

        if (tasksWithKeyword.size() == 0) {
            return "I could not find any tasks with the keyword '" + keyword + "' :/\n";

        } else {
            StringBuilder response = new StringBuilder();
            response.append("Listing all tasks with the keyword '").append(keyword).append("'...\n");
            for (int i = 0; i < tasksWithKeyword.size(); i++) {
                response.append(requiredTaskNumbers.get(i)).append(") ").append(tasksWithKeyword.get(i)).append("\n");
            }

            return response.toString();

        }
    }

    public String setTaskPriority(int taskNumber, Priority priority) throws CalidrException {
        taskNumber--;

        if (taskNumber >= 0 && taskNumber < this.tasks.size()) {
            this.tasks.get(taskNumber).setPriority(priority);
            return String.format("I have changed the priority of Task %d to %s.\n%s\n", //
                    taskNumber, priority, this.tasks.get(taskNumber));
        } else {
            throw new CalidrInvalidArgumentException("Sorry... That is an invalid task number :/");
        }
    }

}
