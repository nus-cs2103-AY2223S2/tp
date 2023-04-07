package seedu.address.logic.idgen;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

import seedu.address.model.DeepCopy;

/**
 * Unique ID generation
 */
public class IdGenerator implements DeepCopy<IdGenerator> {
    private final SortedSet<Integer> usedCustomerIds = new TreeSet<>();
    private final Queue<Integer> unusedCustomerIds = new PriorityQueue<>();
    private final SortedSet<Integer> usedVehicleIds = new TreeSet<>();
    private final Queue<Integer> unusedVehicleIds = new PriorityQueue<>();
    private final SortedSet<Integer> usedServiceIds = new TreeSet<>();
    private final Queue<Integer> unusedServiceIds = new PriorityQueue<>();
    private final SortedSet<Integer> usedAppointmentIds = new TreeSet<>();
    private final Queue<Integer> unusedAppointmentIds = new PriorityQueue<>();
    private final SortedSet<Integer> usedStaffIds = new TreeSet<>();
    private final Queue<Integer> unusedStaffIds = new PriorityQueue<>();

    public IdGenerator() {
    }

    public IdGenerator(IdGenerator other) {
        this.resetData(other);
    }

    private int generateId(SortedSet<Integer> used, Queue<Integer> unused) {
        int id;
        if (used.isEmpty()) {
            id = 1;
        } else if (unused.isEmpty()) {
            id = used.last() + 1;
        } else {
            id = unused.poll();
        }
        used.add(id);
        return id;
    }

    public int generateCustomerId() {
        return generateId(usedCustomerIds, unusedCustomerIds);
    }

    public int generateVehicleId() {
        return generateId(usedVehicleIds, unusedVehicleIds);
    }

    public int generateServiceId() {
        return generateId(usedServiceIds, unusedServiceIds);
    }

    public int generateAppointmentId() {
        return generateId(usedAppointmentIds, unusedAppointmentIds);
    }

    public int generateStaffId() {
        return generateId(usedStaffIds, unusedStaffIds);
    }

    public void setCustomerIdUnused(int id) {
        unusedCustomerIds.add(id);
    }

    public void setVehicleIdUnused(int id) {
        unusedVehicleIds.add(id);
    }

    public void setServiceIdUnused(int id) {
        unusedServiceIds.add(id);
    }

    public void setAppointmentIdUnused(int id) {
        unusedAppointmentIds.add(id);
    }

    public void setStaffIdUnused(int id) {
        unusedStaffIds.add(id);
    }

    public void setCustomerIdUsed(int id) {
        usedCustomerIds.add(id);
    }

    public void setVehicleIdUsed(int id) {
        usedVehicleIds.add(id);
    }

    public void setServiceIdUsed(int id) {
        usedServiceIds.add(id);
    }

    public void setAppointmentIdUsed(int id) {
        usedAppointmentIds.add(id);
    }

    public void setStaffIdUsed(int id) {
        usedStaffIds.add(id);
    }

    /**
     * Resets the ID generator to default state
     */
    public void reset() {
        usedCustomerIds.clear();
        unusedCustomerIds.clear();
        usedVehicleIds.clear();
        unusedVehicleIds.clear();
        usedServiceIds.clear();
        unusedServiceIds.clear();
        usedStaffIds.clear();
        unusedStaffIds.clear();
    }

    /**
     * Resets the ID generator to the state of the other ID generator
     *
     * @param other the other ID generator
     */
    public void resetData(IdGenerator other) {
        reset();
        usedCustomerIds.addAll(other.usedCustomerIds);
        unusedCustomerIds.addAll(other.unusedCustomerIds);
        usedVehicleIds.addAll(other.usedVehicleIds);
        unusedVehicleIds.addAll(other.unusedVehicleIds);
        usedServiceIds.addAll(other.usedServiceIds);
        unusedServiceIds.addAll(other.unusedServiceIds);
        usedAppointmentIds.addAll(other.usedAppointmentIds);
        unusedAppointmentIds.addAll(other.unusedAppointmentIds);
        usedStaffIds.addAll(other.usedStaffIds);
        unusedStaffIds.addAll(other.unusedStaffIds);
    }

    @Override
    public IdGenerator copy() {
        return new IdGenerator(this);
    }
}
