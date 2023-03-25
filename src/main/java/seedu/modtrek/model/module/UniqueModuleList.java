package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.modtrek.model.module.exceptions.DuplicateModuleException;
import seedu.modtrek.model.module.exceptions.ModuleNotFoundException;

/**
 * UniqueModuleList is a list of modules where each modules must be unique.
 */
public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Checks if the module is in this list.
     *
     * @param toCheck
     * @return boolean if module is in list
     */
    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModule);
    }

    /**
     * Adds module into the list. Module must not be null and must be unique.
     *
     * @param toAdd
     */
    public void add(Module toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Changes the module into the editedModule.
     *
     * @param target
     * @param editedModule
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleNotFoundException();
        }

        if (!target.isSameModule(editedModule) && contains(editedModule)) {
            throw new DuplicateModuleException();
        }

        internalList.set(index, editedModule);
    }

    /**
     * Removes the module from the list.
     *
     * @param toRemove
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    /**
     * Sets modules based on the replacement list.
     *
     * @param replacement
     */
    // For resetting
    public void setModules(UniqueModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Sets modules based on the list of modules.
     *
     * @param modules
     */
    // For resetting
    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!modulesAreUnique(modules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(modules);
    }

    /**
     * Returns an unmodifiable list.
     *
     * @return
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns TreeMap of Modules sorted by SemYear
     *
     * @return
     */
    public TreeMap<SemYear, ObservableList<Module>> sortBySemYear() {
        TreeMap<SemYear, ObservableList<Module>> result = new TreeMap<>();
        for (Module m : internalUnmodifiableList) {
            SemYear currSemYear = m.getSemYear();
            ObservableList<Module> existingSemYearList = result.get(currSemYear);
            if (existingSemYearList == null) {
                ObservableList<Module> currSemYearList = FXCollections.observableArrayList();
                currSemYearList.add(m);
                result.put(currSemYear, currSemYearList);
            } else {
                existingSemYearList.add(m);
            }
        }
        return result;
    }

    /**
     * Returns TreeMap of Modules sorted by Credit
     *
     * @return
     */
    public TreeMap<Credit, ObservableList<Module>> sortByCredit() {
        TreeMap<Credit, ObservableList<Module>> result = new TreeMap<>();
        for (Module m : internalUnmodifiableList) {
            Credit currCredit = m.getCredit();
            ObservableList<Module> existingCreditList = result.get(currCredit);
            if (existingCreditList == null) {
                ObservableList<Module> currCreditList = FXCollections.observableArrayList();
                currCreditList.add(m);
                result.put(currCredit, currCreditList);
            } else {
                existingCreditList.add(m);
            }
        }
        return result;
    }

    /**
     * Returns TreeMap of Modules sorted by Grade
     *
     * @return
     */
    public TreeMap<Grade, ObservableList<Module>> sortByGrade() {
        TreeMap<Grade, ObservableList<Module>> result = new TreeMap<>();
        for (Module m : internalUnmodifiableList) {
            Grade currGrade = m.getGrade();
            ObservableList<Module> existingGradeList = result.get(currGrade);
            if (existingGradeList == null) {
                ObservableList<Module> currGradeList = FXCollections.observableArrayList();
                currGradeList.add(m);
                result.put(currGrade, currGradeList);
            } else {
                existingGradeList.add(m);
            }
        }
        return result;
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleList // instanceof handles nulls
                        && internalList.equals(((UniqueModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    private boolean modulesAreUnique(List<Module> modules) {
        for (int i = 0; i < modules.size() - 1; i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                if (modules.get(i).isSameModule(modules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
