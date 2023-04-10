package seedu.patientist.model.person.patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.tag.PriorityTag;
import seedu.patientist.model.tag.RoleTag;
import seedu.patientist.model.tag.Tag;

/**
 * Represents a patient object in Patientist
 * Guarantees: superclass guarantees, and details is non null. If none provided, details is blank.
 */
public class Patient extends Person {
    public static final RoleTag PATIENT_TAG = new RoleTag("Patient");
    private PriorityTag priority = new PriorityTag("LOW");
    private List<PatientStatusDetails> details = new ArrayList<>();
    private List<PatientToDo> toDos = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Patient(Email email, Name name, Phone phone, IdNumber id, Address address, Set<Tag> tags) {
        super(name, phone, email, id, address, tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Patient(IdNumber id, Name name, Phone phone, Email email,
                   Address address, PriorityTag priority, List<PatientStatusDetails> details,
                   List<PatientToDo> toDos, Set<Tag> tags) {
        super(name, phone, email, id, address, tags);
        this.priority = priority;
        this.details.addAll(details);
        this.toDos.addAll(toDos);
    }

    /**
     * Every field must be present and not null.
     */
    public Patient(IdNumber id, Name name, Phone phone, Email email,
                   Address address, List<PatientStatusDetails> details, List<PatientToDo> toDos, Set<Tag> tags) {
        super(name, phone, email, id, address, tags);
        this.details.addAll(details);
        this.toDos.addAll(toDos);
    }

    /**
     * Adds the new <code>PatientStatusDetails</code> into the <code>details</code> field.
     */
    public void addPatientStatusDetails(PatientStatusDetails details) {
        this.details.add(details);
    }

    /**
     * Deletes the <code>PatientStatusDetails</code> specified by the index into the <code>details</code> field.
     */
    public void deletePatientStatusDetails(Index index) throws CommandException {
        if (!statusDetailsIndexInRange(index)) {
            throw new CommandException("Status index not in range.");
        }
        this.details.remove(index.getZeroBased());
    }

    /**
     * Returns the <code>PatientStatusDetails</code> of this patient
     * @return <code>details</code>, the object representing the details of a patient's treatment
     */
    public List<PatientStatusDetails> getPatientStatusDetails() {
        return Collections.unmodifiableList(this.details);
    }

    private boolean statusDetailsIndexInRange(Index index) {
        return (index.getZeroBased() < details.size() && index.getZeroBased() >= 0);
    }

    /**
     * Adds the new <code>PatientToDo</code> into the <code>toDos</code> field
     */
    public void addPatientToDo(PatientToDo toDo) {
        this.toDos.add(toDo);
    }

    /**
     * Deletes the <code>PatientToDo</code> specified by the index into the <code>ToDos</code> field.
     */
    public void deletePatientToDo(Index index) throws CommandException {
        if (!toDoIndexInRange(index)) {
            throw new CommandException("Todo index not in range.");
        }
        this.toDos.remove(index.getZeroBased());
    }

    /**
     * Returns the <code>PatientToDos</code> of this patient
     * @return <code>toDos</code>, the object representing the details of a patient's todo list
     */
    public List<PatientToDo> getPatientToDoList() {
        return Collections.unmodifiableList(this.toDos);
    }

    public PriorityTag getPriority() {
        return priority;
    }

    private boolean toDoIndexInRange(Index index) {
        return (index.getZeroBased() < toDos.size() && index.getZeroBased() >= 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("; Status: ")
                .append(details.toString())
                .append("; Todos: ")
                .append(toDos.toString())
                .append("; Type: Patient ");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return getIdNumber().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPat = (Patient) other;
        return super.equals(otherPat);
    }

    @Override
    public RoleTag getRoleTag() {
        return PATIENT_TAG;
    }

    @Override
    public boolean isSamePerson(Person otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        if (!(otherPatient instanceof Patient)) {
            return false;
        }

        return super.isSamePerson(otherPatient);
    }

}
