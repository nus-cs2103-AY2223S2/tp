package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Event;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Note;
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
     * @param index the index of the tutorial that the student will be added into.
     */
    public void addStudentToTutorial(Person toAdd, Index index) throws CommandException {
        Tutorial original = tutorials.get(index.getZeroBased());
        if (original.hasStudent(toAdd)) {
            throw new CommandException("Student already in event!");
        }
        original.addStudent(toAdd);
    }

    /**
     * Deletes a student from an event.
     *
     * @param toDel the index of the student within the event's student list to be deleted.
     * @param eventIndex the index of the event from which the student will be deleted.
     * @param type the type of the event from which the student will be deleted.
     */
    public void deleteStudentFromEvent(Index toDel, Index eventIndex, String type) throws CommandException {
        Optional<Event> target = Optional.empty();
        try {
            if (type.equals(PREFIX_TUTORIAL.getPrefix())) {
                target = Optional.of(tutorials.get(eventIndex.getZeroBased()));
            }

            if (type.equals(PREFIX_LAB.getPrefix())) {
                target = Optional.of(labs.get(eventIndex.getZeroBased()));
            }

            if (type.equals(PREFIX_CONSULTATION.getPrefix())) {
                target = Optional.of(consultations.get(eventIndex.getZeroBased()));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Event index is out of bounds!");
        }

        try {
            target.get().removeIndexStudent(toDel.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Student index is out of bounds!");
        }
    }

    /**
     * Adds a student to a specific lab.
     *
     * @param toAdd the student to be added.
     * @param index the index of the lab that the student will be added into.
     */
    public void addStudentToLab(Person toAdd, Index index) throws CommandException {
        Lab original = labs.get(index.getZeroBased());
        if (original.hasStudent(toAdd)) {
            throw new CommandException("Student already in event!");
        }
        original.addStudent(toAdd);
    }

    /**
     * Adds a student to a specific consultation
     *
     * @param toAdd the student to be added.
     * @param index the index of the consultation that the student will be added into.
     */
    public void addStudentToConsultation(Person toAdd, Index index) throws CommandException {
        Consultation original = consultations.get(index.getZeroBased());
        Consultation added = original;
        if (added.hasStudent(toAdd)) {
            throw new CommandException("Student already in event!");
        }
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
     * Adds note to address book tutorial note list
     * @param note The note to add.
     */
    public boolean addNoteToTutorial(Note note, String nameOfEvent) {
        if (!(tutorials.containsEventName(nameOfEvent))) {
            return false;
        }
        for (Tutorial tutorial : tutorials) {
            if (tutorial.hasMatchByName(nameOfEvent)) {
                tutorial.addNote(note);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds note to address book lab note list
     * @param note The note to add.
     */
    public boolean addNoteToLab(Note note, String nameOfEvent) {
        if (!(labs.containsEventName(nameOfEvent))) {
            return false;
        }
        for (Lab lab : labs) {
            if (lab.hasMatchByName(nameOfEvent)) {
                lab.addNote(note);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds note to address book consultation note list
     * @param note The note to add.
     */
    public boolean addNoteToConsultation(Note note, String nameOfEvent) {
        if (!(consultations.containsEventName(nameOfEvent))) {
            return false;
        }
        for (Consultation consultation : consultations) {
            if (consultation.hasMatchByName(nameOfEvent)) {
                consultation.addNote(note);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes note from specific tutorial
     * @param index Index of note to remove
     * @param nameOfEvent Event name
     */
    public boolean removeNoteFromTutorial(Index index, String nameOfEvent) {
        for (Tutorial tut : tutorials) {
            if (tut.hasMatchByName(nameOfEvent)) {
                try {
                    return tut.removeNote(index.getZeroBased());
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Removes note from specific lab
     * @param index Index of note to remove
     * @param nameOfEvent Event name
     */
    public boolean removeNoteFromLab(Index index, String nameOfEvent) {
        for (Lab lab : labs) {
            if (lab.hasMatchByName(nameOfEvent)) {
                try {
                    return lab.removeNote(index.getZeroBased());
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Removes note from specific consult
     * @param index Index of note to remove
     * @param nameOfEvent Event name
     */
    public boolean removeNoteFromConsultation(Index index, String nameOfEvent) {
        for (Consultation consultation : consultations) {
            if (consultation.hasMatchByName(nameOfEvent)) {
                try {
                    return consultation.removeNote(index.getZeroBased());
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Edits note from specific consult
     * @param index Index of note to edit
     * @param nameOfEvent Event name
     */
    public boolean editNoteFromConsultation(Index index, Note note, String nameOfEvent) {
        for (Consultation consultation : consultations) {
            if (consultation.hasMatchByName(nameOfEvent)) {
                return consultation.setNote(note, index);
            }

        }
        return false;
    }

    /**
     * Removes note from specific lab
     * @param index Index of note to edit
     * @param nameOfEvent Event name
     */
    public boolean editNoteFromLab(Index index, Note note, String nameOfEvent) {
        for (Lab lab : labs) {
            if (lab.hasMatchByName(nameOfEvent)) {
                return lab.setNote(note, index);
            }
        }
        return false;
    }

    /**
     * Removes note from specific tutorial
     * @param index Index of note to edit
     * @param nameOfEvent Event name
     */
    public boolean editNoteFromTutorial(Index index, Note note, String nameOfEvent) {
        for (Tutorial tutorial : tutorials) {
            if (tutorial.hasMatchByName(nameOfEvent)) {
                return tutorial.setNote(note, index);
            }
        }
        return false;
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
