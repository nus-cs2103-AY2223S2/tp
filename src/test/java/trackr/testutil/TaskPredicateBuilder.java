package trackr.testutil;

import java.util.List;

import trackr.model.task.Task;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskDescriptor;
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
        taskPredicate.setTaskNameKeywords(taskNameKeywords);
        return this;
    }

    /**
     * Sets the {@code TaskName} of the {@code TaskContainsKeywordsPredicate} that we are building.
     */
    public TaskPredicateBuilder withTaskName(String taskName) {
        taskPredicate.setTaskName(new TaskName(taskName));
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code TaskContainsKeywordsPredicate} that we are building.
     */
    public TaskPredicateBuilder withTaskDeadline(String taskDeadline) {
        taskPredicate.setTaskDeadline(new TaskDeadline(taskDeadline));
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code TaskContainsKeywordsPredicate} that we are building.
     */
    public TaskPredicateBuilder withTaskStatus(String taskStatus) {
        taskPredicate.setTaskStatus(new TaskStatus(taskStatus));
        return this;
    }

    public TaskDescriptor build() {
        return taskPredicate;
    }
}
