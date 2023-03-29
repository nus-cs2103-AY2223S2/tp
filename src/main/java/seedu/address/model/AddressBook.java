package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Event;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Note;
import seedu.address.model.event.NoteList;
import seedu.address.model.event.Tutorial;
import seedu.address.model.event.UniqueConsultationList;
import seedu.address.model.event.UniqueLabList;
import seedu.address.model.event.UniqueTutorialList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTutorialList tutorials;
    private final UniqueLabList labs;
    private final UniqueConsultationList consultations;
    private final NoteList notes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tutorials = new UniqueTutorialList();
        labs = new UniqueLabList();
        consultations = new UniqueConsultationList();
        notes = new NoteList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setTutorials(List<Tutorial> tutorials) {
        this.tutorials.setTutorials(tutorials);
    }

    public void setLabs(List<Lab> labs) {
        this.labs.setLabs(labs);
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations.setConsultations(consultations);
    }

    /**
     * Adds a student to a specific tutorial.
     *
     * @param toAdd the student to be added.
     * @param name the name of the tutorial that the student will be added into.
     */
    public void addStudentToTutorial(Person toAdd, String name) {
        Tutorial original = tutorials.get(0);
        for (int i = 0; i < tutorials.size(); i++) {
            if (tutorials.get(i).hasMatchByName(name)) {
                original = tutorials.get(i);
                break;
            }
        }
        original.addStudent(toAdd);
    }

    /**
     * Deletes a student from an event.
     *
     * @param toDel the index of the student within the event's student list to be deleted.
     * @param name the name of the event from which the student will be deleted.
     * @param type the type of the event from which the student will be deleted.
     */
    public void deleteStudentFromEvent(Index toDel, String name, String type) {
        Optional<Event> target = Optional.empty();
        if (type.equals(PREFIX_TUTORIAL.getPrefix())) {
            for (int i = 0; i < tutorials.size(); i++) {
                if (tutorials.get(i).hasMatchByName(name)) {
                    target = Optional.of(tutorials.get(i));
                    break;
                }
            }
        }

        if (type.equals(PREFIX_LAB.getPrefix())) {
            for (int i = 0; i < labs.size(); i++) {
                if (labs.get(i).hasMatchByName(name)) {
                    target = Optional.of(labs.get(i));
                    break;
                }
            }
        }

        if (type.equals(PREFIX_CONSULTATION.getPrefix())) {
            for (int i = 0; i < consultations.size(); i++) {
                if (consultations.get(i).hasMatchByName(name)) {
                    target = Optional.of(consultations.get(i));
                    break;
                }
            }
        }

        if (target.isEmpty()) {
            //todo: maybe add error message, but not really possible that the prefix is not present
            return;
        }

        target.get().removeIndexStudent(toDel.getZeroBased());
    }

    /**
     * Adds a student to a specific lab.
     *
     * @param toAdd the student to be added.
     * @param name the name of the lab that the student will be added into.
     */
    public void addStudentToLab(Person toAdd, String name) {
        Lab original = labs.get(0);
        for (int i = 0; i < labs.size(); i++) {
            if (labs.get(i).hasMatchByName(name)) {
                original = labs.get(i);
                break;
            }
        }
        Lab added = original;
        added.addStudent(toAdd);
        labs.setLab(original, added);
    }

    /**
     * Adds a student to a specific consultation
     *
     * @param toAdd the student to be added.
     * @param name the name of the consultation that the student will be added into.
     */
    public void addStudentToConsultation(Person toAdd, String name) {
        Consultation original = consultations.get(0);
        for (int i = 0; i < consultations.size(); i++) {
            if (consultations.get(i).hasMatchByName(name)) {
                original = consultations.get(i);
                break;
            }
        }
        Consultation added = original;
        added.addStudent(toAdd);
        consultations.setConsultation(original, added);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setTutorials(newData.getTutorialList());
        setLabs(newData.getLabList());
        setConsultations(newData.getConsultationList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// tutorial-level operations

    /**
     * Returns true if a tutorial with the same identity as {@code tutorial} exists in the address book.
     */
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return tutorials.contains(tutorial);
    }

    /**
     * Adds a tutorial to the address book.
     * The tutorial must not already exist in the address book.
     */
    public void addTutorial(Tutorial p) {
        tutorials.add(p);
    }

    /**
     * Replaces the given tutorial {@code target} in the list with {@code editedTutorial}.
     * {@code target} must exist in the address book.
     * The tutorial identity of {@code editedTutorial} must not be the same as another existing
     * tutorial in the address book.
     */
    public void setTutorial(Tutorial target, Tutorial editedTutorial) {
        requireNonNull(editedTutorial);

        tutorials.setTutorial(target, editedTutorial);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTutorial(Tutorial key) {
        tutorials.remove(key);
    }

    //// lab-level operations

    /**
     * Returns true if a lab with the same identity as {@code lab} exists in the address book.
     */
    public boolean hasLab(Lab lab) {
        requireNonNull(lab);
        return labs.contains(lab);
    }

    /**
     * Adds a lab to the address book.
     * The lab must not already exist in the address book.
     */
    public void addLab(Lab p) {
        labs.add(p);
    }

    /**
     * Replaces the given lab {@code target} in the list with {@code editedLab}.
     * {@code target} must exist in the address book.
     * The lab identity of {@code editedLab} must not be the same as another existing
     * lab in the address book.
     */
    public void setLab(Lab target, Lab editedLab) {
        requireNonNull(editedLab);

        labs.setLab(target, editedLab);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeLab(Lab key) {
        labs.remove(key);
    }

    //// consultation-level operations

    /**
     * Returns true if a consultation with the same identity as {@code consultation} exists in the address book.
     */
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return consultations.contains(consultation);
    }

    /**
     * Adds a consultation to the address book.
     * The consultation must not already exist in the address book.
     */
    public void addConsultation(Consultation p) {
        consultations.add(p);
    }

    /**
     * Replaces the given consultation {@code target} in the list with {@code editedConsultation}.
     * {@code target} must exist in the address book.
     * The consultation identity of {@code editedConsultation} must not be the same as another existing
     * consultation in the address book.
     */
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireNonNull(editedConsultation);

        consultations.setConsultation(target, editedConsultation);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeConsultation(Consultation key) {
        consultations.remove(key);
    }

    /**
     * Returns true if a note with the same identity as {@code note} exists in the address book.
     */
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return notes.contains(note);
    }

    /**
     * Adds note to address book note list
     * @param note The note to add.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons "
                + tutorials.asUnmodifiableObservableList().size() + " tutorials "
                + labs.asUnmodifiableObservableList().size() + " labs "
                + consultations.asUnmodifiableObservableList().size() + " consultations ";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tutorial> getTutorialList() {
        return tutorials.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Lab> getLabList() {
        return labs.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Consultation> getConsultationList() {
        return consultations.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && tutorials.equals(((AddressBook) other).tutorials)
                && labs.equals(((AddressBook) other).labs)
                && consultations.equals(((AddressBook) other).consultations));
    }

    @Override
    public int hashCode() {
        return persons.hashCode()
                + tutorials.hashCode()
                + labs.hashCode()
                + consultations.hashCode();
    }
}
