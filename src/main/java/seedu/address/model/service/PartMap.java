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
     * Checks if part is in the map
     *
     * @param partName Part Name
     */
    public boolean contains(String partName) {
        return map.containsKey(partName);
    }

    /**
     * Gets the quantity of a specified part
     *
     * @param partName Part name
     * @return Quantity of part
     */
    public int getPartQuantity(String partName) {
        if (!contains(partName)) {
            return 0;
        }
        return map.get(partName);
    }

    /**
     * Gets the entrySet of the map
     */
    public Set<Map.Entry<String, Integer>> getEntrySet() {
        return map.entrySet();
    }

    /**
     * Adds part into mapping.
     *
     * @param partName Part name
     * @param quantity Quantity of part to add
     */
    public void addPart(String partName, int quantity) {
        if (this.map.containsKey(partName)) {
            this.map.put(partName, map.get(partName) + quantity);
        }
        this.map.put(partName, quantity);
    }

    /**
     * Add all content of other map into PartMap
     *
     * @param other Another partMap with values
     */
    public void addAll(PartMap other) {
        this.map.putAll(other.map);
    }

    /**
     * Removes the part from the PartMap
     *
     * @param partName Part name
     */
    public void removePart(String partName) {
        this.map.remove(partName);
    }

    /**
     * Removes all indicated parts from this PartMap
     *
     * @param other Another partMap with values
     */
    public void removeParts(PartMap other) {
        Set<String> keysToRemove = other.map.keySet();
        this.map.keySet().removeAll(keysToRemove);
    }

    /**
     * Removes all parts in PartMap
     */
    public void removeAll() {
        this.map.clear();
    }

    /**
     * Replaces contents of partMap with another map
     *
     * @param other Map to replace with
     */
    public void replace(PartMap other) {
        removeAll();
        addAll(other);
    }

    /**
     * Increases the quantity of a part in the PartMap by a specified quantity
     *
     * @param partName Part name to increase quantity of
     * @param quantity Amount to increase
     * @throws PartNotFoundException If part not in Set
     */
    public void increasePartQuantity(String partName, int quantity) throws PartNotFoundException {
        if (!this.map.containsKey(partName)) {
            throw new PartNotFoundException(partName);
        }
        this.map.put(partName, map.get(partName) + quantity);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }

        if (result.length() > 0) {
            // Remove the trailing comma and space
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }
}
