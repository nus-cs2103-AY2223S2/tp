package mycelium.mycelium.testutil;

import java.util.HashMap;
import java.util.Map;

import mycelium.mycelium.model.ModelManager;

/**
 * A utility class for Project model.
 */
public class ProjectUtil {
    /**
     * Check if all values in the hashmap returned by {#getProjectStatistics} is positive
     * @param modelManager the model manager to check
     * @return boolean true of all values are positive
     *                  false otherwise
     */
    public static boolean containsPositiveValues(ModelManager modelManager) {
        HashMap<String, Long> statistics = modelManager.getProjectStatistics();

        for (Map.Entry<String, Long> set: statistics.entrySet()) {
            if (set.getValue() < 0) {
                return false;
            }
        }

        return true;
    }
}
