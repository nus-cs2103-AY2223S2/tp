package seedu.address.logic.idgen;

import java.io.Serializable;
import java.util.Queue;
import java.util.SortedSet;

/**
 * Serializable IdGenerator state
 */
public class IdGeneratorState implements Serializable {
    private final SortedSet<Integer> usedCustomerIds;
    private final Queue<Integer> unusedCustomerIds;
    private final SortedSet<Integer> usedVehicleIds;
    private final Queue<Integer> unusedVehicleIds;
    private final SortedSet<Integer> usedServiceIds;
    private final Queue<Integer> unusedServiceIds;

    IdGeneratorState(SortedSet<Integer> usedCustomerIds, Queue<Integer> unusedCustomerIds,
                     SortedSet<Integer> usedVehicleIds, Queue<Integer> unusedVehicleIds,
                     SortedSet<Integer> usedServiceIds, Queue<Integer> unusedServiceIds) {
        this.usedCustomerIds = usedCustomerIds;
        this.unusedCustomerIds = unusedCustomerIds;
        this.usedVehicleIds = usedVehicleIds;
        this.unusedVehicleIds = unusedVehicleIds;
        this.usedServiceIds = usedServiceIds;
        this.unusedServiceIds = unusedServiceIds;
    }

    SortedSet<Integer> getUsedCustomerIds() {
        return usedCustomerIds;
    }

    Queue<Integer> getUnusedCustomerIds() {
        return unusedCustomerIds;
    }

    SortedSet<Integer> getUsedVehicleIds() {
        return usedVehicleIds;
    }

    Queue<Integer> getUnusedVehicleIds() {
        return unusedVehicleIds;
    }

    SortedSet<Integer> getUsedServiceIds() {
        return usedServiceIds;
    }

    Queue<Integer> getUnusedServiceIds() {
        return unusedServiceIds;
    }

}
