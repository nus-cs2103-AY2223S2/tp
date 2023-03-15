package seedu.address.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import seedu.address.model.service.exception.PartNotFoundException;

/**
 * Mapping between part name and the part in storage
 */
public class PartMap {
    private final Map<String, Integer> map;

    public PartMap() {
        this.map = new HashMap<>();
    }

    /**
     * Checks if part is in the list
     *
     * @param partName Part Name
     */
    public boolean contains(String partName) {
        return map.containsKey(partName);
    }

    /**
     * Get part with suitable description
     *
     * @param partName Part name
     * @return Quantity of part
     * @throws PartNotFoundException If part not in Set
     */
    public int getPartQuantity(String partName) {
        if (!contains(partName)) {
            return 0;
        }
        return map.get(partName);
    }

    public Set<Map.Entry<String, Integer>> getEntrySet() {
        return map.entrySet();
    }

    /**
     * Adds parts into mapping.
     *
     * @param partName Part name
     * @param quantity Quantity of part to add
     */
    public void addParts(String partName, int quantity) {
        if (this.map.containsKey(partName)) {
            this.map.put(partName, map.get(partName) + quantity);
        }
        this.map.put(partName, quantity);
    }

    /**
     * Replaces contents of partMap with another map
     *
     * @param other Map to replace with
     */
    public void replace(Map<String, Integer> other) {
        this.map.clear();
        this.map.putAll(other);
    }
}
