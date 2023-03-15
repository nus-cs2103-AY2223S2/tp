package seedu.address.logic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Unique ID generation
 */
public class IdGenerator {
    private static final SortedSet<Integer> usedCustomerIds = new TreeSet<>();
    private static final Queue<Integer> unusedCustomerIds = new LinkedList<>();
    private static final SortedSet<Integer> usedVehicleIds = new TreeSet<>();
    private static final Queue<Integer> unusedVehicleIds = new LinkedList<>();
    private static final SortedSet<Integer> usedServiceIds = new TreeSet<>();
    private static final Queue<Integer> unusedServiceIds = new LinkedList<>();

    private IdGenerator() {
    }

    private static int generateId(SortedSet<Integer> used, Queue<Integer> unused) {
        int id;
        if (used.isEmpty()) {
            id = 1;
        } else if (unused.isEmpty()) {
            id = used.last() + 1;
        } else {
            id = unused.poll();
        }
        return id;
    }

    public static int generateCustomerId() {
        return generateId(usedCustomerIds, unusedCustomerIds);
    }

    public static int generateVehicleId() {
        return generateId(usedVehicleIds, unusedVehicleIds);
    }

    public static int generateServiceId() {
        return generateId(usedServiceIds, unusedServiceIds);
    }

    public static void setCustomerIdUnused(int id) {
        unusedCustomerIds.add(id);
    }

    public static void setVehicleIdUnused(int id) {
        unusedVehicleIds.add(id);
    }

    public static void setServiceIdUnused(int id) {
        unusedServiceIds.add(id);
    }
}
