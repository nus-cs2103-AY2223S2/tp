package seedu.patientist.model.person.patient;

/**
 * Represents the status details of a patient. This is where the treatment progress and notes are to be recorded.
 */
public class PatientToDo {
    private String todo;

    public PatientToDo() {
        this.todo = "";
    }

    public PatientToDo(String details) {
        this.todo = details;
    }

    public String getTodo() {
        return todo;
    }

    @Override
    public String toString() {
        return todo;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientToDo // instanceof handles nulls
                && todo.equals(((PatientToDo) other).todo)); // state check
    }
}
