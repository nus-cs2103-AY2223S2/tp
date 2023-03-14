package trackr.testutil;

import java.util.List;

import trackr.model.task.Task;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * A utility class to help with building TaskContainsKeywordsPredicate objects.
 */
public class TaskPredicateBuilder {

    private TaskContainsKeywordsPredicate taskPredicate;

    public TaskPredicateBuilder() {
        taskPredicate = new TaskContainsKeywordsPredicate();
    }

    public TaskPredicateBuilder(TaskContainsKeywordsPredicate taskPredicate) {
        this.taskPredicate = new TaskContainsKeywordsPredicate(taskPredicate);
    }

    /**
     * Returns an {@code TaskContainsKeywordsPredicate} with fields containing {@code task}'s details
     */
    public TaskPredicateBuilder(List<String> taskNameKeywords, Task task) {
        taskPredicate = new TaskContainsKeywordsPredicate();
        taskPredicate.setTaskNameKeywords(taskNameKeywords);
        taskPredicate.setTaskName(task.getTaskName());
        taskPredicate.setTaskDeadline(task.getTaskDeadline());
        taskPredicate.setTaskStatus(task.getTaskStatus());
    }

    /**
     * Sets the {@code TaskNameKeywords} of the {@code TaskContainsKeywordsPredicate} that we are building.
     */
    public TaskPredicateBuilder withTaskNameKeywords(List<String> taskNameKeywords) {
        if (taskNameKeywords != null) {
            taskPredicate.setTaskNameKeywords(taskNameKeywords);
        }
        return this;
    }

    /**
     * Sets the {@code TaskName} of the {@code TaskContainsKeywordsPredicate} that we are building.
     */
    public TaskPredicateBuilder withTaskName(String taskName) {
        if (taskName != null) {
            taskPredicate.setTaskName(new TaskName(taskName));
        }
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code TaskContainsKeywordsPredicate} that we are building.
     */
    public TaskPredicateBuilder withTaskDeadline(String taskDeadline) {
        if (taskDeadline != null) {
            taskPredicate.setTaskDeadline(new TaskDeadline(taskDeadline));
        }
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code TaskContainsKeywordsPredicate} that we are building.
     */
    public TaskPredicateBuilder withTaskStatus(String taskStatus) {
        if (taskStatus != null) {
            taskPredicate.setTaskStatus(new TaskStatus(taskStatus));
        }
        return this;
    }

    public TaskContainsKeywordsPredicate build() {
        return taskPredicate;
    }
}
