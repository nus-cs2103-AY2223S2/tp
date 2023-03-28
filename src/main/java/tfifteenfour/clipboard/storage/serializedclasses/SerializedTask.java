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
    private final List<SerializedStudent> students = new ArrayList<>();
    private final List<Integer> grades = new ArrayList<>();

    /**
     * Constructs a {@code SerializedTask} with the given task.
     */
    public SerializedTask(Task task) {
        this.taskName = task.getTaskName();
        Map<Student, Integer> taskGrades = task.getGrades();
        for (Student student : taskGrades.keySet()) {
            students.add(new SerializedStudent(student));
            grades.add(taskGrades.get(student));
        }
    }

    /**
     * Constructs an empty SerializedTask object.
     */
    public SerializedTask() {}

    @JsonProperty("taskName")
    public String getTaskName() {
        return taskName;
    }

    @JsonProperty("students")
    public List<SerializedStudent> getKeys() {
        return this.students;
    }

    @JsonProperty("grades")
    public List<Integer> getValues() {
        return this.grades;
    }

    /**
     * Converts current SerializedTask object into a Task object and returns
     * it.
     * @return A Task object that corresponds to this SerializedTask object.
     */
    public Task toModelType() {
        Task newTask = new Task(this.taskName);

        Map<Student, Integer> newGrades = newTask.getGrades();
        for (int i = 0; i < students.size(); i++) {
            newGrades.put(
                    students.get(i).toModelType(),
                    grades.get(i)
            );
        }
        return newTask;
    }
}
