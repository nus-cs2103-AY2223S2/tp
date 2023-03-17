package seedu.address.logic.idgen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Unique ID generation
 */
public class IdGenerator {
    private static final String FILE_NAME = "IdGenState.ser";
    private static final SortedSet<Integer> usedCustomerIds = new TreeSet<>();
    private static final Queue<Integer> unusedCustomerIds = new PriorityQueue<>();
    private static final SortedSet<Integer> usedVehicleIds = new TreeSet<>();
    private static final Queue<Integer> unusedVehicleIds = new PriorityQueue<>();
    private static final SortedSet<Integer> usedServiceIds = new TreeSet<>();
    private static final Queue<Integer> unusedServiceIds = new PriorityQueue<>();
    private static final SortedSet<Integer> usedAppointmentIds = new TreeSet<>();
    private static final Queue<Integer> unusedAppointmentIds = new PriorityQueue<>();
    private static final SortedSet<Integer> usedStaffIds = new TreeSet<>();
    private static final Queue<Integer> unusedStaffIds = new PriorityQueue<>();

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
        used.add(id);
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

    public static int generateAppointmentId() {
        return generateId(usedAppointmentIds, unusedAppointmentIds);
    }

    public static int generateStaffId() {
        return generateId(usedStaffIds, unusedStaffIds);
    }

    /**
     * Saves state of ID generator to specified path
     *
     * @param path Path of directories to save to
     * @throws IOException If error occurs while saving state
     */
    public static void saveState(Path path) throws IOException {
        IdGeneratorState state = new IdGeneratorState(
                usedCustomerIds, unusedCustomerIds,
                usedVehicleIds, unusedVehicleIds,
                usedServiceIds, unusedServiceIds,
                usedStaffIds, unusedStaffIds);
        Files.createDirectories(path);
        path = path.resolve(FILE_NAME);
        ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path));
        outputStream.writeObject(state);
        outputStream.close();
    }

    /**
     * Loads ID generator state from path and updates the ID generator
     *
     * @param path Path of directories to load from
     * @throws IOException            If IO error occurs while loading
     * @throws ClassNotFoundException If class not found
     * @throws ClassCastException     If loaded object is not IdGeneratorState
     */
    public static void loadState(Path path) throws IOException, ClassNotFoundException, ClassCastException {
        path = path.resolve(FILE_NAME);
        ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path));
        Object obj = inputStream.readObject();
        if (obj instanceof IdGeneratorState) {
            IdGeneratorState state = (IdGeneratorState) obj;
            reset();
            usedCustomerIds.addAll(state.getUsedCustomerIds());
            unusedCustomerIds.addAll(state.getUnusedCustomerIds());
            usedVehicleIds.addAll(state.getUsedVehicleIds());
            unusedVehicleIds.addAll(state.getUnusedVehicleIds());
            usedServiceIds.addAll(state.getUsedServiceIds());
            unusedServiceIds.addAll(state.getUnusedServiceIds());
            usedStaffIds.addAll(state.getUsedStaffIds());
            unusedStaffIds.addAll(state.getUnusedStaffIds());
        } else {
            throw new ClassCastException();
        }
        inputStream.close();
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

    public static void setAppointmentIdUnused(int id) {
        unusedAppointmentIds.add(id);
    }

    public static void setStaffIdUnused(int id) {
        unusedStaffIds.add(id);
    }

    /**
     * Resets the ID generator to default state
     */
    public static void reset() {
        usedCustomerIds.clear();
        unusedCustomerIds.clear();
        usedVehicleIds.clear();
        unusedVehicleIds.clear();
        usedServiceIds.clear();
        unusedServiceIds.clear();
        usedStaffIds.clear();
        unusedStaffIds.clear();
    }
}
