package tfifteenfour.clipboard.storage.serializedclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Serializes a Task to JSON format.
 */
public class SerializedTask {
    private String taskName;
    private final List<SerializedStudent> keys = new ArrayList<>();
    private final List<Integer> values = new ArrayList<>();


    /**
     * Constructs a {@code SerializedTask} with the given session.
     */
    public SerializedTask(Task task) {
        this.taskName = task.getTaskName();
        Map<Student, Integer> studentWithGrades = task.getGrades();
        for (Student student : studentWithGrades.keySet()) {
            keys.add(new SerializedStudent(student));
            values.add(studentWithGrades.get(student));
        }

    }

    /**
     * Constructs an empty SerializedTask object.
     */
    public SerializedTask() {}

    @JsonProperty("taskName")
    public String getSessionName() {
        return taskName;
    }

    @JsonProperty("keys")
    public List<SerializedStudent> getKeys() {
        return this.keys;
    }

    @JsonProperty("values")
    public List<Integer> getValues() {
        return this.values;
    }

    /**
     * Converts current SerializedTask object into a Task object and returns
     * it.
     * @return A Task object that corresponds to this SerializedTask object.
     */
    public Task toModelType() {
        Task newTask = new Task(this.taskName);

        Map<Student, Integer> newGrades = newTask.getGrades();
        for (int i = 0; i < keys.size(); i++) {
            newGrades.put(
                    keys.get(i).toModelType(),
                    values.get(i)
            );
        }
        return newTask;
    }
}
