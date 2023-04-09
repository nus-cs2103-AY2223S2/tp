package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.modtrek.logic.commands.SortCommand.DEFAULT_SORT;
import static seedu.modtrek.logic.parser.ParserUtil.parseTagsForSort;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.modtrek.logic.commands.SortCommand;
import seedu.modtrek.model.module.exceptions.DuplicateModuleException;
import seedu.modtrek.model.module.exceptions.ModuleNotFoundException;
import seedu.modtrek.model.tag.Tag;

/**
 * UniqueModuleList is a list of modules where each modules must be unique.
 */
public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private TreeMap<Object, ObservableList<Module>> moduleGroups = new TreeMap<>();
    private SortCommand.Sort sort = DEFAULT_SORT;


    /**
     * Checks if the module is in this list.
     *
     * @param toCheck the to check
     * @return boolean if module is in list
     */
    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModule);
    }

    /**
     * Adds module into the list. Module must not be null and must be unique.
     *
     * @param toAdd the to add
     */
    public void add(Module toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(toAdd);
        sortByObject(sort);
    }

    /**
     * Changes the module into the editedModule.
     *
     * @param target       the target
     * @param editedModule the edited module
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
        sortByObject(sort);
    }

    /**
     * Removes the module from the list.
     *
     * @param toRemove the to remove
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
        sortByObject(sort);
    }

    /**
     * Sets modules based on the replacement list.
     *
     * @param replacement the replacement
     */
    // For resetting
    public void setModules(UniqueModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        sort = SortCommand.Sort.YEAR;
        sortByObject(sort);
    }

    /**
     * Sets modules based on the list of modules.
     *
     * @param modules the modules
     */
    // For resetting
    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!modulesAreUnique(modules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(modules);
        sort = SortCommand.Sort.YEAR;
        sortByObject(sort);
    }

    /**
     * Returns an unmodifiable list.
     *
     * @return observable list
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns a sorted TreeMap of module groups
     * @param sort
     * @return sorted Map
     */
    public TreeMap<Object, ObservableList<Module>> sortByObject(SortCommand.Sort sort) {
        TreeMap<Object, ObservableList<Module>> result = new TreeMap<>();
        Comparator<Module> comparator = Comparator.comparing(Module::toString);
        this.sort = sort;
        if (sort == SortCommand.Sort.TAG) {
            return sortByTag();
        }
        for (Module m : internalList) {
            Object obj;
            switch (sort) {
            case GRADE:
                obj = m.getGrade();
                break;
            case CREDITS:
                obj = m.getCredit().toString();
                break;
            case TAG:
                obj = parseTagsForSort(m.getTags());
                break;
            case CODE:
                obj = m.getCodePrefix().toString();
                break;
            default:
                obj = m.getSemYear();
            }
            ObservableList<Module> existingList = result.get(obj);
            if (existingList == null) {
                ObservableList<Module> newList = FXCollections.observableArrayList();
                newList.add(m);
                result.put(obj, newList);
            } else {
                existingList.add(m);
                FXCollections.sort(existingList, comparator);
            }
        }
        moduleGroups = result;
        return result;
    }

    /**
     * Returns a sorted TreeMap of modules grouped by Tag
     * @return sorted Map
     */
    private TreeMap<Object, ObservableList<Module>> sortByTag() {
        TreeMap<Object, ObservableList<Module>> result = new TreeMap<>();
        Comparator<Module> comparator = Comparator.comparing(Module::toString);
        Set<Tags> OverallTags = Tags.getAllShortFormTags();

        for (Tags t : OverallTags) {
            ObservableList<Module> newList = FXCollections.observableArrayList();
            internalList.stream().filter(x -> parseTagsForSort(x.getTags()).contains(t)).forEach(y -> newList.add(y));
            if (!newList.isEmpty()) {
                FXCollections.sort(newList, comparator);
                result.put(t, newList);
            }
        }
        moduleGroups = result;
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

    /**
     * Gets module groups.
     *
     * @return the module groups
     */
    public TreeMap<Object, ObservableList<Module>> getModuleGroups() {
        return moduleGroups;
    }

    public String getSort() {
        return sort.name();
    }
}
