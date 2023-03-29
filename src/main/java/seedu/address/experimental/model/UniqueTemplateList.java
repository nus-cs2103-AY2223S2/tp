package seedu.address.experimental.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.entity.Template;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A more skeletal version of UniqueEntityList, to store Templates.
 */
public class UniqueTemplateList implements Iterable<Template> {

    private final ObservableList<Template> internalList = FXCollections.observableArrayList();
    private final ObservableList<Template> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent template as the given argument.
     */
    public boolean contains(Template toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTemplate);
    }

    /**
     * Adds a template to the list.
     * The template must not already exist in the list.
     */
    public void add(Template toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the contents of this list with {@code entities}.
     * {@code entities} must not contain duplicate entities.
     */
    public void setTemplates(List<Template> entities) {
        requireAllNonNull(entities);
        if (!templatesAreUnique(entities)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(entities);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Template> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Template> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTemplateList // instanceof handles nulls
                && internalList.equals(((UniqueTemplateList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code entities} contains only unique entities.
     */
    private boolean templatesAreUnique(List<? extends Template> entities) {
        for (int i = 0; i < entities.size() - 1; i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                if (entities.get(i).isSameTemplate(entities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
