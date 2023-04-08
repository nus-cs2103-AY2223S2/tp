package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.exceptions.ConsultationNotFoundException;
import seedu.address.model.event.exceptions.DuplicateConsultationException;

/**
 * Lists the consultation that enforces uniqueness between its elements and does not allow nulls.
 * A consultation is considered unique by comparing using {@code Consultation#isSameConsultation(Consultation)}.
 * As such, adding and updating of consultation uses Consultation#isSameConsultation(Consultation) for equality to
 * ensure that the consultation being added or updated is unique in terms of identity in the UniqueConsultationList.
 * However, the removal of a consultation uses Consultation#equals(Object) to ensure that the consultation with
 * exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Consultation#isSameConsultation(Consultation)
 */
public class UniqueConsultationList implements Iterable<Consultation> {

    private final ObservableList<Consultation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Consultation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent consultation as the given argument.
     *
     * @param toCheck the consultation to check.
     * @return        if the list contains the consultation.
     */
    public boolean contains(Consultation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameConsultation);
    }

    /**
     * Checks whether an event is contained in the list universally.
     *
     * @param nameOfEvent Name of event.
     * @return A boolean indicator.
     */
    public boolean containsEventName(String nameOfEvent) {
        requireNonNull(nameOfEvent);
        return internalList.stream().anyMatch(x -> x.hasMatchByName(nameOfEvent));
    }

    /**
     * Checks whether a note is contained universally.
     *
     * @param note Note to check upon.
     * @return A boolean indicator.
     */
    public boolean containsNote(Note note) {
        requireNonNull(note);
        Optional<NoteList> mergedList = internalList.stream().map(Event::getNoteList).reduce(NoteList::merge);
        return mergedList.map(noteList -> noteList.getNotes().stream().anyMatch(note::equals)).orElse(false);
    }

    // todo: probably try remove get and size methods to preserve abstraction barrier
    public Consultation get(int index) {
        return this.internalList.get(index);
    }

    public int size() {
        return this.internalList.size();
    }

    /**
     * Adds a consultation to the list.
     * The consultation must not already exist in the list.
     *
     * @param toAdd the consultation to be added.
     */
    public void add(Consultation toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateConsultationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the consultation {@code target} in the list with {@code editedConsultation}.
     * {@code target} must exist in the list.
     * The consultation identity of {@code editedConsultation} must not be the same
     * as another existing consultation in the list.
     *
     * @param target                the target consultation.
     * @param editedConsultation    the edited consultation.
     */
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireAllNonNull(target, editedConsultation);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ConsultationNotFoundException();
        }

        if (!target.isSameConsultation(editedConsultation) && contains(editedConsultation)) {
            throw new DuplicateConsultationException();
        }

        internalList.set(index, editedConsultation);
    }

    /**
     * Removes the equivalent consultation from the list.
     * The consultation must exist in the list.
     *
     * @param toRemove the consultation to remove.
     */
    public void remove(Consultation toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ConsultationNotFoundException();
        }
    }

    /**
     * Replaces the consultation with a new consultation.
     *
     * @param replacement the consultation to replace the old consultation.
     */
    public void setConsultations(UniqueConsultationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code consultations}.
     * {@code consultations} must not contain duplicate consultations.
     *
     * @param consultations the list of consultations to replace the old consultations list.
     */
    public void setConsultations(List<Consultation> consultations) {
        requireAllNonNull(consultations);
        if (!consultationsAreUnique(consultations)) {
            throw new DuplicateConsultationException();
        }

        internalList.setAll(consultations);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Consultation> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Consultation> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueConsultationList // instanceof handles nulls
                && internalList.equals(((UniqueConsultationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code consultations} contains only unique consultations.
     *
     * @param consultations the consultations to be checked for uniqueness.
     * @return              the boolean result if any one of the consultations are not unique.
     */
    private boolean consultationsAreUnique(List<Consultation> consultations) {
        for (int i = 0; i < consultations.size() - 1; i++) {
            for (int j = i + 1; j < consultations.size(); j++) {
                if (consultations.get(i).isSameConsultation(consultations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
