package seedu.modtrek.model;

import java.util.TreeMap;

import javafx.collections.ObservableList;
import seedu.modtrek.model.degreedata.DegreeProgressionData;
import seedu.modtrek.model.module.Module;

/**
 * Unmodifiable view of a degree progression
 */
public interface ReadOnlyDegreeProgression {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Module> getModuleList();

    /**
     * Returns a DegreeProgressionData with the most updated details of the degree progression.
     *
     * @return the degree progression data
     */
    DegreeProgressionData getProgressionData();

    TreeMap<Object, ObservableList<Module>> getModuleGroups();

    String getSort();
}
